package interface_adapter.recommend_recipes;

import use_case.recommend_recipes.RecipesInputBoundary;
import java.util.List;

public class RecipesController {
    private final RecipesInputBoundary inputBoundary;

    public RecipesController(RecipesInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void getRecipesFromIngredients(List<String> ingredients, int missingIngredients) {
        inputBoundary.getRecipesFromIngredients(ingredients, missingIngredients);
    }

    public void return_to_main() {
        inputBoundary.return_to_main();
    }
}