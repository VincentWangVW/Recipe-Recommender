package use_case.manage_ingredients;

import entity.CommonIngredientFactory;
import entity.Ingredient;
import entity.IngredientFactory;
import java.util.ArrayList;
import java.util.List;

public class IngredientsInteractor implements IngredientsInputBoundary {
    private final List<Ingredient> localIngredientList = new ArrayList<>();
    private final IngredientsIOutputBoundary outputBoundary;
    private final IngredientFactory ingredientFactory;

    public IngredientsInteractor(IngredientsIOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.ingredientFactory = new CommonIngredientFactory();
    }

    public void addIngredient(String ingredientName, int quantity) {
        for (Ingredient ingredient : localIngredientList) {
            if (ingredient.getName().equals(ingredientName)) {
                int newAmount = ingredient.getAmount() + quantity;
                localIngredientList.remove(ingredient);
                localIngredientList.add(ingredientFactory.create(ingredientName, newAmount));
                return;
            }
        }
        Ingredient newIngredient = ingredientFactory.create(ingredientName, quantity);
        localIngredientList.add(newIngredient);
    }

    public void deleteIngredient(String ingredientName) {
        localIngredientList.removeIf(ingredient -> ingredient.getName().equals(ingredientName));
    }

    public int changeIngredientAmount(String ingredientName, int delta) {
        for (int i = 0; i < localIngredientList.size(); i++) {
            Ingredient ingredient = localIngredientList.get(i);
            if (ingredient.getName().equals(ingredientName)) {
                int newAmount = ingredient.getAmount() + delta;
                if (newAmount < 0) {
                    localIngredientList.remove(i);
                } else {
                    localIngredientList.remove(i);
                    localIngredientList.add(ingredientFactory.create(ingredientName, newAmount));
                }
                return newAmount;
            }
        }
        return -1;
    }

    public List<String> getIngredients() {
        List<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName() + " - " + ingredient.getAmount());
        }
        return ingredientList;
    }

    public void return_to_main() {
        outputBoundary.return_to_main();
    }
}