package use_case.recommend_season;

import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;

public interface SeasonInputBoundary {
    void return_to_main();
    String getDate();
    String getSeason();
    ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo);
}