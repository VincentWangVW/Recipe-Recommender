package use_case.manage_ingredients;

import java.util.ArrayList;
import java.util.List;

import entity.Recipe;
import entity.UserPreferences;

/**
 * Interface defining the input boundary for managing ingredients use case.
 */
public interface IngredientsInputBoundary {

    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Adds an ingredient with the specified name and quantity.
     *
     * @param ingredientName The name of the ingredient to add.
     * @param quantity       The quantity of the ingredient.
     */
    void addIngredient(String ingredientName, int quantity);

    /**
     * Deletes an ingredient with the specified name.
     *
     * @param ingredientName The name of the ingredient to delete.
     */
    void deleteIngredient(String ingredientName);

    /**
     * Changes the quantity of the specified ingredient by the given delta.
     *
     * @param ingredientName The name of the ingredient to update.
     * @param delta          The change in quantity (positive or negative).
     * @return The updated quantity of the ingredient.
     */
    int changeIngredientAmount(String ingredientName, int delta);

    /**
     * Gets a list of ingredients as strings.
     *
     * @return A list of ingredient names.
     */
    List<String> getIngredients();

    /**
     * Gets an array list of ingredients.
     *
     * @return An array list of ingredient names.
     */
    ArrayList<String> getIngredientsArray();

    /**
     * Generates a list of recipes based on the specified ingredients, user preferences, and user information.
     *
     * @param ingredients     A list of ingredient names.
     * @param userPreferences The user's preferences for recipes.
     * @param userInfo        Whether to include user-specific information.
     * @return A list of recipes generated based on the ingredients.
     */
    ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences,
                                                       boolean userInfo);
}
