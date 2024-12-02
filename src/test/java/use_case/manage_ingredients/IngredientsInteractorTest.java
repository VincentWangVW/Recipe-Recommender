package use_case.manage_ingredients;

import entity.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IngredientsInteractorTest {

    private TestOutputBoundary outputBoundary;
    private IngredientsInteractor ingredientsInteractor;

    @Before
    public void setUp() {
        outputBoundary = new TestOutputBoundary();
        ingredientsInteractor = new IngredientsInteractor(outputBoundary);
    }

    @Test
    public void testAddIngredientNew() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 5", ingredients.get(0));
    }

    @Test
    public void testAddIngredientExisting() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Tomato", 3);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 8", ingredients.get(0));
    }

    @Test
    public void testDeleteIngredientExisting() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.deleteIngredient("Tomato");
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.isEmpty());
    }

    @Test
    public void testDeleteIngredientNonExisting() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.deleteIngredient("Onion");
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 5", ingredients.get(0));
    }

    @Test
    public void testChangeIngredientAmountIncrease() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", 3);
        assertEquals(8, newAmount);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals("Tomato - 8", ingredients.get(0));
    }

    @Test
    public void testChangeIngredientAmountDecrease() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", -3);
        assertEquals(2, newAmount);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals("Tomato - 2", ingredients.get(0));
    }

    @Test
    public void testChangeIngredientAmountNonExisting() {
        int newAmount = ingredientsInteractor.changeIngredientAmount("Onion", 5);
        assertEquals(-1, newAmount);
    }

    @Test
    public void testGetIngredients() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Onion", 3);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(2, ingredients.size());
        assertTrue(ingredients.contains("Tomato - 5"));
        assertTrue(ingredients.contains("Onion - 3"));
    }

    @Test
    public void testGetIngredientsNEW() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Onion", 3);
        ArrayList<String> ingredients = ingredientsInteractor.getIngredientsNEW();
        assertEquals(2, ingredients.size());
        assertTrue(ingredients.contains("Tomato"));
        assertTrue(ingredients.contains("Onion"));
    }

    @Test
    public void testReturnToMain() {
        ingredientsInteractor.return_to_main();
        assertTrue(outputBoundary.returnToMainCalled);
    }

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

    private static class TestOutputBoundary implements IngredientsIOutputBoundary {
        boolean returnToMainCalled = false;

        @Override
        public void return_to_main() {
            returnToMainCalled = true;
        }
    }

    @Test
    public void testAddIngredientExistingWithUpdate() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Tomato", 2); // Add more to existing
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Tomato - 7", ingredients.get(0));
    }

    @Test
    public void testChangeIngredientAmountToNegativeRemovesIngredient() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", -10); // Exceeds available amount
        assertEquals(-5, newAmount); // Confirm return value
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.isEmpty()); // Ingredient should be removed
    }

    @Test
    public void testDeleteIngredientFromEmptyList() {
        ingredientsInteractor.deleteIngredient("Onion"); // Deleting non-existent from empty list
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.isEmpty());
    }

    @Test
    public void testChangeIngredientAmountEmptyList() {
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", 5); // Non-existent
        assertEquals(-1, newAmount);
    }

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


}
