package use_case.recommend_season;

import java.util.ArrayList;

import data_access.InMemoryDateInfoDao;
import data_access.SpoonacularDao;
import entity.Recipe;
import entity.UserPreferences;

/**
 * The SeasonInteractor class implements the SeasonInputBoundary interface
 * and provides methods for season-based recipe recommendations.
 */
public class SeasonInteractor implements SeasonInputBoundary {
    private final SeasonOutputBoundary seasonpresenter;
    private final InMemoryDateInfoDao inMemoryDateInfoDao;
    private final SpoonacularDao spoonacularDao;
    private final UserPreferences nullPreferences = new UserPreferences(0, false,
            false, new String[0]);

    /**
     * Constructs a SeasonInteractor instance.
     *
     * @param seasonpresenter     The output boundary for presenting season information.
     * @param inMemoryDateInfoDao The DAO for retrieving date-related information.
     * @param spoonacularDao      The DAO for interacting with the recipe database.
     */
    public SeasonInteractor(SeasonOutputBoundary seasonpresenter, InMemoryDateInfoDao inMemoryDateInfoDao,
                            SpoonacularDao spoonacularDao) {
        this.seasonpresenter = seasonpresenter;
        this.inMemoryDateInfoDao = inMemoryDateInfoDao;
        this.spoonacularDao = spoonacularDao;
    }

    @Override
    public void returnTomain() {
        seasonpresenter.returnTomain();
    }

    @Override
    public String getDate() {
        return seasonpresenter.getDate(inMemoryDateInfoDao.getDate());
    }

    @Override
    public String getSeason() {
        return seasonpresenter.getSeason(inMemoryDateInfoDao.getSeason());
    }

    @Override
    public ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo) {
        final ArrayList<Recipe> recipes;
        if (userInfo) {
            recipes = spoonacularDao.getRecipesFromQuery(getSeason(), userPreferences);
        }
        else {
            recipes = spoonacularDao.getRecipesFromQuery(getSeason(), nullPreferences);
        }
        return recipes;
    }
}
