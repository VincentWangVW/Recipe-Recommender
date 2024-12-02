package use_case.recommend_holiday;

import data_access.InMemoryDateInfoDAO;
import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;

public class HolidayInteractor implements HolidayInputBoundary {
    public final HolidayOutputBoundary holidaypresenter;
    private final InMemoryDateInfoDAO inMemoryDateInfoDAO;
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    public HolidayInteractor(HolidayOutputBoundary holidaypresenter, InMemoryDateInfoDAO inMemoryDateInfoDAO,
                             SpoonacularDAO spoonacularDAO) {
        this.holidaypresenter = holidaypresenter;
        this.inMemoryDateInfoDAO = inMemoryDateInfoDAO;
        this.spoonacularDAO = spoonacularDAO;
    }

    @Override
    public void return_to_main() {
        holidaypresenter.return_to_main();
    }

    @Override
    public String getHoliday() {
        return holidaypresenter.getHoliday(inMemoryDateInfoDAO.get_holiday());
    }

    @Override
    public ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo) {
        if (userInfo) {
            return spoonacularDAO.getRecipesFromQuery(getHoliday(), userPreferences);
        }
        else {
            return spoonacularDAO.getRecipesFromQuery(getHoliday(), nullPreferences);
        }
    }
}
