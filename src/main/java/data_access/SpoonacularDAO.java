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
    private static final String API_KEY = "0932dddc83804dd589d24608dc16182f";
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
                                                       UserPreferences userPreferences) throws IOException {
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
    }

    /**
     * Helper method to get recipe information from recipe IDs.
     *
     * @param recipeIDs the recipe IDs to get information for
     * @return the recipe information
     * @throws IOException if the request fails
     */
    private ArrayList<Recipe> getRecipeInfoFromID(HashMap<Integer, Integer> recipeIDs, UserPreferences userPreferences) throws IOException {
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
        }
        return recipeInfo;
    }

    /**
     * @param query the query to search for
     * @return the recipe information
     * @throws IOException if the request fails
     */
    public ArrayList<Recipe> getRecipesFromQuery(String query, UserPreferences userPreferences) throws IOException {
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
    }

    private ArrayList<Recipe> getRecipeInfoFromIDQuery(ArrayList<Integer> recipeIDs, UserPreferences userPreferences) throws IOException {
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
        String[] fruits = {"pear"};
        UserPreferences userPreferences = new UserPreferences(5, false, false, fruits);



        try {
            ArrayList<String> ingredients = new ArrayList<>();
//            ingredients.add("apple");
//            ingredients.add("flour");
//            ingredients.add("sugar");
//            ingredients.add("butter");
//            ingredients.add("oats");
//            ingredients.add("cranberries");
            ArrayList<Recipe> recipes = dao.getRecipesFromIngredients(ingredients,  userPreferences);
            for (Recipe recipe : recipes) {
                System.out.println(recipe.getName());
                System.out.println(recipe.getUrl());
                System.out.println(recipe.getMissingItems());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            ArrayList<Recipe> recipes = dao.getRecipesFromQuery("apple", userPreferences);
//            for (Recipe recipe : recipes) {
//                System.out.println(recipe.getName());
//                System.out.println(recipe.getUrl());
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}