package use_case.recommend_custom;

import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;

public class CustomSearchInteractor implements CustomSearchInputBoundary {
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    public CustomSearchInteractor() {
        this.spoonacularDAO = new SpoonacularDAO();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromCustom(String custom, UserPreferences userPreferences, boolean userInfo) {
        if (userInfo) {
            return spoonacularDAO.getRecipesFromQuery(custom, userPreferences);
        }
        else {
            return spoonacularDAO.getRecipesFromQuery(custom, nullPreferences);
        }
    }
}