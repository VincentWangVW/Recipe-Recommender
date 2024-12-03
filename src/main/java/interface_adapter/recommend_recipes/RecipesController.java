package interface_adapter.recommend_recipes;

import use_case.recommend_recipes.RecipesInputBoundary;

public class RecipesController {
    private final RecipesInputBoundary recipesInputBoundary;

    public RecipesController(RecipesInputBoundary inputBoundary) {
        this.recipesInputBoundary = inputBoundary;
    }

    public void return_to_main() {
        recipesInputBoundary.returnTomain();
    }

    public void go_to_generated(String selectedType, boolean userInfo, String custom) {
        recipesInputBoundary.goToGenerated(selectedType, userInfo, custom);
    }

    public String getHoliday() {
        return recipesInputBoundary.getHoliday();
    }
}