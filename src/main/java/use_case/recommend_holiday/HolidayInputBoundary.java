package use_case.recommend_holiday;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * Interface defining the input boundary for holiday recommendations.
 */
public interface HolidayInputBoundary {

    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Retrieves the current holiday being used in the recommendation process.
     *
     * @return The name of the holiday as a String.
     */
    String getHoliday();

    /**
     * Retrieves recipes based on the specified holiday and user preferences.
     *
     * @param userPreferences The user's preferences for recipe recommendations.
     * @param userInfo        A boolean indicating if user-specific information should be considered.
     * @return A list of recipes matching the holiday recommendation criteria.
     */
    ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo);
}
