package use_case.recommend_holiday;

import java.util.ArrayList;

import data_access.InMemoryDateInfoDAO;
import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

/**
 * The HolidayInteractor class implements the HolidayInputBoundary interface
 * and provides methods for holiday-based recipe recommendations.
 */
public class HolidayInteractor implements HolidayInputBoundary {
    private final HolidayOutputBoundary holidaypresenter;
    private final InMemoryDateInfoDAO inMemoryDateInfoDao;
    private final SpoonacularDAO spoonacularDao;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    /**
     * Constructs a HolidayInteractor instance.
     *
     * @param holidaypresenter     The output boundary for presenting holiday information.
     * @param inMemoryDateInfoDao  The DAO for retrieving date-related information.
     * @param spoonacularDao       The DAO for interacting with the recipe database.
     */
    public HolidayInteractor(HolidayOutputBoundary holidaypresenter, InMemoryDateInfoDAO inMemoryDateInfoDao,
                             SpoonacularDAO spoonacularDao) {
        this.holidaypresenter = holidaypresenter;
        this.inMemoryDateInfoDao = inMemoryDateInfoDao;
        this.spoonacularDao = spoonacularDao;
    }

    @Override
    public void returnTomain() {
        holidaypresenter.returnTomain();
    }

    @Override
    public String getHoliday() {
        return holidaypresenter.getHoliday(inMemoryDateInfoDao.getHoliday());
    }

    @Override
    public ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo) {
        final ArrayList<Recipe> recipes;
        if (userInfo) {
            recipes = spoonacularDao.getRecipesFromQuery(getHoliday(), userPreferences);
        }
        else {
            recipes = spoonacularDao.getRecipesFromQuery(getHoliday(), nullPreferences);
        }
        return recipes;
    }
}
