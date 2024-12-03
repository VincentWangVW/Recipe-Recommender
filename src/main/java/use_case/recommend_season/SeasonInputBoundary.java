package use_case.recommend_season;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * The SeasonInputBoundary interface defines the contract for managing the input related to seasonal recipe
 * recommendations.
 * It includes methods for retrieving seasonal recipes based on user preferences and interacting with the
 * system's date and season data.
 */
public interface SeasonInputBoundary {

    /**
     * Returns to the main screen, typically by triggering the appropriate UI update.
     */
    void return_to_main();

    /**
     * Retrieves the current date.
     *
     * @return a string representing the current date
     */
    String getDate();

    /**
     * Retrieves the current season based on the system's date.
     *
     * @return a string representing the current season (e.g., "Winter", "Spring")
     */
    String getSeason();

    /**
     * Retrieves a list of recipes for the current season based on the provided user preferences.
     *
     * @param userPreferences the user preferences to personalize the recipe suggestions
     * @param userInfo a flag indicating whether to consider user-specific information
     * @return a list of recipes suitable for the current season
     */
    ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo);
}
