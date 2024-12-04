package use_case.recommend_recipes;

/**
 * Interface defining the input boundary for recipe-related operations.
 */
public interface RecipesInputBoundary {

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

    /**
     * Retrieves the current holiday information.
     *
     * @return The name of the holiday as a String.
     */
    String getHoliday();
}
