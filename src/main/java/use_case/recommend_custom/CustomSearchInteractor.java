package use_case.recommend_custom;

import java.util.ArrayList;

import data_access.SpoonacularDao;
import entity.Recipe;
import entity.UserPreferences;

/**
 * The CustomSearchInteractor class implements the CustomSearchInputBoundary interface
 * and provides methods to retrieve recipes based on a custom query.
 */
public class CustomSearchInteractor implements CustomSearchInputBoundary {
    private final SpoonacularDao spoonacularDao;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    /**
     * Constructs a CustomSearchInteractor instance.
     */
    public CustomSearchInteractor() {

        this.spoonacularDao = new SpoonacularDao();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromCustom(String custom, UserPreferences userPreferences, boolean userInfo) {
        final ArrayList<Recipe> recipes;
        if (userInfo) {
            recipes = spoonacularDao.getRecipesFromQuery(custom, userPreferences);
        }
        else {
            recipes = spoonacularDao.getRecipesFromQuery(custom, nullPreferences);
        }
        return recipes;
    }
}
