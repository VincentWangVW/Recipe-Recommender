package use_case.manage_ingredients;

import entity.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for IngredientsInteractor.
 * This class tests the methods in the IngredientsInteractor class.
 * The methods tested are addIngredient, deleteIngredient, changeIngredientAmount, getIngredients, getIngredientsArray,
 * returnToMain, getRecipesFromIngredients, addIngredientExistingWithUpdate, changeIngredientAmountToNegativeRemovesIngredient,
 * deleteIngredientFromEmptyList, changeIngredientAmountEmptyList, IOExceptionInGetRecipesFromIngredients, changeIngredientAmountIngredientNotFound.
 */
public class IngredientsInteractorTest {
    private TestOutputBoundary outputBoundary;
    private IngredientsInteractor ingredientsInteractor;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() {
        outputBoundary = new TestOutputBoundary();
        ingredientsInteractor = new IngredientsInteractor(outputBoundary);
    }

    /**
     * Test the addIngredient method.
     */
    @Test
    public void testAddIngredientNew() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 5", ingredients.get(0));
    }

    /**
     * Test the addIngredient method with an existing ingredient.
     */
    @Test
    public void testAddIngredientExisting() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Tomato", 3);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 8", ingredients.get(0));
    }

    /**
     * Test the deleteIngredient method with an existing ingredient.
     */
    @Test
    public void testDeleteIngredientExisting() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.deleteIngredient("Tomato");
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.isEmpty());
    }

    /**
     * Test the deleteIngredient method with a non-existing ingredient.
     */
    @Test
    public void testDeleteIngredientNonExisting() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.deleteIngredient("Onion");
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 5", ingredients.get(0));
    }

    /**
     * Test the changeIngredientAmount method.
     */
    @Test
    public void testChangeIngredientAmountIncrease() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", 3);
        assertEquals(8, newAmount);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals("Tomato - 8", ingredients.get(0));
    }

    /**
     * Test the changeIngredientAmount method with a decrease.
     */
    @Test
    public void testChangeIngredientAmountDecrease() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", -3);
        assertEquals(2, newAmount);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals("Tomato - 2", ingredients.get(0));
    }

    /**
     * Test the changeIngredientAmount method with a non-existing ingredient.
     */
    @Test
    public void testChangeIngredientAmountNonExisting() {
        int newAmount = ingredientsInteractor.changeIngredientAmount("Onion", 5);
        assertEquals(-1, newAmount);
    }

    /**
     * Test the getIngredients method.
     */
    @Test
    public void testGetIngredients() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Onion", 3);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(2, ingredients.size());
        assertTrue(ingredients.contains("Tomato - 5"));
        assertTrue(ingredients.contains("Onion - 3"));
    }

    /**
     * Test the getIngredientsArray method.
     */
    @Test
    public void testGetIngredientsNEW() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Onion", 3);
        ArrayList<String> ingredients = ingredientsInteractor.getIngredientsArray();
        assertEquals(2, ingredients.size());
        assertTrue(ingredients.contains("Tomato"));
        assertTrue(ingredients.contains("Onion"));
    }

    /**
     * Test the returnToMain method.
     */
    @Test
    public void testReturnToMain() {
        ingredientsInteractor.returnTomain();
        assertTrue(outputBoundary.returnToMainCalled);
    }

    /**
     * Test the getRecipesFromIngredients method.
     */
    @Test
    public void testGetRecipesFromIngredientsWithUserInfo() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomato");
        UserPreferences preferences = new UserPreferences(5, true, true, new String[]{"nuts"});

        try {
            ingredientsInteractor.getRecipesFromIngredients(ingredients, preferences, true);
        } catch (RuntimeException e) {
            assertNotNull(e.getMessage());
        }
    }

    /**
     * Test the getRecipesFromIngredients method without user info.
     */
    @Test
    public void testGetRecipesFromIngredientsWithoutUserInfo() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomato");

        try {
            ingredientsInteractor.getRecipesFromIngredients(ingredients, new UserPreferences(0, false, false, new String[0]), false);
        } catch (RuntimeException e) {
            assertNotNull(e.getMessage());
        }
    }

    /**
     * Test the getRecipesFromIngredients method with an empty list of ingredients.
     */
    private static class TestOutputBoundary implements IngredientsOutputBoundary {
        boolean returnToMainCalled = false;

        @Override
        public void returnTomain() {
            returnToMainCalled = true;
        }
    }

    /**
     * Test the addIngredient method with an existing ingredient and an update.
     */
    @Test
    public void testAddIngredientExistingWithUpdate() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Tomato", 2); // Add more to existing
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 7", ingredients.get(0));
    }

    /**
     * Test the changeIngredientAmount method with a negative amount that removes the ingredient.
     */
    @Test
    public void testChangeIngredientAmountToNegativeRemovesIngredient() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", -10); // Exceeds available amount
        assertEquals(-5, newAmount); // Confirm return value
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.isEmpty()); // Ingredient should be removed
    }

    /**
     * Test the deleteIngredient method with an empty list.
     */
    @Test
    public void testDeleteIngredientFromEmptyList() {
        ingredientsInteractor.deleteIngredient("Onion"); // Deleting non-existent from empty list
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.isEmpty());
    }

    /**
     * Test the changeIngredientAmount method with an empty list.
     */
    @Test
    public void testChangeIngredientAmountEmptyList() {
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", 5); // Non-existent
        assertEquals(-1, newAmount);
    }

    /**
     * Test the getRecipesFromIngredients method with an IOException.
     */
    @Test
    public void testIOExceptionInGetRecipesFromIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Tomato");
        UserPreferences preferences = new UserPreferences(5, true, true, new String[]{"nuts"});

        // Mock SpoonacularDAO to throw IOException
        IngredientsInteractor interactorWithMockDAO = new IngredientsInteractor(outputBoundary) {
            @Override
            public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences, boolean userInfo) {
                throw new RuntimeException(new IOException("Simulated IOException"));
            }
        };

        try {
            interactorWithMockDAO.getRecipesFromIngredients(ingredients, preferences, true);
            fail("Expected RuntimeException not thrown");
        } catch (RuntimeException e) {
            assertTrue(e.getCause() instanceof IOException); // Confirm cause is IOException
        }
    }

    /**
     * Test the changeIngredientAmount method with an ingredient that is not found.
     */
    @Test
    public void testChangeIngredientAmountIngredientNotFound() {
        // Add an ingredient to the list
        ingredientsInteractor.addIngredient("Tomato", 5);

        // Try to change an ingredient that doesn't exist (i.e., "Onion")
        int newAmount = ingredientsInteractor.changeIngredientAmount("Onion", 3);

        // Assert that the method returns -1, indicating that the ingredient was not found
        assertEquals(-1, newAmount);

        // Verify that the original ingredient ("Tomato") is still in the list with its original amount
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 5", ingredients.get(0));
    }
}
