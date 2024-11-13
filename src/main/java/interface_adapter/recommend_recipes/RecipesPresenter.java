package interface_adapter.recommend_recipes;

import interface_adapter.ViewManagerModel;
import use_case.recommend_recipes.RecipesOutputBoundary;
import java.util.HashMap;
import java.util.List;

public class RecipesPresenter implements RecipesOutputBoundary {
    private HashMap<Integer, List<String>> recipeInfo;
    private final ViewManagerModel viewManagerModel;

    public RecipesPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentRecipes(HashMap<Integer, List<String>> recipeInfo) {
        this.recipeInfo = recipeInfo;
    }

    public HashMap<Integer, List<String>> getRecipeInfo() {
        return recipeInfo;
    }

    public void return_to_main() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}