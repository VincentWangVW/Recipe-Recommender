package use_case.recommend_holiday;

import data_access.InMemoryDateInfoDao;
import data_access.SpoonacularDao;
import entity.Recipe;
import entity.UserPreferences;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HolidayInteractorTest {

    private TestHolidayOutputBoundary holidayPresenter;
    private TestInMemoryDateInfoDao inMemoryDAO;
    private HolidayInteractor holidayInteractor;
    private SpoonacularDao spoonacularDAO;


    @Before
    public void setUp() {
        holidayPresenter = new TestHolidayOutputBoundary();
        inMemoryDAO = new TestInMemoryDateInfoDao();
        spoonacularDAO = new SpoonacularDao();
        holidayInteractor = new HolidayInteractor(holidayPresenter, inMemoryDAO, spoonacularDAO);
    }

    @Test
    public void testReturnToMain() {
        holidayInteractor.returnTomain();
        assertTrue(holidayPresenter.returnToMainCalled);
    }

    @Test
    public void testGetHoliday() {
        String expectedHoliday = "Christmas";
        inMemoryDAO.setHoliday(expectedHoliday);
        holidayPresenter.setHoliday(expectedHoliday);

        String actualHoliday = holidayInteractor.getHoliday();
        assertEquals(expectedHoliday, actualHoliday);
    }
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