package data_access;

import interface_adapter.recommend_recipes.RecipesDataAccessInterface;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Recipe;
import entity.RecipeFactory;
import entity.UserPreferences;

import entity.CommonRecipeFactory;

/**
 * The DAO for the Spoonacular API.
 */
public class SpoonacularDAO implements RecipesDataAccessInterface {
    private static final String API_KEY = "545260c1d16f42fcafa426a015ab14d6"; //"0932dddc83804dd589d24608dc16182f";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/";
    private final RecipeFactory recipeFactory;
    private final OkHttpClient client;

    public SpoonacularDAO() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        this.recipeFactory = new CommonRecipeFactory();
    }

    /**
     * @param ingredients        the ingredients to search for
     * @return the recipe information
     * @throws IOException if the request fails
     */
    public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                       UserPreferences userPreferences) {
        String ingredientsStr = String.join(",", ingredients);
        String url = BASE_URL + "findByIngredients?ingredients=" + ingredientsStr + "&number=100&apiKey=" + API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONArray recipes = new JSONArray(responseBody);
            HashMap<Integer, Integer> recipeIDs = new HashMap<>();
            for (int i = 0; i < recipes.length(); i++) {
                JSONObject recipe = recipes.getJSONObject(i);
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
     * @return the recipe information
     * @throws IOException if the request fails
     */
    private ArrayList<Recipe> getRecipeInfoFromID(HashMap<Integer, Integer> recipeIDs, UserPreferences userPreferences) {
        ArrayList<Recipe> recipeInfo = new ArrayList<>();
        outerLoop:
        for (HashMap.Entry<Integer, Integer> entry : recipeIDs.entrySet()) {
            int recipeID = entry.getKey();
            System.out.println(recipeID);
            int missedIngredients = entry.getValue();
            String url = BASE_URL + recipeID + "/information?apiKey=" + API_KEY;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                JSONObject recipe = new JSONObject(responseBody);
                if (userPreferences.getDairyFree() && recipe.getBoolean("dairyFree")) {
                    continue;
                }
                if (userPreferences.getGlutenFree() && recipe.getBoolean("glutenFree")) {
                    continue;
                }
                if (userPreferences.getAllergies() != null) {
                    JSONArray recipeAllergens = recipe.getJSONArray("extendedIngredients");
                    for (int i = 0; i < recipeAllergens.length(); i++) {
                        JSONObject allergen = recipeAllergens.getJSONObject(i);
                        String allergenName = allergen.getString("name");
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
     * @param query the query to search for
     * @return the recipe information
     * @throws IOException if the request fails
     */
    public ArrayList<Recipe> getRecipesFromQuery(String query, UserPreferences userPreferences) {
        String url = BASE_URL + "complexSearch?query=" + query + "&number=100&apiKey=" + API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONArray recipes = new JSONObject(responseBody).getJSONArray("results");
            List<Integer> recipeIDs = new ArrayList<>();
            for (int i = 0; i < recipes.length(); i++) {
                JSONObject recipe = recipes.getJSONObject(i);
                recipeIDs.add(recipe.getInt("id"));
            }
            Collections.shuffle(recipeIDs);
            ArrayList<Integer> selectedRecipeIDs = new ArrayList<>();
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

    private ArrayList<Recipe> getRecipeInfoFromIDQuery(ArrayList<Integer> recipeIDs, UserPreferences userPreferences) {
        ArrayList<Recipe> recipeInfo = new ArrayList<>();
        outerLoop:
        for (int recipeID : recipeIDs) {
            String url = BASE_URL + recipeID + "/information?apiKey=" + API_KEY;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                JSONObject recipe = new JSONObject(responseBody);
                if (userPreferences.getDairyFree() && recipe.getBoolean("dairyFree")) {
                    continue;
                }
                if (userPreferences.getGlutenFree() && recipe.getBoolean("glutenFree")) {
                    continue;
                }
                if (userPreferences.getAllergies() != null) {
                    JSONArray recipeAllergens = recipe.getJSONArray("extendedIngredients");
                    for (int i = 0; i < recipeAllergens.length(); i++) {
                        JSONObject allergen = recipeAllergens.getJSONObject(i);
                        String allergenName = allergen.getString("name");
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