package use_case.recommend_custom;

import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;

public interface CustomSearchInputBoundary {
    public ArrayList<Recipe> getRecipesFromCustom(String custom, UserPreferences userPreferences, boolean userInfo);
}
