package interface_adapter.datescreen;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;
import use_case.recommend_holiday.HolidayInputBoundary;
import use_case.recommend_season.SeasonInputBoundary;

/**
 * The DateController class combines season and holiday functionalities
 * by delegating requests to the respective input boundaries.
 */
public class DateController implements SeasonInputBoundary, HolidayInputBoundary {
    private final SeasonInputBoundary seasonInputBoundary;
    private final HolidayInputBoundary holidayInputBoundary;

    /**
     * Constructs a DateController with the given season and holiday interactors.
     *
     * @param seasonInteractor  The interactor for season-related operations.
     * @param holidayInteractor The interactor for holiday-related operations.
     */
    public DateController(SeasonInputBoundary seasonInteractor, HolidayInputBoundary holidayInteractor) {
        this.seasonInputBoundary = seasonInteractor;
        this.holidayInputBoundary = holidayInteractor;
    }

    /**
     * Retrieves the current date.
     *
     * @return The current date as a String.
     */
    public String getDate() {
        return seasonInputBoundary.getDate();
    }

    /**
     * Retrieves the current season.
     *
     * @return The current season as a String.
     */
    public String getSeason() {
        return seasonInputBoundary.getSeason();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo) {
        return seasonInputBoundary.getRecipesFromSeason(userPreferences, userInfo);
    }

    /**
     * Retrieves the current holiday.
     *
     * @return The current holiday as a String.
     */
    public String getHoliday() {
        return holidayInputBoundary.getHoliday();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo) {
        return holidayInputBoundary.getRecipesFromHoliday(userPreferences, userInfo);
    }

    /**
     * Returns to the main screen.
     */
    public void returnTomain() {
        seasonInputBoundary.returnTomain();
    }
}
