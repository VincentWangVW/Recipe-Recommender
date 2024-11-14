package use_case.recommend_recipes;

import java.util.HashMap;
import java.util.List;

public class RecipesInteractor implements RecipesInputBoundary {
    private final RecipesOutputBoundary outputBoundary;

    public RecipesInteractor(RecipesOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void getRecipesFromIngredients(List<String> ingredients, int missingIngredients) {
        HashMap<Integer, List<String>> recipes = new HashMap<>();
        // Example logic to populate recipes
        recipes.put(1, List.of("Recipe1", "Recipe2"));
        outputBoundary.presentRecipes(recipes);
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }
}