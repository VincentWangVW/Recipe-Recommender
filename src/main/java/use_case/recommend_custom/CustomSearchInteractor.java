package use_case.recommend_custom;

import java.util.ArrayList;

import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

/**
 * The CustomSearchInteractor class implements the CustomSearchInputBoundary interface
 * and is responsible for retrieving recipes based on a custom search query.
 * It interacts with the SpoonacularDAO to fetch recipe data based on the user's preferences.
 */
public class CustomSearchInteractor implements CustomSearchInputBoundary {
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    /**
     * Constructs a new CustomSearchInteractor with a new instance of SpoonacularDAO.
     */
    public CustomSearchInteractor() {
        this.spoonacularDAO = new SpoonacularDAO();
    }

    /**
     * Retrieves recipes based on a custom search query. If user information is available,
     * it includes the user preferences; otherwise, it uses default (null) preferences.
     *
     * @param custom        the custom search query to filter recipes
     * @param userPreferences the user preferences to customize the search (if applicable)
     * @param userInfo      a flag indicating whether user preferences should be applied
     * @return an ArrayList of Recipe objects that match the search criteria
     */
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
