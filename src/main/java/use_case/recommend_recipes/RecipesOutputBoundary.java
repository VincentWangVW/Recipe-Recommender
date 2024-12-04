package use_case.recommend_recipes;

/**
 * Interface defining the output boundary for recipe-related operations.
 */
public interface RecipesOutputBoundary {

    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Navigates to the generated recipes view with the specified parameters.
     *
     * @param selectedType The type of recipe generation (e.g., "Ingredients", "Season").
     * @param userInfo     A boolean indicating if user-specific information should be considered.
     * @param custom       Custom criteria for recipe generation.
     */
    void goToGenerated(String selectedType, boolean userInfo, String custom);
}
