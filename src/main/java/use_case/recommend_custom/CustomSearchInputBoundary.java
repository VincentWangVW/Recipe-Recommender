package use_case.recommend_custom;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * The CustomSearchInputBoundary interface defines the input boundary for the custom recipe search use case.
 * It provides a method for retrieving recipes based on custom search criteria, user preferences,
 * and whether user information should be considered.
 */
public interface CustomSearchInputBoundary {

    /**
     * Retrieves a list of recipes based on custom search criteria and user preferences.
     *
     * @param custom The custom search query or criteria provided by the user.
     * @param userPreferences The user preferences to filter the recipes.
     * @param userInfo A flag indicating whether user-specific information (like allergies, dietary restrictions)
     *                 should be considered when retrieving recipes.
     * @return A list of recipes matching the custom search criteria and preferences.
     */
    ArrayList<Recipe> getRecipesFromCustom(String custom, UserPreferences userPreferences, boolean userInfo);
}
