package use_case.recommend_holiday;

import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;

public interface HolidayInputBoundary {
    void return_to_main();
    String getHoliday();
    ArrayList<Recipe> getRecipeFromHoliday(UserPreferences userPreferences, boolean userInfo);
}
