package use_case.generated_manager;

import interface_adapter.ViewManagerModel;

/**
 * The GeneratedOutputBoundary interface defines the contract for the output boundary
 * in the recipe generation use case. It provides methods for returning to the main view
 * and retrieving the ViewManagerModel, which holds the state and user preferences for the view.
 */
public interface GeneratedOutputBoundary {

    /**
     * Returns to the main view. This method is intended to be invoked by the interactor
     * when the user decides to navigate back to the main screen.
     */
    void return_to_main();

    /**
     * Retrieves the ViewManagerModel, which holds the current view state and other user-related data.
     * The ViewManagerModel is responsible for managing the state of the views and triggering changes
     * in the UI layer.
     *
     * @return the ViewManagerModel used for managing the application's views
     */
    ViewManagerModel getViewManagerModel();
}
