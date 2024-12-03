package use_case.recommend_recipes;

/**
 * The RecipesInputBoundary interface defines the methods that an input boundary or controller
 * should implement to handle user interactions related to recipe recommendations. It provides
 * methods for returning to the main screen, navigating to generated recipes, and retrieving holiday information.
 */
public interface RecipesInputBoundary {

    /**
     * Returns to the main screen or menu. This method is called when the user navigates away
     * from the current recipe-related view.
     */
    void return_to_main();

    /**
     * Navigates to the generated recipes screen with the provided parameters.
     *
     * @param selectedType the type of recipe generation (e.g., ingredients, season, holiday, etc.)
     * @param userInfo a boolean flag indicating if user-specific information should be considered
     * @param custom a custom string parameter for personalized recipe searches
     */
    void go_to_generated(String selectedType, boolean userInfo, String custom);

    /**
     * Retrieves the current holiday information.
     *
     * @return the current holiday as a String
     */
    String getHoliday();
}
