package use_case.recommend_holiday;

import data_access.InMemoryDAO;
import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;

public class HolidayInteractor implements HolidayInputBoundary {
    public final HolidayOutputBoundary holidaypresenter;
    private final InMemoryDAO inMemoryDAO;
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    public HolidayInteractor(HolidayOutputBoundary holidaypresenter, InMemoryDAO inMemoryDAO) {
        this.holidaypresenter = holidaypresenter;
        this.inMemoryDAO = inMemoryDAO;
        this.spoonacularDAO = new SpoonacularDAO();
    }

    @Override
    public void return_to_main() {
        holidaypresenter.return_to_main();
    }

    @Override
    public String getHoliday() {
        return holidaypresenter.getHoliday(inMemoryDAO.get_holiday());
    }

    @Override
    public ArrayList<Recipe> getRecipeFromHoliday(UserPreferences userPreferences, boolean userInfo) {
        if (userInfo) {
            return spoonacularDAO.getRecipesFromQuery(getHoliday(), userPreferences);
        }
        else {
            return spoonacularDAO.getRecipesFromQuery(getHoliday(), nullPreferences);
        }
    }
}
