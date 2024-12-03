package interface_adapter.recommend_recipes;

import java.util.HashMap;
import java.util.List;

/**
 * State class for managing and storing recipe-related information.
 * This class holds the recipe data and any error messages related to the recipe processing.
 */
public class RecipesState {
    private HashMap<Integer, List<String>> recipeInfo;
    private String errorMessage;

    /**
     * Gets the recipe information stored in this state.
     *
     * @return A HashMap containing the recipe information, where the key is an integer
     *         (e.g., recipe ID) and the value is a list of strings describing the recipe.
     */
    public HashMap<Integer, List<String>> getRecipeInfo() {
        return recipeInfo;
    }

    /**
     * Sets the recipe information for this state.
     *
     * @param recipeInfo A HashMap containing the recipe information to be set.
     *                   The key is an integer (e.g., recipe ID), and the value is a list
     *                   of strings describing the recipe.
     */
    public void setRecipeInfo(HashMap<Integer, List<String>> recipeInfo) {
        this.recipeInfo = recipeInfo;
    }
}
