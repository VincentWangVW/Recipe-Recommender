package use_case.recommend_recipes;

import org.junit.Before;
import org.junit.Test;
import use_case.recommend_holiday.HolidayInteractor;

import static org.junit.Assert.*;

public class RecipesInteractorTest {

    private TestRecipesOutputBoundary recipesPresenter;
    private TestHolidayInteractor holidayInteractor;
    private RecipesInteractor recipesInteractor;

    @Before
    public void setUp() {
        recipesPresenter = new TestRecipesOutputBoundary();
        holidayInteractor = new TestHolidayInteractor();
        recipesInteractor = new RecipesInteractor(recipesPresenter, holidayInteractor);
    }

    @Test
    public void testReturnToMain() {
        recipesInteractor.return_to_main();
        assertTrue(recipesPresenter.returnToMainCalled);
    }

    @Test
    public void testGoToGenerated() {
        String selectedType = "Type";
        boolean userInfo = true;
        String custom = "Custom";
        recipesInteractor.go_to_generated(selectedType, userInfo, custom);
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
        public void return_to_main() {
            returnToMainCalled = true;
        }

        @Override
        public void go_to_generated(String selectedType, boolean userInfo, String custom) {
            this.selectedType = selectedType;
            this.userInfo = userInfo;
            this.custom = custom;
        }
    }

    private static class TestHolidayInteractor extends HolidayInteractor {
        private String holiday;

        public TestHolidayInteractor() {
            super(null, null);
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