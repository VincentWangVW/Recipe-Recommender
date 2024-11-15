package interface_adapter.recommend_recipes;

import use_case.recommend_recipes.RecipesInputBoundary;

public class RecipesController {
    private final RecipesInputBoundary recipesInputBoundary;
    // TODO add input boundary for each use case

    public RecipesController(RecipesInputBoundary inputBoundary) {
        this.recipesInputBoundary = inputBoundary;
        // TODO add input boundary for each use case
    }

    public void return_to_main() {
        recipesInputBoundary.return_to_main();
    }

    // TODO also change this for each of the use cases
    public void generateRecipes(boolean followUserInfo, boolean isIngredients, boolean isSeason,
                                boolean isHoliday, boolean isCustom) {
        if (isIngredients) {
            recipesInputBoundary.generateRecipesFromIngredients(followUserInfo);
        }
        if (isSeason) {
            // TODO change to season
            recipesInputBoundary.generateRecipesFromSeason(followUserInfo);
        }
        if (isHoliday) {
            // TODO change to holiday
            recipesInputBoundary.generateRecipesFromHoliday(followUserInfo);
        }
        if (isCustom) {
            // TODO change to custom
            recipesInputBoundary.generateRecipesFromCustom(followUserInfo);
        }
    }
}