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
        mainInteractor.switchToIngredientsView();
        assertTrue(mainOutputBoundary.ingredientsViewSwitched);
    }

    @Test
    public void testSwitchToUserInfoView() {
        mainInteractor.switchToUserInfoView();
        assertTrue(mainOutputBoundary.userInfoViewSwitched);
    }

    @Test
    public void testSwitchToDateView() {
        mainInteractor.switchToDateView();
        assertTrue(mainOutputBoundary.dateViewSwitched);
    }

    @Test
    public void testSwitchToRecipeView() {
        mainInteractor.switchToRecipeView();
        assertTrue(mainOutputBoundary.recipeViewSwitched);
    }

    private static class TestMainOutputBoundary implements MainOutputBoundary {
        boolean ingredientsViewSwitched = false;
        boolean userInfoViewSwitched = false;
        boolean dateViewSwitched = false;
        boolean recipeViewSwitched = false;

        @Override
        public void switchToIngredientsView() {
            ingredientsViewSwitched = true;
        }

        @Override
        public void switchToUserInfoView() {
            userInfoViewSwitched = true;
        }

        @Override
        public void switchToDateView() {
            dateViewSwitched = true;
        }

        @Override
        public void switchToRecipeView() {
            recipeViewSwitched = true;
        }
    }
}