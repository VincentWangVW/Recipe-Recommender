package interface_adapter.recommend_recipes;

import use_case.recommend_recipes.RecipesInputBoundary;

/**
 * The controller for managing the Recipes screen actions.
 * This class serves as an intermediary between the user interface and the use case for recommending recipes.
 * It handles user actions related to recipes, such as returning to the main screen and navigating to generated recipes.
 */
public class RecipesController {
    private final RecipesInputBoundary recipesInputBoundary;

    /**
     * Constructs a RecipesController with the provided RecipesInputBoundary.
     * @param inputBoundary The RecipesInputBoundary used for interacting with the use case layer.
     */
    public RecipesController(RecipesInputBoundary inputBoundary) {
        this.recipesInputBoundary = inputBoundary;
    }

    /**
     * Returns to the main screen.
     * This method delegates the action to the RecipesInputBoundary.
     */
    public void return_to_main() {
        recipesInputBoundary.returnTomain();
    }

    /**
     * Navigates to the generated recipes screen with the selected type, user info flag, and custom data.
     * This method delegates the action to the RecipesInputBoundary.
     * @param selectedType The selected recipe generation type.
     * @param userInfo A flag indicating if user information should be used.
     * @param custom Custom information related to recipe generation.
     */
    public void go_to_generated(String selectedType, boolean userInfo, String custom) {
        recipesInputBoundary.goToGenerated(selectedType, userInfo, custom);
    }

    /**
     * Gets the holiday associated with the current recipe recommendations.
     * @return The holiday string associated with the recipes.
     */
    public String getHoliday() {
        return recipesInputBoundary.getHoliday();
    }
}
