package use_case.generated_manager;

import java.util.ArrayList;

import entity.Recipe;

/**
 * Interface defining the input boundary for the generated manager use case.
 */
public interface GeneratedInputBoundary {
    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Generates recipes based on the input criteria.
     *
     * @return A list of {@link Recipe} objects generated.
     */
    ArrayList<Recipe> generateRecipes();

    /**
     * Gets the generation type (e.g., ingredients, season, holiday, etc.).
     *
     * @return The generation type as a string.
     */
    String getGenerationType();

    /**
     * Gets the current holiday associated with the generated recipes.
     *
     * @return The holiday name as a string, or {@code null} if no holiday is set.
     */
    String getHoliday();
}
