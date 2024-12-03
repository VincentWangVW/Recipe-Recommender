package use_case.recommend_recipes;

import data_access.InMemoryDateInfoDAO;
import data_access.SpoonacularDAO;
import org.junit.Before;
import org.junit.Test;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_holiday.HolidayOutputBoundary;

import static org.junit.Assert.*;

public class RecipesInteractorTest {

    private TestRecipesOutputBoundary recipesPresenter;
    private TestHolidayInteractor holidayInteractor;
    private RecipesInteractor recipesInteractor;

    @Before
    public void setUp() {
        recipesPresenter = new TestRecipesOutputBoundary();
        holidayInteractor = new TestHolidayInteractor(null, null, null);
        recipesInteractor = new RecipesInteractor(recipesPresenter, holidayInteractor);
    }

    @Test
    public void testReturnToMain() {
        recipesInteractor.returnTomain();
        assertTrue(recipesPresenter.returnToMainCalled);
    }

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

    @Test
    public void testGetHoliday() {
        String expectedHoliday = "Christmas";
        holidayInteractor.setHoliday(expectedHoliday);

        String actualHoliday = recipesInteractor.getHoliday();
        assertEquals(expectedHoliday, actualHoliday);
    }

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

    private static class TestHolidayInteractor extends HolidayInteractor {
        private String holiday;

        public TestHolidayInteractor(HolidayOutputBoundary holidaypresenter, InMemoryDateInfoDAO inMemoryDateInfoDAO, SpoonacularDAO spoonacularDAO) {
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