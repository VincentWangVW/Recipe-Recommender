package interface_adapter.datescreen;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;
import use_case.recommend_holiday.HolidayInputBoundary;
import use_case.recommend_season.SeasonInputBoundary;

/**
 * The controller for managing date-related functionalities.
 * Delegates requests to season and holiday interactors.
 */
public class DateController implements SeasonInputBoundary, HolidayInputBoundary {
    private final SeasonInputBoundary seasonInputBoundary;
    private final HolidayInputBoundary holidayInputBoundary;

    /**
     * Constructs a {@code DateController} with the specified season and holiday interactors.
     *
     * @param seasonInteractor  the interactor handling season-related operations
     * @param holidayInteractor the interactor handling holiday-related operations
     */
    public DateController(SeasonInputBoundary seasonInteractor, HolidayInputBoundary holidayInteractor) {
        this.seasonInputBoundary = seasonInteractor;
        this.holidayInputBoundary = holidayInteractor;
    }

    /**
     * Retrieves the current date.
     *
     * @return the current date in "yyyy-MM-dd" format
     */
    public String getDate() {
        return seasonInputBoundary.getDate();
    }

    /**
     * Retrieves the current season.
     *
     * @return the current season as a string
     */
    public String getSeason() {
        return seasonInputBoundary.getSeason();
    }

    /**
     * Retrieves recipes based on the current season and user preferences.
     *
     * @param userPreferences the user's dietary preferences
     * @param userInfo        a flag indicating whether to include user-specific information
     * @return a list of {@code Recipe} objects for the current season
     */
    @Override
    public ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo) {
        return seasonInputBoundary.getRecipesFromSeason(userPreferences, userInfo);
    }

    /**
     * Retrieves the holiday for the current date.
     *
     * @return the name of the holiday, or "No Holiday Today!" if none exists
     */
    public String getHoliday() {
        return holidayInputBoundary.getHoliday();
    }

    /**
     * Retrieves recipes based on the current holiday and user preferences.
     *
     * @param userPreferences the user's dietary preferences
     * @param userInfo        a flag indicating whether to include user-specific information
     * @return a list of {@code Recipe} objects for the current holiday
     */
    @Override
    public ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo) {
        return holidayInputBoundary.getRecipesFromHoliday(userPreferences, userInfo);
    }

    /**
     * Returns the user to the main screen.
     */
    public void returnTomain() {
        seasonInputBoundary.returnTomain();
    }
}
