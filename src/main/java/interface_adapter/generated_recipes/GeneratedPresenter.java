package interface_adapter.generated_recipes;

import interface_adapter.ViewManagerModel;
import use_case.generated_manager.GeneratedOutputBoundary;

/**
 * Presenter for handling the output of the recipe generation process.
 * Acts as a bridge between the use case layer and the view layer, managing screen transitions and providing data
 * to the view.
 */
public class GeneratedPresenter implements GeneratedOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a {@code GeneratedPresenter} with the specified view manager model.
     *
     * @param viewManagerModel the view manager model for managing the view state
     */
    public GeneratedPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Navigates back to the main screen by updating the view manager's state.
     */
    public void return_to_main() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Retrieves the view manager model associated with this presenter.
     *
     * @return the {@link ViewManagerModel} for managing the view's state
     */
    @Override
    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
