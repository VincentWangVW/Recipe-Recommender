package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import interface_adapter.recommend_recipes.RecipesDataAccessInterface;

/**
 * The DAO for the Spoonacular API.
 */
public class SpoonacularDAO implements RecipesDataAccessInterface {
    private static final String API_KEY = "0932dddc83804dd589d24608dc16182f";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/";

    private final OkHttpClient client;

    public SpoonacularDAO() {
        this.client = new OkHttpClient();
    }

    /**
     *
     * @param ingredients the ingredients to search for
     * @param missingIngredients the number of missing ingredients allowed
     * @return the recipe information
     * @throws IOException if the request fails
     */
    public HashMap<Integer, ArrayList<String>> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                                         int missingIngredients) throws IOException {
        String ingredientsStr = String.join(",", ingredients);
        String url = BASE_URL + "findByIngredients?ingredients=" + ingredientsStr + "&number=10&apiKey=" + API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONArray recipes = new JSONArray(responseBody);
            ArrayList<Integer> recipeIDs = new ArrayList<>();
            for (int i = 0; i < recipes.length(); i++) {
                JSONObject recipe = recipes.getJSONObject(i);
                if (recipe.getInt("missedIngredientCount") <= missingIngredients) {
                    recipeIDs.add(recipe.getInt("id"));
                }
            }
            return getRecipeInfoFromID(recipeIDs);
        }
    }

    /**
     * Helper method to get recipe information from recipe IDs.
     * @param recipeIDs the recipe IDs to get information for
     * @return the recipe information
     * @throws IOException if the request fails
     */
    private HashMap<Integer, ArrayList<String>> getRecipeInfoFromID(ArrayList<Integer> recipeIDs) throws IOException {
        HashMap<Integer, ArrayList<String>> recipeInfo = new HashMap<>();
        for (int recipeID : recipeIDs) {
            String url = BASE_URL + recipeID + "/information?apiKey=" + API_KEY;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                JSONObject recipe = new JSONObject(responseBody);
                ArrayList<String> recipeDetails = new ArrayList<>();
                recipeDetails.add(recipe.getString("title"));
                recipeDetails.add(recipe.getString("sourceUrl"));
                recipeInfo.put(recipeID, recipeDetails);
            }
        }
        return recipeInfo;
    }

    /**
     *
     * @param args the arguments
     */
    // TODO GET RID OF THIS ------------------------- TETSING ONLY
    public static void main(String[] args) {
        try {
            SpoonacularDAO client = new SpoonacularDAO();
            ArrayList<String> ingredients = new ArrayList<>();
            ingredients.add("apple");
            ingredients.add("flour");
            ingredients.add("sugar");
            ingredients.add("cranberries");
            ingredients.add("butter");
            ingredients.add("oats");

//            ingredients.add("carrots");
//            ingredients.add("tomatoes");

            HashMap<Integer, ArrayList<String>> recipeInfo = client.getRecipesFromIngredients(ingredients, 0);
            for (int recipeID : recipeInfo.keySet()) {
                System.out.println("Recipe ID: " + recipeID);
                System.out.println("Recipe Title: " + recipeInfo.get(recipeID).get(0));
                System.out.println("Recipe URL: " + recipeInfo.get(recipeID).get(1));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
