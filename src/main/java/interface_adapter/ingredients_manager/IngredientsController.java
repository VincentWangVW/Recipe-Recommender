package interface_adapter.ingredients_manager;

import use_case.manage_ingredients.IngredientsInteractor;
import java.util.List;

public class IngredientsController {
    private final IngredientsInteractor ingredientsInteractor;

    public IngredientsController(IngredientsInteractor ingredientsInteractor) {
        this.ingredientsInteractor = ingredientsInteractor;
    }

    public void addIngredient(String ingredientName, int quantity) {
        ingredientsInteractor.addIngredient(ingredientName, quantity);
    }

    public void deleteIngredient(String ingredientName) {
        ingredientsInteractor.deleteIngredient(ingredientName);
    }

    public int changeIngredientAmount(String ingredientName, int delta) {
        return ingredientsInteractor.changeIngredientAmount(ingredientName, delta);
    }

    public List<String> getIngredients() {
        return ingredientsInteractor.getIngredients();
    }

    public void return_to_main() {
        ingredientsInteractor.returnTomain();
    }
}