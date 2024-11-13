package use_case.recommend_recipes;

import java.util.List;

public interface RecipesInputBoundary {
    void getRecipesFromIngredients(List<String> ingredients, int missingIngredients);
    void return_to_main();
}