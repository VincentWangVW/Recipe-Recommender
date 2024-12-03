package interface_adapter.recommend_recipes;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * Interface for data access related to recipe recommendations.
 * This interface defines methods for retrieving recipes based on ingredients and user queries.
 * The methods may throw exceptions if the recipe retrieval fails.
 */
public interface RecipesDataAccessInterface {

    /**
     * Retrieves a list of recipes based on the provided ingredients and user preferences.
     * This method may throw an exception if the recipe retrieval fails.
     *
     * @param ingredients A list of ingredients to filter the recipes by.
     * @param userPreferences The preferences of the user to filter recipes further.
     * @return A list of recipes that match the provided ingredients and user preferences.
     * @throws Exception If an error occurs while retrieving the recipes.
     */
    ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                UserPreferences userPreferences) throws Exception;

    /**
     * Retrieves a list of recipes based on a user-provided query and their preferences.
     * This method may throw an exception if the recipe retrieval fails.
     *
     * @param query The query string to filter recipes by.
     * @param userPreferences The preferences of the user to filter recipes further.
     * @return A list of recipes that match the provided query and user preferences.
     * @throws Exception If an error occurs while retrieving the recipes.
     */
    ArrayList<Recipe> getRecipesFromQuery(String query, UserPreferences userPreferences) throws Exception;
}
