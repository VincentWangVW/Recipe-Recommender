package use_case.generated_manager;

import java.util.ArrayList;

import entity.Recipe;

/**
 * The GeneratedInputBoundary interface defines the methods that are used to interact
 * with the use case logic for generating recipes. It provides methods for navigating
 * back to the main view, generating recipes, and retrieving information about the
 * generation type and holiday.
 */
public interface GeneratedInputBoundary {

    /**
     * Navigates back to the main view or screen.
     */
    void return_to_main();

    /**
     * Generates a list of recipes based on the input data.
     *
     * @return a list of generated recipes
     */
    ArrayList<Recipe> generateRecipes();

    /**
     * Gets the type of recipe generation (e.g., random, based on ingredients, etc.).
     *
     * @return the generation type
     */
    String getGenerationType();

    /**
     * Gets the holiday associated with the recipe generation, if any.
     *
     * @return the holiday string (could be empty if no holiday is associated)
     */
    String getHoliday();
}
