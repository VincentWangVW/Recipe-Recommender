package use_case.mainwindow;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for MainInteractor.
 * This class tests the methods in the MainInteractor class.
 * The methods tested are switchToIngredientsView, switchToUserInfoView, switchToDateView, and switchToRecipeView.
 */
public class MainInteractorTest {
    private TestMainOutputBoundary mainOutputBoundary;
    private MainInteractor mainInteractor;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() {
        mainOutputBoundary = new TestMainOutputBoundary();
        mainInteractor = new MainInteractor(mainOutputBoundary);
    }

    /**
     * Test the switchToIngredientsView method.
     */
    @Test
    public void testSwitchToIngredientsView() {
        mainInteractor.switchToIngredientsView();
        assertTrue(mainOutputBoundary.ingredientsViewSwitched);
    }

    /**
     * Test the switchToUserInfoView method.
     */
    @Test
    public void testSwitchToUserInfoView() {
        mainInteractor.switchToUserInfoView();
        assertTrue(mainOutputBoundary.userInfoViewSwitched);
    }

    /**
     * Test the switchToDateView method.
     */
    @Test
    public void testSwitchToDateView() {
        mainInteractor.switchToDateView();
        assertTrue(mainOutputBoundary.dateViewSwitched);
    }

    /**
     * Test the switchToRecipeView method.
     */
    @Test
    public void testSwitchToRecipeView() {
        mainInteractor.switchToRecipeView();
        assertTrue(mainOutputBoundary.recipeViewSwitched);
    }

    /**
     * TestMainOutputBoundary class for testing MainInteractor.
     */
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
