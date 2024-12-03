package use_case.recommend_holiday;

import java.util.ArrayList;

import data_access.InMemoryDateInfoDAO;
import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;

/**
 * The HolidayInteractor class implements the HolidayInputBoundary interface and handles the logic
 * related to holiday-specific recipe recommendations. It interacts with the data access layer
 * and the output boundary to manage holiday-related recipe retrieval.
 */
public class HolidayInteractor implements HolidayInputBoundary {
    public final HolidayOutputBoundary holidaypresenter;
    private final InMemoryDateInfoDAO inMemoryDateInfoDAO;
    private final SpoonacularDAO spoonacularDAO;
    private final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

    /**
     * Constructs a new HolidayInteractor instance.
     *
     * @param holidaypresenter an instance of HolidayOutputBoundary to interact with the output layer
     * @param inMemoryDateInfoDAO an instance of InMemoryDateInfoDAO to manage holiday data
     * @param spoonacularDAO an instance of SpoonacularDAO to retrieve recipes from the Spoonacular API
     */
    public HolidayInteractor(HolidayOutputBoundary holidaypresenter, InMemoryDateInfoDAO inMemoryDateInfoDAO,
                             SpoonacularDAO spoonacularDAO) {
        this.holidaypresenter = holidaypresenter;
        this.inMemoryDateInfoDAO = inMemoryDateInfoDAO;
        this.spoonacularDAO = spoonacularDAO;
    }

    /**
     * Returns to the main screen or main menu by invoking the appropriate method on the output boundary.
     */
    @Override
    public void return_to_main() {
        holidaypresenter.return_to_main();
    }

    /**
     * Retrieves the current holiday or selected holiday.
     *
     * @return a String representing the current or selected holiday
     */
    @Override
    public String getHoliday() {
        return holidaypresenter.getHoliday(inMemoryDateInfoDAO.get_holiday());
    }

    /**
     * Retrieves a list of recipes based on the holiday and user preferences.
     * If user information is provided, it customizes the recipe search according to the user's preferences.
     *
     * @param userPreferences the user preferences to customize the search (if applicable)
     * @param userInfo a flag indicating whether user preferences should be applied
     * @return an ArrayList of Recipe objects that match the holiday and user preferences
     */
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
