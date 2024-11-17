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

import entity.Recipe;
import entity.RecipeFactory;

import entity.CommonRecipeFactory;

/**
 * The DAO for the Spoonacular API.
 */
public class SpoonacularDAO implements RecipesDataAccessInterface {
    private static final String API_KEY = "0932dddc83804dd589d24608dc16182f";
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/";
    private final RecipeFactory recipeFactory;

    private final OkHttpClient client;

    public SpoonacularDAO() {
        this.client = new OkHttpClient();
        this.recipeFactory = new CommonRecipeFactory();
    }

    /**
     * @param ingredients        the ingredients to search for
     * @param missingIngredients the number of missing ingredients allowed
     * @return the recipe information
     * @throws IOException if the request fails
     */
    public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                       int missingIngredients) throws IOException {
        String ingredientsStr = String.join(",", ingredients);
        String url = BASE_URL + "findByIngredients?ingredients=" + ingredientsStr + "&number=10&apiKey=" + API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            JSONArray recipes = new JSONArray(responseBody);
            HashMap<Integer, Integer> recipeIDs = new HashMap<>();
            for (int i = 0; i < recipes.length(); i++) {
                JSONObject recipe = recipes.getJSONObject(i);
                if (recipe.getInt("missedIngredientCount") <= missingIngredients) {
                    recipeIDs.put(Integer.valueOf(recipe.getInt("id")),
                            Integer.valueOf(recipe.getInt("missedIngredientCount")));
                }
            }
            return getRecipeInfoFromID(recipeIDs);
        }
    }

    /**
     * Helper method to get recipe information from recipe IDs.
     *
     * @param recipeIDs the recipe IDs to get information for
     * @return the recipe information
     * @throws IOException if the request fails
     */
    private ArrayList<Recipe> getRecipeInfoFromID(HashMap<Integer, Integer> recipeIDs) throws IOException {
        ArrayList<Recipe> recipeInfo = new ArrayList<>();
        for (HashMap.Entry<Integer, Integer> entry : recipeIDs.entrySet()) {
            int recipeID = entry.getKey();
            int missedIngredients = entry.getValue();
            String url = BASE_URL + recipeID + "/information?apiKey=" + API_KEY;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                JSONObject recipe = new JSONObject(responseBody);
                recipeInfo.add(recipeFactory.create(recipe.getString("title"), recipe.getString("sourceUrl"),
                        Integer.valueOf(missedIngredients)));
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
        SpoonacularDAO dao = new SpoonacularDAO();
        try {
            ArrayList<String> ingredients = new ArrayList<>();
            ingredients.add("apple");
            ingredients.add("flour");
            ingredients.add("sugar");
            ingredients.add("butter");
            ingredients.add("oats");
            ingredients.add("cranberries");
            ArrayList<Recipe> recipes = dao.getRecipesFromIngredients(ingredients, 0);
            for (Recipe recipe : recipes) {
                System.out.println(recipe.getName());
                System.out.println(recipe.getUrl());
                System.out.println(recipe.getMissingItems());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}