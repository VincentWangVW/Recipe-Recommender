package use_case.recommend_holiday;

import data_access.InMemoryDateInfoDao;
import data_access.SpoonacularDao;
import entity.Recipe;
import entity.UserPreferences;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for HolidayInteractor.
 * This class tests the methods in the HolidayInteractor class.
 * The methods tested are returnToMain, getHoliday, getRecipesFromHoliday.
 */
public class HolidayInteractorTest {
    private TestHolidayOutputBoundary holidayPresenter;
    private TestInMemoryDateInfoDao inMemoryDAO;
    private HolidayInteractor holidayInteractor;
    private SpoonacularDao spoonacularDAO;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() {
        holidayPresenter = new TestHolidayOutputBoundary();
        inMemoryDAO = new TestInMemoryDateInfoDao();
        spoonacularDAO = new SpoonacularDao();
        holidayInteractor = new HolidayInteractor(holidayPresenter, inMemoryDAO, spoonacularDAO);
    }

    /**
     * Test the returnToMain method.
     */
    @Test
    public void testReturnToMain() {
        holidayInteractor.returnTomain();
        assertTrue(holidayPresenter.returnToMainCalled);
    }

    /**
     * Test the getHoliday method.
     */
    @Test
    public void testGetHoliday() {
        String expectedHoliday = "Christmas";
        inMemoryDAO.setHoliday(expectedHoliday);
        holidayPresenter.setHoliday(expectedHoliday);

        String actualHoliday = holidayInteractor.getHoliday();
        assertEquals(expectedHoliday, actualHoliday);
    }

    /**
     * Test the getRecipesFromHoliday method.
     */
    @Test public void testGetRecipesFromHolidayTrue() {
        UserPreferences userPreferences = new UserPreferences(10, true, false,
                new String[0]);
        String expectedHoliday = "Christmas";
        inMemoryDAO.setHoliday(expectedHoliday);
        holidayPresenter.setHoliday(expectedHoliday);
        ArrayList<Recipe> actual = holidayInteractor.getRecipesFromHoliday(userPreferences,true);
        ArrayList<Recipe> empty = new ArrayList<>();
        assertNotEquals(empty, actual);
    }

    /**
     * Test the getRecipesFromHoliday method.
     */
    @Test public void testGetRecipesFromHolidayFalse() {

        UserPreferences userPreferences = new UserPreferences(0, false, false,
                new String[0]);
        String expectedHoliday = "Christmas";
        inMemoryDAO.setHoliday(expectedHoliday);
        holidayPresenter.setHoliday(expectedHoliday);
        ArrayList<Recipe> actual = holidayInteractor.getRecipesFromHoliday(userPreferences,false);
        ArrayList<Recipe> empty = new ArrayList<>();
        assertNotEquals(empty, actual);
    }

    /**
     * Test the getRecipesFromHoliday method.
     */
    private static class TestHolidayOutputBoundary implements HolidayOutputBoundary {
        boolean returnToMainCalled = false;
        private String holiday;

        @Override
        public void returnTomain() {
            returnToMainCalled = true;
        }

        @Override
        public String getHoliday(String holiday) {
            return this.holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }
    }

    /**
     * Test class for InMemoryDateInfoDao.
     */
    private static class TestInMemoryDateInfoDao extends InMemoryDateInfoDao {
        private String holiday;

        @Override
        public String getHoliday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }
    }
}
