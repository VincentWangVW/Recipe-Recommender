package interface_adapter.ingredients_manager;

import java.util.List;
import java.util.Map;

import interface_adapter.ViewManagerModel;
import use_case.manage_ingredients.IngredientsOutputBoundary;

/**
 * Presenter for managing ingredients-related data and actions.
 * Implements the IngredientsOutputBoundary interface to communicate
 * with the view layer while maintaining separation of concerns.
 */
public class IngredientsPresenter implements IngredientsOutputBoundary {
    private Map<Integer, List<String>> ingredientsInfo;
    private final ViewManagerModel viewManagerModel;

    public IngredientsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    // Getter for ingredientsInfo
    public Map<Integer, List<String>> getIngredientsInfo() {
        return ingredientsInfo;
    }

    @Override
    public void returnTomain() {
        // Return to the main screen by updating the ViewManagerModel
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}
