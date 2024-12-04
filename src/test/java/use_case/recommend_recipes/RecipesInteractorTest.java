package use_case.recommend_recipes;

import data_access.InMemoryDateInfoDao;
import data_access.SpoonacularDao;
import org.junit.Before;
import org.junit.Test;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_holiday.HolidayOutputBoundary;

import static org.junit.Assert.*;

/**
 * Test class for RecipesInteractor.
 * This class tests the methods in the RecipesInteractor class.
 * The methods tested are returnToMain, goToGenerated, and getHoliday.
 */
public class RecipesInteractorTest {
    private TestRecipesOutputBoundary recipesPresenter;
    private TestHolidayInteractor holidayInteractor;
    private RecipesInteractor recipesInteractor;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() {
        recipesPresenter = new TestRecipesOutputBoundary();
        holidayInteractor = new TestHolidayInteractor(null, null, null);
        recipesInteractor = new RecipesInteractor(recipesPresenter, holidayInteractor);
    }

    /**
     * Test the returnToMain method.
     */
    @Test
    public void testReturnToMain() {
        recipesInteractor.returnTomain();
        assertTrue(recipesPresenter.returnToMainCalled);
    }

    /**
     * Test the goToGenerated method.
     */
    @Test
    public void testGoToGenerated() {
        String selectedType = "Type";
        boolean userInfo = true;
        String custom = "Custom";
        recipesInteractor.goToGenerated(selectedType, userInfo, custom);
        assertEquals(selectedType, recipesPresenter.selectedType);
        assertEquals(userInfo, recipesPresenter.userInfo);
        assertEquals(custom, recipesPresenter.custom);
    }

    /**
     * Test the getHoliday method.
     */
    @Test
    public void testGetHoliday() {
        String expectedHoliday = "Christmas";
        holidayInteractor.setHoliday(expectedHoliday);

        String actualHoliday = recipesInteractor.getHoliday();
        assertEquals(expectedHoliday, actualHoliday);
    }

    /**
     * TestRecipesOutputBoundary is a test class that implements the RecipesOutputBoundary interface.
     * This class is used to test the RecipesInteractor class.
     */
    private static class TestRecipesOutputBoundary implements RecipesOutputBoundary {
        boolean returnToMainCalled = false;
        String selectedType;
        boolean userInfo;
        String custom;

        @Override
        public void returnTomain() {
            returnToMainCalled = true;
        }

        @Override
        public void goToGenerated(String selectedType, boolean userInfo, String custom) {
            this.selectedType = selectedType;
            this.userInfo = userInfo;
            this.custom = custom;
        }
    }

    /**
     * TestHolidayInteractor is a test class that extends the HolidayInteractor class.
     * This class is used to test the RecipesInteractor class.
     */
    private static class TestHolidayInteractor extends HolidayInteractor {
        private String holiday;

        public TestHolidayInteractor(HolidayOutputBoundary holidaypresenter, InMemoryDateInfoDao inMemoryDateInfoDAO, SpoonacularDao spoonacularDAO) {
            super(holidaypresenter, inMemoryDateInfoDAO, spoonacularDAO);
        }

        @Override
        public String getHoliday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }
    }
}
