package interface_adapter.ingredients_manager;

import java.util.List;

import use_case.manage_ingredients.IngredientsInteractor;

/**
 * The controller responsible for managing ingredients through the IngredientsInteractor.
 * This class provides methods to add, delete, update, and retrieve ingredients.
 */
public class IngredientsController {
    private final IngredientsInteractor ingredientsInteractor;

    /**
     * Constructs a new IngredientsController with the provided IngredientsInteractor.
     * This controller delegates the actual ingredient management functionality to the interactor.
     *
     * @param ingredientsInteractor the {@link IngredientsInteractor} used for managing ingredients
     */
    public IngredientsController(IngredientsInteractor ingredientsInteractor) {
        this.ingredientsInteractor = ingredientsInteractor;
    }

    /**
     * Adds a new ingredient with the specified quantity.
     *
     * @param ingredientName the name of the ingredient to be added
     * @param quantity the quantity of the ingredient to be added
     */
    public void addIngredient(String ingredientName, int quantity) {
        ingredientsInteractor.addIngredient(ingredientName, quantity);
    }

    /**
     * Deletes the specified ingredient.
     *
     * @param ingredientName the name of the ingredient to be deleted
     */
    public void deleteIngredient(String ingredientName) {
        ingredientsInteractor.deleteIngredient(ingredientName);
    }

    /**
     * Changes the quantity of the specified ingredient by the given delta.
     *
     * @param ingredientName the name of the ingredient whose quantity is to be changed
     * @param delta the amount to adjust the quantity by (positive to increase, negative to decrease)
     * @return the new quantity of the ingredient after the change
     */
    public int changeIngredientAmount(String ingredientName, int delta) {
        return ingredientsInteractor.changeIngredientAmount(ingredientName, delta);
    }

    /**
     * Retrieves the list of all ingredients.
     *
     * @return a list of ingredient names currently managed
     */
    public List<String> getIngredients() {
        return ingredientsInteractor.getIngredients();
    }

    /**
     * Returns to the main screen, delegating to the IngredientsInteractor.
     */
    public void return_to_main() {
        ingredientsInteractor.returnTomain();
    }
}
