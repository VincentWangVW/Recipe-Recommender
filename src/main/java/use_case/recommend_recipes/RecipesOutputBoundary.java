package use_case.recommend_recipes;

/**
 * The RecipesOutputBoundary interface defines the contract for the output boundary related to recipe recommendations.
 * It includes methods for navigating between screens and passing user-selected information regarding recipe generation.
 */
public interface RecipesOutputBoundary {

    /**
     * Returns to the main screen, typically by triggering the appropriate UI update.
     */
    void return_to_main();

    /**
     * Navigates to the generated recipes screen, passing the specified parameters.
     *
     * @param selectedType the type of recipe generation (e.g., ingredients, season, holiday, etc.)
     * @param userInfo a boolean flag indicating whether user-specific information should be considered
     * @param custom a custom string parameter for personalized recipe searches
     */
    void go_to_generated(String selectedType, boolean userInfo, String custom);
}
