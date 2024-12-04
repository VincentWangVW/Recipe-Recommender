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
public class SpoonacularDao implements RecipesDataAccessInterface {
    private static final String API_KEY = "d66d2965b6974f7a82df73c404b8bd0a";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/";
    private static final int TIMEOUT_SECONDS = 30;
    private static final int MAX_RECIPES = 100;
    private static final int MAX_SELECTED_RECIPES = 10;
    private final RecipeFactory recipeFactory;
    private final OkHttpClient client;

    /**
     * Constructs a {@code SpoonacularDAO} instance.
     * Initializes the HTTP client and the recipe factory.
     */
    public SpoonacularDao() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
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
                + "&number=" + MAX_RECIPES + "&apiKey=" + API_KEY;
        final Request request = new Request.Builder()
                .url(url)
                .build();

        ArrayList<Recipe> finalRecipes = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONArray recipes = new JSONArray(responseBody);
            final HashMap<Integer, Integer> recipeIds = new HashMap<>();
            for (int i = 0; i < recipes.length(); i++) {
                final JSONObject recipe = recipes.getJSONObject(i);
                if (recipe.getInt("missedIngredientCount") <= userPreferences.getMissingIngredientsLimit()) {
                    recipeIds.put(Integer.valueOf(recipe.getInt("id")),
                            Integer.valueOf(recipe.getInt("missedIngredientCount")));
                }
            }
            finalRecipes = getRecipeInfoFromID(recipeIds, userPreferences);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return finalRecipes;
    }

    /**
     * Helper method to get recipe information from recipe IDs.
     *
     * @param recipeIds the recipe IDs to get information for
     * @param userPreferences the user's dietary preferences
     * @return the recipe information
     * @throws IOException if the request fails
     */
    private ArrayList<Recipe> getRecipeInfoFromID(HashMap<Integer, Integer> recipeIds,
                                                  UserPreferences userPreferences) {
        final ArrayList<Recipe> recipeInfo = new ArrayList<>();
        outerLoop:
        for (HashMap.Entry<Integer, Integer> entry : recipeIds.entrySet()) {
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
            catch (IOException exception) {
                exception.printStackTrace();
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
        final String url = BASE_URL + "complexSearch?query=" + query + "&number=" + MAX_RECIPES + "&apiKey=" + API_KEY;
        final Request request = new Request.Builder()
                .url(url)
                .build();

        ArrayList<Recipe> finalRecipes = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            final String responseBody = response.body().string();
            final JSONArray recipes = new JSONObject(responseBody).getJSONArray("results");
            final List<Integer> recipeIds = new ArrayList<>();
            for (int i = 0; i < recipes.length(); i++) {
                final JSONObject recipe = recipes.getJSONObject(i);
                recipeIds.add(recipe.getInt("id"));
            }
            Collections.shuffle(recipeIds);
            final ArrayList<Integer> selectedRecipeIds = new ArrayList<>();
            for (int i = 0; i < MAX_SELECTED_RECIPES && i < recipeIds.size(); i++) {
                selectedRecipeIds.add(recipeIds.get(i));
            }
            finalRecipes = getRecipeInfoFromIDQuery(selectedRecipeIds, userPreferences);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return finalRecipes;
    }

    /**
     * Helper method to fetch recipe details based on a list of recipe IDs from a query search.
     *
     * @param recipeIds       a list of recipe IDs to fetch details for
     * @param userPreferences the user's dietary preferences
     * @return a list of {@code Recipe} objects with detailed information
     * @throws IOException if the request to the Spoonacular API fails
     */
    private ArrayList<Recipe> getRecipeInfoFromIDQuery(ArrayList<Integer> recipeIds, UserPreferences userPreferences) {
        final ArrayList<Recipe> recipeInfo = new ArrayList<>();
        outerLoop:
        for (int recipeID : recipeIds) {
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
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return recipeInfo;
    }
}
