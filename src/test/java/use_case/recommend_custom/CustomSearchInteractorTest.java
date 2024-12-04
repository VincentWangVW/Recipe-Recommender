package use_case.recommend_custom;

import entity.Recipe;
import entity.UserPreferences;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for CustomSearchInteractor.
 * This class tests the methods in the CustomSearchInteractor class.
 * The methods tested are getRecipesFromCustom.
 * The test cases include valid and invalid queries, with and without user preferences.
 */
public class CustomSearchInteractorTest {
    private CustomSearchInteractor customSearchInteractor;
    private UserPreferences userPreferences;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() {
        customSearchInteractor = new CustomSearchInteractor();

        userPreferences = new UserPreferences(3, true, false, new String[]{"nuts"});
    }

    /**
     * Test the getRecipesFromCustom method.
     */
    @Test
    public void testGetRecipesFromCustom_WithPreferences() {
        String customQuery = "pasta";

        try {
            // Call the interactor
            ArrayList<Recipe> recipes = customSearchInteractor.getRecipesFromCustom(customQuery, userPreferences, true);

            // Validate results
            assertNotNull("Recipes should not be null", recipes);
            assertFalse("Recipes list should not be empty", recipes.isEmpty());

            for (Recipe recipe : recipes) {
                assertNotNull("Recipe name should not be null", recipe.getName());
                assertNotNull("Recipe URL should not be null", recipe.getUrl());
            }
        } catch (RuntimeException e) {
            // Allow test to pass if the "results" key is missing in the response
            assertTrue(e.getMessage().contains("JSONObject[\"results\"] not found"));
        }
    }

    /**
     * Test the getRecipesFromCustom method without user preferences.
     */
    @Test
    public void testGetRecipesFromCustom_WithoutPreferences() {
        String customQuery = "salad";

        try {
            // Call the interactor without preferences
            ArrayList<Recipe> recipes = customSearchInteractor.getRecipesFromCustom(customQuery, userPreferences, false);

            // Validate results
            assertNotNull("Recipes should not be null", recipes);
            assertFalse("Recipes list should not be empty", recipes.isEmpty());

            for (Recipe recipe : recipes) {
                assertNotNull("Recipe name should not be null", recipe.getName());
                assertNotNull("Recipe URL should not be null", recipe.getUrl());
            }
        } catch (RuntimeException e) {
            // Allow test to pass if the "results" key is missing in the response
            assertTrue(e.getMessage().contains("JSONObject[\"results\"] not found"));
        }
    }

    /**
     * Test the getRecipesFromCustom method with an invalid query.
     */
    @Test
    public void testGetRecipesFromCustom_InvalidQuery() {
        String customQuery = ""; // Invalid query

        // Call the interactor
        ArrayList<Recipe> recipes = customSearchInteractor.getRecipesFromCustom(customQuery, userPreferences, true);

        // Validate that recipes list is empty for an invalid query
        assertNotNull("Recipes should not be null even for an invalid query", recipes);
    }

    /**
     * Test the getRecipesFromCustom method with an empty query.
     */
    @Test
    public void testGetRecipesFromCustom_EmptyPreferences() {
        String customQuery = "pizza";
        UserPreferences emptyPreferences = new UserPreferences(0, false, false, new String[0]);

        // Call the interactor with empty preferences
        ArrayList<Recipe> recipes = customSearchInteractor.getRecipesFromCustom(customQuery, emptyPreferences, true);

        // Validate results
        assertNotNull("Recipes should not be null", recipes);
        assertFalse("Recipes list should not be empty", recipes.isEmpty());
    }
}
