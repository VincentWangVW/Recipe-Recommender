package interface_adapter.generated_recipes;

import java.util.ArrayList;

import entity.Recipe;
import use_case.generated_manager.GeneratedInputBoundary;

/**
 * Controller class for handling actions related to generated recipes.
 * This class acts as a mediator between the view and the use case layer
 * for generating recipes, retrieving holidays, and managing navigation.
 */
public class GeneratedController {
    private final GeneratedInputBoundary generatedInputBoundary;

    public GeneratedController(GeneratedInputBoundary inputBoundary) {
        this.generatedInputBoundary = inputBoundary;
    }

    /**
     * Returns to the main screen.
     */
    public void returnTomain() {
        generatedInputBoundary.returnTomain();
    }

    /**
     * Generates recipes based on the current generation type.
     *
     * @return A list of generated recipes.
     */
    public ArrayList<Recipe> generateRecipes() {
        return generatedInputBoundary.generateRecipes();
    }

    public String getGenerationType() {
        return generatedInputBoundary.getGenerationType();
    }

    public String getHoliday() {
        return generatedInputBoundary.getHoliday();
    }
}
