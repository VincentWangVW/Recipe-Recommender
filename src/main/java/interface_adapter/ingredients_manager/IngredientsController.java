package interface_adapter.ingredients_manager;

import use_case.manage_ingredients.IngredientsInteractor;
import java.util.ArrayList;
import java.util.List;

public class IngredientsController {
    private final List<Ingredient> localIngredientList = new ArrayList<>();
    private final IngredientsInteractor ingredientsInteractor;

    // Constructor to initialize with IngredientsInteractor
    public IngredientsController(IngredientsInteractor ingredientsInteractor) {
        this.ingredientsInteractor = ingredientsInteractor;
    }

    // Add ingredient with quantity
    public void addIngredient(String ingredientName, int quantity) {
        // Check if ingredient already exists, if so update the quantity
        for (Ingredient ingredient : localIngredientList) {
            if (ingredient.getName().equals(ingredientName)) {
                ingredient.setQuantity(ingredient.getQuantity() + quantity);
                return;
            }
        }
        // If ingredient does not exist, add a new entry
        localIngredientList.add(new Ingredient(ingredientName, quantity));
    }

    // Delete ingredient by name
    public void deleteIngredient(String ingredientName) {
        localIngredientList.removeIf(ingredient -> ingredient.getName().equals(ingredientName));
    }

    // Get list of ingredients in string format
    public List<String> getIngredients() {
        List<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName() + " - " + ingredient.getQuantity());
        }
        return ingredientList;
    }

    // Method to transition to the main screen
    public void return_to_main() {
        ingredientsInteractor.return_to_main();
    }

    // Ingredient class to represent an ingredient with a name and quantity
    private static class Ingredient {
        private String name;
        private int quantity;

        public Ingredient(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
