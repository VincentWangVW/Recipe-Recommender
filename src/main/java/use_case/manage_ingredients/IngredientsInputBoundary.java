package use_case.manage_ingredients;

import java.util.ArrayList;
import java.util.List;

import entity.Recipe;
import entity.UserPreferences;

/**
 * The IngredientsInputBoundary interface defines the operations for managing ingredients in the application.
 * It acts as the input boundary for the IngredientsInteractor, providing methods to interact with the ingredient data.
 */
public interface IngredientsInputBoundary {

    /**
     * Returns to the main screen of the application.
     */
    void return_to_main();

    /**
     * Adds a new ingredient with the specified name and quantity.
     *
     * @param ingredientName the name of the ingredient to add.
     * @param quantity the quantity of the ingredient to add.
     */
    void addIngredient(String ingredientName, int quantity);

    /**
     * Deletes the specified ingredient.
     *
     * @param ingredientName the name of the ingredient to delete.
     */
    void deleteIngredient(String ingredientName);

    /**
     * Changes the quantity of the specified ingredient by the given delta.
     *
     * @param ingredientName the name of the ingredient to modify.
     * @param delta the amount by which to change the ingredient's quantity.
     * @return the updated quantity of the ingredient.
     */
    int changeIngredientAmount(String ingredientName, int delta);

    /**
     * Retrieves the list of ingredients.
     *
     * @return a list of ingredient names.
     */
    List<String> getIngredients();

    /**
     * Retrieves the ingredients as an array list of strings.
     *
     * @return an ArrayList of ingredient names.
     */
    ArrayList<String> getIngredientsArray();

    /**
     * Retrieves recipes based on the provided list of ingredients, user preferences, and whether user information is
     * available.
     *
     * @param ingredients the list of ingredients to search for.
     * @param userPreferences the preferences of the user to consider when generating recipes.
     * @param userInfo whether user-specific information should be considered in the recipe generation.
     * @return a list of recipes matching the provided ingredients and preferences.
     */
    ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences,
                                                       boolean userInfo);
}
