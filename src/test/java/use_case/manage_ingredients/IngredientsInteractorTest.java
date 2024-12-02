package use_case.manage_ingredients;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class IngredientsInteractorTest {

    private TestIngredientsOutputBoundary outputBoundary;
    private IngredientsInteractor ingredientsInteractor;

    @Before
    public void setUp() {
        outputBoundary = new TestIngredientsOutputBoundary();
        ingredientsInteractor = new IngredientsInteractor(outputBoundary);
    }

    @Test
    public void testAddIngredient() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.contains("Tomato - 5"));
    }

    @Test
    public void testDeleteIngredient() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.deleteIngredient("Tomato");
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertFalse(ingredients.contains("Tomato - 5"));
    }

    @Test
    public void testChangeIngredientAmount() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", 3);
        assertEquals(8, newAmount);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.contains("Tomato - 8"));
    }

    @Test
    public void testChangeIngredientAmountToRemove() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        int newAmount = ingredientsInteractor.changeIngredientAmount("Tomato", -5);
        assertEquals(0, newAmount);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        System.out.println(ingredients);
        assertTrue(ingredients.contains("Tomato - 0"));
    }

    @Test
    public void testGetIngredients() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Potato", 3);
        List<String> ingredients = ingredientsInteractor.getIngredients();
        assertTrue(ingredients.contains("Tomato - 5"));
        assertTrue(ingredients.contains("Potato - 3"));
    }

    @Test
    public void testGetIngredientsArray() {
        ingredientsInteractor.addIngredient("Tomato", 5);
        ingredientsInteractor.addIngredient("Potato", 3);
        List<String> ingredients = ingredientsInteractor.getIngredientsArray();
        assertTrue(ingredients.contains("Tomato"));
        assertTrue(ingredients.contains("Potato"));
    }

    @Test
    public void testReturnToMain() {
        ingredientsInteractor.return_to_main();
        assertTrue(outputBoundary.mainReturned);
    }

    private static class TestIngredientsOutputBoundary implements IngredientsIOutputBoundary {
        boolean mainReturned = false;

        @Override
        public void return_to_main() {
            mainReturned = true;
        }
    }
}