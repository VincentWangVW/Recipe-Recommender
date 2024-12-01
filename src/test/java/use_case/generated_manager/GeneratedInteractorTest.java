package use_case.generated_manager;

import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_season.SeasonInteractor;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GeneratedInteractorTest {

    private TestGeneratedOutputBoundary outputBoundary;
    private TestIngredientsInteractor ingredientsInteractor;
    private TestSeasonInteractor seasonInteractor;
    private TestHolidayInteractor holidayInteractor;
    private UserPreferences userPreferences;
    private GeneratedInteractor generatedInteractor;
    private TestSpoonacularDAO spoonacularDAO;

    @Before
    public void setUp() {
        outputBoundary = new TestGeneratedOutputBoundary();
        ingredientsInteractor = new TestIngredientsInteractor();
        seasonInteractor = new TestSeasonInteractor();
        holidayInteractor = new TestHolidayInteractor();
        userPreferences = new UserPreferences(0, false, false, new String[0]);
        spoonacularDAO = new TestSpoonacularDAO();
        generatedInteractor = new GeneratedInteractor(outputBoundary, ingredientsInteractor, seasonInteractor, holidayInteractor, userPreferences);
    }

    @Test
    public void testReturnToMain() {
        generatedInteractor.return_to_main();
        assertTrue(outputBoundary.returnToMainCalled);
    }

    @Test
    public void testGenerateRecipes() throws IOException {
        ArrayList<Recipe> expectedRecipes = new ArrayList<>();
        outputBoundary.setSelectedType("Ingredients");
        outputBoundary.setUserInfo(true);
        ingredientsInteractor.setIngredients(new ArrayList<>());

        spoonacularDAO.setExpectedRecipes(expectedRecipes);

        ArrayList<Recipe> actualRecipes = generatedInteractor.generateRecipes();
        assertEquals(expectedRecipes, actualRecipes);
    }

    @Test
    public void testGetHoliday() {
        String expectedHoliday = "Christmas";
        holidayInteractor.setHoliday(expectedHoliday);

        String actualHoliday = generatedInteractor.getHoliday();
        assertEquals(expectedHoliday, actualHoliday);
    }

    private static class TestGeneratedOutputBoundary implements GeneratedOutputBoundary {
        boolean returnToMainCalled = false;
        private String selectedType;
        private boolean userInfo;
        private String custom;

        @Override
        public void return_to_main() {
            returnToMainCalled = true;
        }

        @Override
        public ViewManagerModel getViewManagerModel() {
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            viewManagerModel.setSelectedType(selectedType);
            viewManagerModel.setUserInfo(userInfo);
            viewManagerModel.setCustom(custom);
            return viewManagerModel;
        }

        public void setSelectedType(String selectedType) {
            this.selectedType = selectedType;
        }

        public void setUserInfo(boolean userInfo) {
            this.userInfo = userInfo;
        }

        public void setCustom(String custom) {
            this.custom = custom;
        }
    }

    private static class TestIngredientsInteractor extends IngredientsInteractor {
        private ArrayList<String> ingredients;

        public TestIngredientsInteractor() {
            super(null);
        }

        @Override
        public ArrayList<String> getIngredientsNEW() {
            return ingredients;
        }

        public void setIngredients(ArrayList<String> ingredients) {
            this.ingredients = ingredients;
        }
    }

    private static class TestSeasonInteractor extends SeasonInteractor {
        private String season;

        public TestSeasonInteractor() {
            super(null, null);
        }

        @Override
        public String getSeason() {
            return season;
        }

        public void setSeason(String season) {
            this.season = season;
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

    private static class TestSpoonacularDAO extends SpoonacularDAO {
        private ArrayList<Recipe> expectedRecipes;

        public void setExpectedRecipes(ArrayList<Recipe> expectedRecipes) {
            this.expectedRecipes = expectedRecipes;
        }

        @Override
        public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences) {
            return expectedRecipes;
        }
    }
}