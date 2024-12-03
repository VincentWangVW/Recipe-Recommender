package interface_adapter.ingredients_manager;

import java.util.List;

import use_case.manage_ingredients.IngredientsInteractor;

/**
 * Controller class for managing ingredients.
 * This class acts as an intermediary between the view and the interactor,
 * facilitating the addition, deletion, and modification of ingredients.
 */
public class IngredientsController {
    private final IngredientsInteractor ingredientsInteractor;

    /**
     * Constructs an IngredientsController with the specified interactor.
     *
     * @param ingredientsInteractor The interactor to handle ingredient-related operations.
     */
    public IngredientsController(IngredientsInteractor ingredientsInteractor) {
        this.ingredientsInteractor = ingredientsInteractor;
    }

    /**
     * Adds a new ingredient with the specified quantity.
     *
     * @param ingredientName The name of the ingredient to be added.
     * @param quantity       The quantity of the ingredient to be added.
     */
    public void addIngredient(String ingredientName, int quantity) {
        ingredientsInteractor.addIngredient(ingredientName, quantity);
    }

    /**
     * Deletes an ingredient by its name.
     *
     * @param ingredientName The name of the ingredient to be deleted.
     */
    public void deleteIngredient(String ingredientName) {
        ingredientsInteractor.deleteIngredient(ingredientName);
    }

    /**
     * Changes the amount of an existing ingredient by a specified delta.
     *
     * @param ingredientName The name of the ingredient whose quantity is to be changed.
     * @param delta          The amount to change the ingredient's quantity by.
     * @return The updated quantity of the ingredient.
     */
    public int changeIngredientAmount(String ingredientName, int delta) {
        return ingredientsInteractor.changeIngredientAmount(ingredientName, delta);
    }

    /**
     * Retrieves the list of all ingredients currently managed.
     *
     * @return A list of strings representing the ingredients.
     */
    public List<String> getIngredients() {
        return ingredientsInteractor.getIngredients();
    }

    /**
     * Returns to the main screen.
     */
    public void returnTomain() {
        ingredientsInteractor.returnTomain();
    }
}
