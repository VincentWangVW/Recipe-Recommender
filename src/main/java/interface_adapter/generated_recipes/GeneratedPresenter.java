package interface_adapter.generated_recipes;

import interface_adapter.ViewManagerModel;
import use_case.generated_manager.GeneratedOutputBoundary;

public class GeneratedPresenter implements GeneratedOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public GeneratedPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void return_to_main() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public ViewManagerModel getViewManagerModel() {
        return viewManagerModel;
    }
}
