package use_case.manage_ingredients;

import data_access.SpoonacularDAO;
import entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngredientsInteractor implements IngredientsInputBoundary {
    private final List<Ingredient> localIngredientList = new ArrayList<>();
    private final IngredientsIOutputBoundary outputBoundary;
    private final IngredientFactory ingredientFactory;
    private final SpoonacularDAO spoonacularDAO;

    public IngredientsInteractor(IngredientsIOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.ingredientFactory = new CommonIngredientFactory();
        this.spoonacularDAO = new SpoonacularDAO();
    }

    @Override
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

    @Override
    public void deleteIngredient(String ingredientName) {
        localIngredientList.removeIf(ingredient -> ingredient.getName().equals(ingredientName));
    }

    @Override
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

    @Override
    public List<String> getIngredients() {
        List<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName() + " - " + ingredient.getAmount());
        }
        return ingredientList;
    }

    @Override
    public ArrayList<String> getIngredientsNEW() {
        ArrayList<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName());
        }
        return ingredientList;
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences,
                                                       boolean userInfo) {
        UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);
            if (userInfo) {
                return spoonacularDAO.getRecipesFromIngredients(ingredients, userPreferences);
            }
            else {
                return spoonacularDAO.getRecipesFromIngredients(ingredients, nullPreferences);
            }
    }
}