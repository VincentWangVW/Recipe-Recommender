package use_case.mainwindow;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainInteractorTest {

    private TestMainOutputBoundary mainOutputBoundary;
    private MainInteractor mainInteractor;

    @Before
    public void setUp() {
        mainOutputBoundary = new TestMainOutputBoundary();
        mainInteractor = new MainInteractor(mainOutputBoundary);
    }

    @Test
    public void testSwitchToIngredientsView() {
        mainInteractor.switch_to_IngredientsView();
        assertTrue(mainOutputBoundary.ingredientsViewSwitched);
    }

    @Test
    public void testSwitchToUserInfoView() {
        mainInteractor.switch_to_UserInfoView();
        assertTrue(mainOutputBoundary.userInfoViewSwitched);
    }

    @Test
    public void testSwitchToDateView() {
        mainInteractor.switch_to_DateView();
        assertTrue(mainOutputBoundary.dateViewSwitched);
    }

    @Test
    public void testSwitchToRecipeView() {
        mainInteractor.switch_to_RecipeView();
        assertTrue(mainOutputBoundary.recipeViewSwitched);
    }

    private static class TestMainOutputBoundary implements MainOutputBoundary {
        boolean ingredientsViewSwitched = false;
        boolean userInfoViewSwitched = false;
        boolean dateViewSwitched = false;
        boolean recipeViewSwitched = false;

        @Override
        public void switch_to_IngredientsView() {
            ingredientsViewSwitched = true;
        }

        @Override
        public void switch_to_UserInfoView() {
            userInfoViewSwitched = true;
        }

        @Override
        public void switch_to_DateView() {
            dateViewSwitched = true;
        }

        @Override
        public void switch_to_RecipeView() {
            recipeViewSwitched = true;
        }
    }
}