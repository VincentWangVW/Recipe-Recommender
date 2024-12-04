package use_case.generated_manager;

import entity.Recipe;
import entity.UserPreferences;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_custom.CustomSearchInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_season.SeasonInteractor;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for GeneratedInteractor.
 * This class tests the methods in the GeneratedInteractor class.
 * The methods tested are returnToMain, generateRecipes, getGenerationType, getHoliday, and getRecipesFromCustom.
 */
public class GeneratedInteractorTest {

    private TestGeneratedOutputBoundary outputBoundary;
    private TestIngredientsInteractor ingredientsInteractor;
    private TestSeasonInteractor seasonInteractor;
    private TestHolidayInteractor holidayInteractor;
    private TestCustomSearchInteractor customSearchInteractor;
    private UserPreferences userPreferences;
    private GeneratedInteractor generatedInteractor;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() {
        outputBoundary = new TestGeneratedOutputBoundary();
        ingredientsInteractor = new TestIngredientsInteractor();
        seasonInteractor = new TestSeasonInteractor();
        holidayInteractor = new TestHolidayInteractor();
        customSearchInteractor = new TestCustomSearchInteractor();
        userPreferences = new UserPreferences(5, true, true, new String[]{"nuts"});

        generatedInteractor = new GeneratedInteractor(outputBoundary, ingredientsInteractor, seasonInteractor,
                holidayInteractor, customSearchInteractor, userPreferences);
    }

    /**
     * Test the returnToMain method.
     */
    @Test
    public void testReturnToMain() {
        generatedInteractor.returnTomain();
        assertTrue(outputBoundary.returnToMainCalled);
    }

    /**
     * Test the generateRecipes method.
     */
    @Test
    public void testGenerateRecipesWithIngredients() {
        outputBoundary.viewManagerModel.setSelectedType("Ingredients");
        outputBoundary.viewManagerModel.setUserInfo(true);

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(ingredientsInteractor.getRecipesCalled);
    }

    /**
     * Test the generateRecipes method.
     */
    @Test
    public void testGenerateRecipesWithSeason() {
        outputBoundary.viewManagerModel.setSelectedType("Season");
        outputBoundary.viewManagerModel.setUserInfo(true);

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(seasonInteractor.getRecipesCalled);
    }

    /**
     * Test the generateRecipes method.
     */
    @Test
    public void testGenerateRecipesWithHoliday() {
        outputBoundary.viewManagerModel.setSelectedType("Holiday");
        outputBoundary.viewManagerModel.setUserInfo(false);

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(holidayInteractor.getRecipesCalled);
    }

    /**
     * Test the generateRecipes method.
     */
    @Test
    public void testGenerateRecipesWithCustom() {
        outputBoundary.viewManagerModel.setSelectedType("Custom");
        outputBoundary.viewManagerModel.setUserInfo(true);
        outputBoundary.viewManagerModel.setCustom("custom");

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(customSearchInteractor.getRecipesCalled);
    }

    /**
     * Test the generateRecipes method with no type selected.
     */
    @Test
    public void testGetGenerationType() {
        outputBoundary.viewManagerModel.setSelectedType("Ingredients");
        String type = generatedInteractor.getGenerationType();
        assertEquals("Ingredients", type);
    }

    /**
     * Test the generateRecipes method with no type selected.
     */
    @Test
    public void testGenerateRecipesWithInvalidType() {
        outputBoundary.viewManagerModel.setSelectedType("InvalidType");
        outputBoundary.viewManagerModel.setUserInfo(true);

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNull(recipes);
    }

    /**
     * Test the getHoliday method.
     */
    @Test
    public void testGetHoliday() {
        holidayInteractor.setHoliday("Christmas");
        String holiday = generatedInteractor.getHoliday();
        assertEquals("Christmas", holiday);
    }

    /**
     * Test the getRecipesFromCustom method.
     */
    private static class TestGeneratedOutputBoundary implements GeneratedOutputBoundary {
        boolean returnToMainCalled = false;
        TestViewManagerModel viewManagerModel = new TestViewManagerModel();

        @Override
        public void returnTomain() {
            returnToMainCalled = true;
        }

        @Override
        public ViewManagerModel getViewManagerModel() {
            return viewManagerModel;
        }
    }

    /**
     * Test class for ViewManagerModel.
     */
    private static class TestViewManagerModel extends ViewManagerModel {
        private String selectedType;
        private boolean userInfo;
        private String custom;

        @Override
        public String getSelectedType() {
            return selectedType;
        }

        public void setSelectedType(String selectedType) {
            this.selectedType = selectedType;
        }

        @Override
        public boolean isUserInfo() {
            return userInfo;
        }

        public void setUserInfo(boolean userInfo) {
            this.userInfo = userInfo;
        }

        @Override
        public String getCustom() {
            return custom;
        }

        public void setCustom(String custom) {
            this.custom = custom;
        }
    }

    /**
     * Test class for IngredientsInteractor.
     */
    private static class TestIngredientsInteractor extends IngredientsInteractor {
        private boolean getRecipesCalled = false;

        public TestIngredientsInteractor() {
            super(null);
        }

        @Override
        public ArrayList<String> getIngredientsArray() {
            return new ArrayList<>();
        }

        @Override
        public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return new ArrayList<>();
        }
    }

    /**
     * Test class for SeasonInteractor.
     */
    private static class TestSeasonInteractor extends SeasonInteractor {
        private boolean getRecipesCalled = false;

        public TestSeasonInteractor() {
            super(null, null, null);
        }

        @Override
        public ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return new ArrayList<>();
        }
    }

    /**
     * Test class for HolidayInteractor.
     */
    private static class TestHolidayInteractor extends HolidayInteractor {
        private boolean getRecipesCalled = false;
        private String holiday;

        public TestHolidayInteractor() {
            super(null, null, null);
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }

        @Override
        public ArrayList<Recipe> getRecipesFromHoliday(UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return new ArrayList<>();
        }

        @Override
        public String getHoliday() {
            return holiday;
        }
    }

    /**
     * Test class for CustomSearchInteractor.
     */
    private static class TestCustomSearchInteractor extends CustomSearchInteractor {
        private boolean getRecipesCalled = false;

        @Override
        public ArrayList<Recipe> getRecipesFromCustom(String custom, UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return new ArrayList<>();
        }
    }
}
