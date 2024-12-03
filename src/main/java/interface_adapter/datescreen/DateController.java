package interface_adapter.datescreen;

import entity.Recipe;
import entity.UserPreferences;
import use_case.recommend_holiday.HolidayInputBoundary;
import use_case.recommend_season.SeasonInputBoundary;

import java.util.ArrayList;

public class DateController implements SeasonInputBoundary, HolidayInputBoundary {
    private final SeasonInputBoundary seasonInputBoundary;
    private final HolidayInputBoundary holidayInputBoundary;

    public DateController(SeasonInputBoundary seasonInteractor, HolidayInputBoundary holidayInteractor) {
        this.seasonInputBoundary = seasonInteractor;
        this.holidayInputBoundary = holidayInteractor;
    }

    public String getDate() {
        return seasonInputBoundary.getDate();
    }

    public String getSeason() {
        return seasonInputBoundary.getSeason();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo) {
        return seasonInputBoundary.getRecipesFromSeason(userPreferences, userInfo);
    }

    public String getHoliday() {
        return holidayInputBoundary.getHoliday();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo) {
        return holidayInputBoundary.getRecipesFromHoliday(userPreferences, userInfo);
    }

    public void returnTomain() {
        seasonInputBoundary.returnTomain();
    }
}
