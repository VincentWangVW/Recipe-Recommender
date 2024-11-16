package interface_adapter.ingredients_manager;

import interface_adapter.ViewManagerModel;
import use_case.manage_ingredients.IngredientsIOutputBoundary;

import java.util.HashMap;
import java.util.List;

public class IngredientsPresenter implements IngredientsIOutputBoundary {
    private HashMap<Integer, List<String>> ingredientsInfo; // Stores ingredient data
    private final ViewManagerModel viewManagerModel;        // Reference to manage view state

    public IngredientsPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentIngredients(HashMap<Integer, List<String>> ingredientsInfo) {
        // Assign the incoming ingredient data to the instance variable
        this.ingredientsInfo = ingredientsInfo;
    }

    // Getter for ingredientsInfo
    public HashMap<Integer, List<String>> getIngredientsInfo() {
        return ingredientsInfo;
    }

    @Override
    public void return_to_main() {
        // Return to the main screen by updating the ViewManagerModel
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}
