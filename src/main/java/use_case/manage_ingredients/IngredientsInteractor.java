package use_case.manage_ingredients;

import java.util.ArrayList;
import java.util.List;

import data_access.SpoonacularDAO;
import entity.*;

/**
 * The IngredientsInteractor class is responsible for managing ingredients in the application.
 * It implements the IngredientsInputBoundary interface, providing methods to add, delete, modify,
 * and retrieve ingredients, as well as generate recipes based on ingredients.
 */
public class IngredientsInteractor implements IngredientsInputBoundary {
    private final List<Ingredient> localIngredientList = new ArrayList<>();
    private final IngredientsIOutputBoundary outputBoundary;
    private final IngredientFactory ingredientFactory;
    private final SpoonacularDAO spoonacularDAO;

    /**
     * Constructs an IngredientsInteractor with the specified output boundary.
     *
     * @param outputBoundary the output boundary used to communicate with the presenter.
     */
    public IngredientsInteractor(IngredientsIOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.ingredientFactory = new CommonIngredientFactory();
        this.spoonacularDAO = new SpoonacularDAO();
    }

    /**
     * Adds a new ingredient to the list or updates the amount of an existing ingredient.
     *
     * @param ingredientName the name of the ingredient to add.
     * @param quantity the quantity of the ingredient to add.
     */
    @Override
    public void addIngredient(String ingredientName, int quantity) {
        for (Ingredient ingredient : localIngredientList) {
            if (ingredient.getName().equals(ingredientName)) {
                final int newAmount = ingredient.getAmount() + quantity;
                localIngredientList.remove(ingredient);
                localIngredientList.add(ingredientFactory.create(ingredientName, newAmount));
                return;
            }
        }
        final Ingredient newIngredient = ingredientFactory.create(ingredientName, quantity);
        localIngredientList.add(newIngredient);
    }

    /**
     * Deletes the specified ingredient from the list.
     *
     * @param ingredientName the name of the ingredient to delete.
     */
    @Override
    public void deleteIngredient(String ingredientName) {
        localIngredientList.removeIf(ingredient -> ingredient.getName().equals(ingredientName));
    }

    /**
     * Changes the amount of a specified ingredient by a given delta. If the resulting amount is negative,
     * the ingredient will be removed from the list.
     *
     * @param ingredientName the name of the ingredient to modify.
     * @param delta the amount by which to change the ingredient's quantity.
     * @return the updated amount of the ingredient, or -1 if the ingredient was not found.
     */
    @Override
    public int changeIngredientAmount(String ingredientName, int delta) {
        for (int i = 0; i < localIngredientList.size(); i++) {
            final Ingredient ingredient = localIngredientList.get(i);
            if (ingredient.getName().equals(ingredientName)) {
                final int newAmount = ingredient.getAmount() + delta;
                if (newAmount < 0) {
                    localIngredientList.remove(i);
                }
                else {
                    localIngredientList.remove(i);
                    localIngredientList.add(ingredientFactory.create(ingredientName, newAmount));
                }
                return newAmount;
            }
        }
        return -1;
    }

    /**
     * Retrieves the list of ingredients as strings, each containing the ingredient's name and amount.
     *
     * @return a list of strings representing the ingredients and their amounts.
     */
    @Override
    public List<String> getIngredients() {
        final List<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName() + " - " + ingredient.getAmount());
        }
        return ingredientList;
    }

    /**
     * Retrieves the list of ingredients as an ArrayList of strings, each containing the ingredient's name.
     *
     * @return an ArrayList of ingredient names.
     */
    @Override
    public ArrayList<String> getIngredientsArray() {
        final ArrayList<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName());
        }
        return ingredientList;
    }

    /**
     * Returns to the main screen of the application.
     */
    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    /**
     * Generates a list of recipes based on the provided ingredients, user preferences, and whether user-specific
     * information should be considered.
     *
     * @param ingredients the list of ingredient names to search for.
     * @param userPreferences the user preferences used to filter recipes.
     * @param userInfo whether to consider user-specific information when generating recipes.
     * @return a list of recipes that match the specified ingredients and preferences.
     */
    @Override
    public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences,
                                                       boolean userInfo) {
        final UserPreferences nullPreferences = new UserPreferences(0, false,
                false, new String[0]);
        if (userInfo) {
            return spoonacularDAO.getRecipesFromIngredients(ingredients, userPreferences);
        }
        else {
            return spoonacularDAO.getRecipesFromIngredients(ingredients, nullPreferences);
        }
    }
}
