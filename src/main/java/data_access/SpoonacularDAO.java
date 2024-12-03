package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;
import entity.UserPreferences;
import interface_adapter.recommend_recipes.RecipesDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The DAO for the Spoonacular API.
 */
public class SpoonacularDAO implements RecipesDataAccessInterface {
    private static final String API_KEY = "d66d2965b6974f7a82df73c404b8bd0a";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/";
    private final RecipeFactory recipeFactory;
    private final OkHttpClient client;

    /**
     * Constructs a {@code SpoonacularDAO} instance.
     * Initializes the HTTP client and the recipe factory.
     */
    public SpoonacularDAO() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        this.recipeFactory = new CommonRecipeFactory();
    }

    /**
     * Retrieves recipes based on the given ingredients and user preferences.
     *
     * @param ingredients        the ingredients to search for
     * @param userPreferences the user's dietary preferences
     * @return the recipe information
     * @throws IOException if the request fails
     */
    public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                       UserPreferences userPreferences) {
        final String ingredientsStr = String.join(",", ingredients);
        final String url = BASE_URL + "findByIngredients?ingredients=" + ingredientsStr
                + "&number=100&apiKey=" + API_KEY;
        final Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONArray recipes = new JSONArray(responseBody);
            final HashMap<Integer, Integer> recipeIDs = new HashMap<>();
            for (int i = 0; i < recipes.length(); i++) {
                final JSONObject recipe = recipes.getJSONObject(i);
                if (recipe.getInt("missedIngredientCount") <= userPreferences.getMissingIngredientsLimit()) {
                    recipeIDs.put(Integer.valueOf(recipe.getInt("id")),
                            Integer.valueOf(recipe.getInt("missedIngredientCount")));
                }
            }
            return getRecipeInfoFromID(recipeIDs, userPreferences);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Helper method to get recipe information from recipe IDs.
     *
     * @param recipeIDs the recipe IDs to get information for
     * @param userPreferences the user's dietary preferences
     * @return the recipe information
     * @throws IOException if the request fails
     */
    private ArrayList<Recipe> getRecipeInfoFromID(HashMap<Integer, Integer> recipeIDs,
                                                  UserPreferences userPreferences) {
        final ArrayList<Recipe> recipeInfo = new ArrayList<>();
        outerLoop:
        for (HashMap.Entry<Integer, Integer> entry : recipeIDs.entrySet()) {
            final int recipeID = entry.getKey();
            System.out.println(recipeID);
            final int missedIngredients = entry.getValue();
            final String url = BASE_URL + recipeID + "/information?apiKey=" + API_KEY;
            final Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                final String responseBody = response.body().string();
                final JSONObject recipe = new JSONObject(responseBody);
                if (userPreferences.getDairyFree() && recipe.getBoolean("dairyFree")) {
                    continue;
                }
                if (userPreferences.getGlutenFree() && recipe.getBoolean("glutenFree")) {
                    continue;
                }
                if (userPreferences.getAllergies() != null) {
                    final JSONArray recipeAllergens = recipe.getJSONArray("extendedIngredients");
                    for (int i = 0; i < recipeAllergens.length(); i++) {
                        final JSONObject allergen = recipeAllergens.getJSONObject(i);
                        final String allergenName = allergen.getString("name");
                        for (String allergy : userPreferences.getAllergies()) {
                            if (allergenName.toLowerCase().contains(allergy.toLowerCase())) {
                                continue outerLoop;
                            }
                        }
                    }
                }
                recipeInfo.add(recipeFactory.create(recipe.getString("title"), recipe.getString("sourceUrl"),
                        Integer.valueOf(missedIngredients)));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return recipeInfo;
    }

    /**
     * Retrieves recipes based on a text query and user preferences.
     *
     * @param query the query to search for
     * @param userPreferences the user's dietary preferences
     * @return the recipe information
     * @throws IOException if the request fails
     */
    public ArrayList<Recipe> getRecipesFromQuery(String query, UserPreferences userPreferences) {
        final String url = BASE_URL + "complexSearch?query=" + query + "&number=100&apiKey=" + API_KEY;
        final Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONArray recipes = new JSONObject(responseBody).getJSONArray("results");
            final List<Integer> recipeIDs = new ArrayList<>();
            for (int i = 0; i < recipes.length(); i++) {
                final JSONObject recipe = recipes.getJSONObject(i);
                recipeIDs.add(recipe.getInt("id"));
            }
            Collections.shuffle(recipeIDs);
            final ArrayList<Integer> selectedRecipeIDs = new ArrayList<>();
            for (int i = 0; i < 10 && i < recipeIDs.size(); i++) {
                selectedRecipeIDs.add(recipeIDs.get(i));
            }
            return getRecipeInfoFromIDQuery(selectedRecipeIDs, userPreferences);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Helper method to fetch recipe details based on a list of recipe IDs from a query search.
     *
     * @param recipeIDs       a list of recipe IDs to fetch details for
     * @param userPreferences the user's dietary preferences
     * @return a list of {@code Recipe} objects with detailed information
     * @throws IOException if the request to the Spoonacular API fails
     */
    private ArrayList<Recipe> getRecipeInfoFromIDQuery(ArrayList<Integer> recipeIDs, UserPreferences userPreferences) {
        final ArrayList<Recipe> recipeInfo = new ArrayList<>();
        outerLoop:
        for (int recipeID : recipeIDs) {
            final String url = BASE_URL + recipeID + "/information?apiKey=" + API_KEY;
            final Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                final String responseBody = response.body().string();
                final JSONObject recipe = new JSONObject(responseBody);
                if (userPreferences.getDairyFree() && recipe.getBoolean("dairyFree")) {
                    continue;
                }
                if (userPreferences.getGlutenFree() && recipe.getBoolean("glutenFree")) {
                    continue;
                }
                if (userPreferences.getAllergies() != null) {
                    final JSONArray recipeAllergens = recipe.getJSONArray("extendedIngredients");
                    for (int i = 0; i < recipeAllergens.length(); i++) {
                        final JSONObject allergen = recipeAllergens.getJSONObject(i);
                        final String allergenName = allergen.getString("name");
                        for (String allergy : userPreferences.getAllergies()) {
                            if (allergenName.toLowerCase().contains(allergy.toLowerCase())) {
                                continue outerLoop;
                            }
                        }
                    }
                }
                recipeInfo.add(recipeFactory.create(recipe.getString("title"), recipe.getString("sourceUrl"), 0));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return recipeInfo;
    }
}
