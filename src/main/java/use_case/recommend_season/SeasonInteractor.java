package use_case.recommend_season;

import data_access.InMemoryDAO;
import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

import java.io.IOException;
import java.util.ArrayList;

public class SeasonInteractor implements SeasonInputBoundary {
    public final SeasonOutputBoundary seasonpresenter;
    private final InMemoryDAO inMemoryDAO;
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    public SeasonInteractor(SeasonOutputBoundary seasonpresenter, InMemoryDAO inMemoryDAO) {
        this.seasonpresenter = seasonpresenter;
        this.inMemoryDAO = inMemoryDAO;
        this.spoonacularDAO = new SpoonacularDAO();
    }

    @Override
    public void return_to_main() {
        seasonpresenter.return_to_main();
    }

    @Override
    public String getDate() {
        return seasonpresenter.getDate(inMemoryDAO.get_date());
    }

    @Override
    public String getSeason() {
        return seasonpresenter.getSeason(inMemoryDAO.get_season());
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