package use_case.generated_manager;

import data_access.InMemoryDAO;
import entity.Recipe;
import entity.UserPreferences;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import use_case.manage_ingredients.IngredientsIOutputBoundary;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_custom.CustomSearchInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_holiday.HolidayOutputBoundary;
import use_case.recommend_season.SeasonInteractor;
import use_case.recommend_season.SeasonOutputBoundary;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GeneratedInteractorTest {

    private TestGeneratedOutputBoundary outputBoundary;
    private TestIngredientsInteractor ingredientsInteractor;
    private TestSeasonInteractor seasonInteractor;
    private TestHolidayInteractor holidayInteractor;
    private TestCustomSearchInteractor customSearchInteractor;
    private UserPreferences userPreferences;
    private GeneratedInteractor generatedInteractor;

    @Before
    public void setUp() {
        outputBoundary = new TestGeneratedOutputBoundary();
        ingredientsInteractor = new TestIngredientsInteractor(new IngredientsIOutputBoundary() {
            @Override
            public void return_to_main() {
                // Implement the method
            }
        });
        seasonInteractor = new TestSeasonInteractor(new InMemoryDAO(), new SeasonOutputBoundary() {
            @Override
            public void return_to_main() {
                // Implement the method
            }

            @Override
            public String getDate(String date) {
                return "";
            }

            @Override
            public String getSeason(String season) {
                return "";
            }
        });
        holidayInteractor = new TestHolidayInteractor(new InMemoryDAO(), new HolidayOutputBoundary() {
            @Override
            public void return_to_main() {
                // Implement the method
            }

            @Override
            public String getHoliday(String holiday) {
                return "";
            }
        });
        customSearchInteractor = new TestCustomSearchInteractor();
        userPreferences = new UserPreferences(5, true, true, new String[]{"nuts"});

        generatedInteractor = new GeneratedInteractor(outputBoundary, ingredientsInteractor, seasonInteractor, holidayInteractor, customSearchInteractor, userPreferences);
    }

    @Test
    public void testReturnToMain() {
        generatedInteractor.return_to_main();
        assertTrue(outputBoundary.returnToMainCalled);
    }

    @Test
    public void testGenerateRecipesWithIngredients() {
        outputBoundary.viewManagerModel.setSelectedType("Ingredients");
        outputBoundary.viewManagerModel.setUserInfo(true);
        ingredientsInteractor.setIngredients(new ArrayList<>());
        ingredientsInteractor.setRecipes(new ArrayList<>());

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(ingredientsInteractor.getRecipesCalled);
    }

    @Test
    public void testGenerateRecipesWithSeason() {
        outputBoundary.viewManagerModel.setSelectedType("Season");
        outputBoundary.viewManagerModel.setUserInfo(true);
        seasonInteractor.setRecipes(new ArrayList<>());

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(seasonInteractor.getRecipesCalled);
    }

    @Test
    public void testGenerateRecipesWithHoliday() {
        outputBoundary.viewManagerModel.setSelectedType("Holiday");
        outputBoundary.viewManagerModel.setUserInfo(true);
        holidayInteractor.setRecipes(new ArrayList<>());

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(holidayInteractor.getRecipesCalled);
    }

    @Test
    public void testGenerateRecipesWithCustom() {
        outputBoundary.viewManagerModel.setSelectedType("Custom");
        outputBoundary.viewManagerModel.setUserInfo(true);
        outputBoundary.viewManagerModel.setCustom("custom");
        customSearchInteractor.setRecipes(new ArrayList<>());

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNotNull(recipes);
        assertTrue(customSearchInteractor.getRecipesCalled);
    }

    @Test
    public void testGetGenerationType() {
        outputBoundary.viewManagerModel.setSelectedType("Ingredients");
        String type = generatedInteractor.getGenerationType();
        assertEquals("Ingredients", type);
    }

    @Test
    public void testGenerateRecipesWithInvalidType() {
        outputBoundary.viewManagerModel.setSelectedType("InvalidType");
        outputBoundary.viewManagerModel.setUserInfo(true);

        ArrayList<Recipe> recipes = generatedInteractor.generateRecipes();
        assertNull(recipes);
    }

    @Test
    public void testGetHoliday() {
        holidayInteractor.setHoliday("Christmas");
        String holiday = generatedInteractor.getHoliday();
        assertEquals("Christmas", holiday);
    }

    private static class TestGeneratedOutputBoundary implements GeneratedOutputBoundary {
        boolean returnToMainCalled = false;
        TestViewManagerModel viewManagerModel = new TestViewManagerModel();

        @Override
        public void return_to_main() {
            returnToMainCalled = true;
        }

        @Override
        public ViewManagerModel getViewManagerModel() {
            return viewManagerModel;
        }
    }

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

    private static class TestIngredientsInteractor extends IngredientsInteractor {
        private boolean getRecipesCalled = false;
        private ArrayList<String> ingredients;
        private ArrayList<Recipe> recipes;

        public TestIngredientsInteractor(IngredientsIOutputBoundary outputBoundary) {
            super(outputBoundary);
        }

        public void setIngredients(ArrayList<String> ingredients) {
            this.ingredients = ingredients;
        }

        public void setRecipes(ArrayList<Recipe> recipes) {
            this.recipes = recipes;
        }

        @Override
        public ArrayList<String> getIngredientsNEW() {
            return ingredients;
        }

        @Override
        public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return recipes;
        }
    }

    private static class TestSeasonInteractor extends SeasonInteractor {
        private boolean getRecipesCalled = false;
        private ArrayList<Recipe> recipes;

        public TestSeasonInteractor(InMemoryDAO dao, SeasonOutputBoundary outputBoundary) {
            super(outputBoundary, dao);
        }

        public void setRecipes(ArrayList<Recipe> recipes) {
            this.recipes = recipes;
        }

        @Override
        public ArrayList<Recipe> getRecipesFromSeason(UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return recipes;
        }
    }

    private static class TestHolidayInteractor extends HolidayInteractor {
        private boolean getRecipesCalled = false;
        private ArrayList<Recipe> recipes;
        private String holiday;

        public TestHolidayInteractor(InMemoryDAO dao, HolidayOutputBoundary outputBoundary) {
            super(outputBoundary, dao);
        }

        public void setRecipes(ArrayList<Recipe> recipes) {
            this.recipes = recipes;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }

        @Override
        public ArrayList<Recipe> getRecipeFromHoliday(UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return recipes;
        }

        @Override
        public String getHoliday() {
            return holiday;
        }
    }

    private static class TestCustomSearchInteractor extends CustomSearchInteractor {
        private boolean getRecipesCalled = false;
        private ArrayList<Recipe> recipes;

        public void setRecipes(ArrayList<Recipe> recipes) {
            this.recipes = recipes;
        }

        @Override
        public ArrayList<Recipe> getRecipesFromCustom(String custom, UserPreferences userPreferences, boolean userInfo) {
            getRecipesCalled = true;
            return recipes;
        }
    }
}