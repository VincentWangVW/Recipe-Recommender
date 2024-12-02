package use_case.recommend_season;

import data_access.InMemoryDateInfoDAO;
import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;

public class SeasonInteractor implements SeasonInputBoundary {
    public final SeasonOutputBoundary seasonpresenter;
    private final InMemoryDateInfoDAO inMemoryDateInfoDAO;
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    public SeasonInteractor(SeasonOutputBoundary seasonpresenter, InMemoryDateInfoDAO inMemoryDateInfoDAO, SpoonacularDAO spoonacularDAO) {
        this.seasonpresenter = seasonpresenter;
        this.inMemoryDateInfoDAO = inMemoryDateInfoDAO;
        this.spoonacularDAO = spoonacularDAO;
    }

    @Override
    public void return_to_main() {
        seasonpresenter.return_to_main();
    }

    @Override
    public String getDate() {
        return seasonpresenter.getDate(inMemoryDateInfoDAO.get_date());
    }

    @Override
    public String getSeason() {
        return seasonpresenter.getSeason(inMemoryDateInfoDAO.get_season());
    }

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