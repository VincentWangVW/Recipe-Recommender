package use_case.recommend_season;

import java.util.ArrayList;

import data_access.InMemoryDateInfoDAO;
import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

/**
 * The SeasonInteractor class is responsible for managing the use case logic related to seasonal recipe recommendations.
 * It interacts with data sources to retrieve the current date and season and fetches recipes based on the user's
 * preferences.
 */
public class SeasonInteractor implements SeasonInputBoundary {
    public final SeasonOutputBoundary seasonpresenter;
    private final InMemoryDateInfoDAO inMemoryDateInfoDAO;
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false,
            false, new String[0]);

    /**
     * Constructs a new SeasonInteractor.
     *
     * @param seasonpresenter the output boundary used to communicate with the presenter layer
     * @param inMemoryDateInfoDAO the data access object for retrieving date and season information
     * @param spoonacularDAO the data access object used to fetch recipes based on various criteria
     */
    public SeasonInteractor(SeasonOutputBoundary seasonpresenter, InMemoryDateInfoDAO inMemoryDateInfoDAO,
                            SpoonacularDAO spoonacularDAO) {
        this.seasonpresenter = seasonpresenter;
        this.inMemoryDateInfoDAO = inMemoryDateInfoDAO;
        this.spoonacularDAO = spoonacularDAO;
    }

    /**
     * Returns to the main screen by delegating the action to the presenter.
     */
    @Override
    public void return_to_main() {
        seasonpresenter.return_to_main();
    }

    /**
     * Retrieves the current date from the data access layer and passes it to the presenter.
     *
     * @return the current date as a string
     */
    @Override
    public String getDate() {
        return seasonpresenter.getDate(inMemoryDateInfoDAO.get_date());
    }

    /**
     * Retrieves the current season from the data access layer and passes it to the presenter.
     *
     * @return the current season as a string (e.g., "Winter", "Spring")
     */
    @Override
    public String getSeason() {
        return seasonpresenter.getSeason(inMemoryDateInfoDAO.get_season());
    }

    /**
     * Retrieves recipes based on the current season and the provided user preferences.
     * If user-specific information is provided, the recipes are personalized accordingly.
     *
     * @param userPreferences the user preferences used to personalize the recipe suggestions
     * @param userInfo a flag indicating whether to consider user-specific information
     * @return a list of recipes suitable for the current season
     */
    @Override
    public ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo) {
        if (userInfo) {

            return spoonacularDAO.getRecipesFromQuery(getSeason(), userPreferences);
        }
        else {
            return spoonacularDAO.getRecipesFromQuery(getSeason(), nullPreferences);
        }
    }
}
