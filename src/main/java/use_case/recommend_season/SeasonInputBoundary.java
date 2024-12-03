package use_case.recommend_season;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * Interface defining the input boundary for season-based recipe recommendations.
 */
public interface SeasonInputBoundary {

    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Retrieves the current date.
     *
     * @return The current date as a String.
     */
    String getDate();

    /**
     * Retrieves the current season based on the date.
     *
     * @return The current season as a String.
     */
    String getSeason();

    /**
     * Retrieves recipes based on the season and user preferences.
     *
     * @param userPreferences The user's preferences for recipe recommendations.
     * @param userInfo        A boolean indicating if user-specific information should be considered.
     * @return A list of recipes matching the season and user preferences.
     */
    ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo);
}
