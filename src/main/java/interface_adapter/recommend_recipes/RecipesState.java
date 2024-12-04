package interface_adapter.recommend_recipes;

import java.util.List;
import java.util.Map;

/**
 * The RecipesState class manages the state of recipes and related information.
 * It stores the recipe information and any associated error messages.
 */
public class RecipesState {
    private Map<Integer, List<String>> recipeInfo;
    private String errorMessage;

    public Map<Integer, List<String>> getRecipeInfo() {
        return recipeInfo;
    }

    public void setRecipeInfo(Map<Integer, List<String>> recipeInfo) {
        this.recipeInfo = recipeInfo;
    }
}
