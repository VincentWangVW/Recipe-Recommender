// src/main/java/interface_adapter/generated_recipes/GeneratedController.java

package interface_adapter.generated_recipes;

import java.util.ArrayList;

import entity.Recipe;
import use_case.generated_manager.GeneratedInputBoundary;

/**
 * Controller for managing the generation of recipes.
 * Acts as a bridge between the input boundary and the view layer.
 */
public class GeneratedController {
    private final GeneratedInputBoundary generatedInputBoundary;

    /**
     * Constructs a {@code GeneratedController} with the specified input boundary.
     *
     * @param inputBoundary the input boundary for handling recipe generation
     */
    public GeneratedController(GeneratedInputBoundary inputBoundary) {
        this.generatedInputBoundary = inputBoundary;
    }

    /**
     * Navigates back to the main screen.
     */
    public void return_to_main() {
        generatedInputBoundary.returnTomain();
    }

    /**
     * Generates a list of recipes based on the current configuration.
     *
     * @return an {@link ArrayList} of {@link Recipe} objects representing the generated recipes
     */
    public ArrayList<Recipe> generateRecipes() {
        return generatedInputBoundary.generateRecipes();
    }

    /**
     * Retrieves the type of generation used for creating recipes.
     *
     * @return a string representing the generation type
     */
    public String getGenerationType() {
        return generatedInputBoundary.getGenerationType();
    }

    /**
     * Retrieves the holiday associated with the generated recipes.
     *
     * @return a string representing the holiday name
     */
    public String getHoliday() {
        return generatedInputBoundary.getHoliday();
    }
}
