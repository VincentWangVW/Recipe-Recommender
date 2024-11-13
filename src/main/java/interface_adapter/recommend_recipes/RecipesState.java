package interface_adapter.recommend_recipes;

import java.util.HashMap;
import java.util.List;

public class RecipesState {
    private HashMap<Integer, List<String>> recipeInfo;
    private String errorMessage;

    public HashMap<Integer, List<String>> getRecipeInfo() {
        return recipeInfo;
    }

    public void setRecipeInfo(HashMap<Integer, List<String>> recipeInfo) {
        this.recipeInfo = recipeInfo;
    }
}