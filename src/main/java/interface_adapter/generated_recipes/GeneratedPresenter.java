package interface_adapter.generated_recipes;

import interface_adapter.ViewManagerModel;
import use_case.generated_manager.GeneratedOutputBoundary;

/**
 * Presenter for the generated recipes feature.
 * This class updates the state of the application through the {@link ViewManagerModel}.
 */
public class GeneratedPresenter implements GeneratedOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public GeneratedPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Navigates back to the main screen by updating the application's state.
     */
    public void returnTomain() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
