package interface_adapter.ingredients_manager;

import java.util.HashMap;
import java.util.List;

import interface_adapter.ViewManagerModel;
import use_case.manage_ingredients.IngredientsIOutputBoundary;

/**
 * The presenter responsible for managing the ingredients view.
 * This class interacts with the ViewManagerModel to update the UI state and
 * handle the ingredients data by implementing the IngredientsIOutputBoundary interface.
 */
public class IngredientsPresenter implements IngredientsIOutputBoundary {
    // Stores ingredient data
    private HashMap<Integer, List<String>> ingredientsInfo;
    // Reference to manage view state
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for IngredientsPresenter.
     * @param viewManagerModel the ViewManagerModel used to control the view state
     */
    public IngredientsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Gets the ingredient information stored in the presenter.
     * @return a map where the key is an integer (ingredient identifier)
     *         and the value is a list of strings representing ingredient details.
     */
    public HashMap<Integer, List<String>> getIngredientsInfo() {
        return ingredientsInfo;
    }

    /**
     * Navigates the view back to the main screen by updating the view state.
     * This method is called when the user opts to return to the main screen.
     */
    @Override
    public void return_to_main() {
        // Return to the main screen by updating the ViewManagerModel
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}
