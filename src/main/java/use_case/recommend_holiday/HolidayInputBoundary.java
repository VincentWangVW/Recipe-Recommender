package use_case.recommend_holiday;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;

/**
 * The HolidayInputBoundary interface defines the contract for use cases related to holiday-based recipe
 * recommendations.
 * Implementations of this interface will handle operations related to retrieving holiday-specific recipes.
 */
public interface HolidayInputBoundary {

    /**
     * Returns to the main screen or main menu.
     */
    void return_to_main();

    /**
     * Retrieves the current holiday or the selected holiday.
     *
     * @return a String representing the current or selected holiday
     */
    String getHoliday();

    /**
     * Retrieves a list of recipes based on the holiday and user preferences.
     * If user information is provided, it customizes the recipe search according to the user's preferences.
     *
     * @param userPreferences the user preferences to customize the search (if applicable)
     * @param userInfo        a flag indicating whether user preferences should be applied
     * @return an ArrayList of Recipe objects that match the holiday and user preferences
     */
    ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo);
}
