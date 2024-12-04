package interface_adapter.recommend_recipes;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * Interface for accessing recipe data.
 * Provides methods to fetch recipes based on ingredients or a query, with user preferences.
 */
public interface RecipesDataAccessInterface {

    /**
     * Retrieves a list of recipes based on the given ingredients and user preferences.
     *
     * @param ingredients     A list of ingredient names to search for recipes.
     * @param userPreferences User-specific preferences for filtering recipes.
     * @return An ArrayList of Recipe objects matching the search criteria.
     * @throws Exception If an error occurs during the recipe retrieval process.
     */
    ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                UserPreferences userPreferences) throws Exception;

    /**
     * Retrieves a list of recipes based on the given query and user preferences.
     *
     * @param query           The search query for recipes (e.g., a type of cuisine or dish).
     * @param userPreferences User-specific preferences for filtering recipes.
     * @return An ArrayList of Recipe objects matching the search criteria.
     * @throws Exception If an error occurs during the recipe retrieval process.
     */
    ArrayList<Recipe> getRecipesFromQuery(String query, UserPreferences userPreferences) throws Exception;
}
