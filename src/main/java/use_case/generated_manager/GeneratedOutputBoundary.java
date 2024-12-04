package use_case.generated_manager;

import interface_adapter.ViewManagerModel;

/**
 * Interface defining the output boundary for the generated manager use case.
 */
public interface GeneratedOutputBoundary {
    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Gets the view manager model.
     *
     * @return The {@link ViewManagerModel} associated with the output boundary.
     */
    ViewManagerModel getViewManagerModel();
}
