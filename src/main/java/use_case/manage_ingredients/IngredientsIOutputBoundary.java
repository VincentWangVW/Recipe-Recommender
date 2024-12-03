package use_case.manage_ingredients;

/**
 * The IngredientsIOutputBoundary interface defines the output boundary for the ingredients use case.
 * It provides methods for the interactor to communicate with the presenter or view, specifically
 * for actions like returning to the main screen.
 */
public interface IngredientsIOutputBoundary {

    /**
     * This method is used to navigate back to the main screen of the application.
     */
    void return_to_main();
}
