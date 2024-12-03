package use_case.recommend_custom;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * Interface defining the input boundary for the custom search recommendation use case.
 */
public interface CustomSearchInputBoundary {

    /**
     * Retrieves recipes based on custom search parameters.
     *
     * @param custom          The custom search parameter provided by the user.
     * @param userPreferences The user's preferences for the recipe search.
     * @param userInfo        A boolean indicating if user-specific information should be considered.
     * @return A list of recipes matching the custom search criteria.
     */
    ArrayList<Recipe> getRecipesFromCustom(String custom, UserPreferences userPreferences, boolean userInfo);
}
