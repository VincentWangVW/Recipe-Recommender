package interface_adapter.recommend_recipes;

import use_case.recommend_recipes.RecipesInputBoundary;

/**
 * Controller for managing recipe-related actions.
 * This controller handles user input and delegates actions to the interactor through the input boundary.
 */
public class RecipesController {
    private final RecipesInputBoundary recipesInputBoundary;

    public RecipesController(RecipesInputBoundary inputBoundary) {
        this.recipesInputBoundary = inputBoundary;
    }

    /**
     * Returns the user to the main screen.
     */
    public void returnTomain() {
        recipesInputBoundary.returnTomain();
    }

    /**
     * Navigates to the generated recipes view based on the provided parameters.
     *
     * @param selectedType The type of recipe generation (e.g., custom, seasonal, etc.).
     * @param userInfo     Whether user-specific preferences should be applied.
     * @param custom       The custom query for recipe generation.
     */
    public void goToGenerated(String selectedType, boolean userInfo, String custom) {
        recipesInputBoundary.goToGenerated(selectedType, userInfo, custom);
    }

    public String getHoliday() {
        return recipesInputBoundary.getHoliday();
    }
}
