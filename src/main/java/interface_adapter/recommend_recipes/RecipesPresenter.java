package interface_adapter.recommend_recipes;

import interface_adapter.ViewManagerModel;
import use_case.recommend_recipes.RecipesOutputBoundary;

/**
 * Presenter for the recipes-related views, responsible for updating the view state
 * and interacting with the ViewManagerModel. This class handles transitions between views
 * and passes necessary data for generating recipes.
 */
public class RecipesPresenter implements RecipesOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a new RecipesPresenter with the specified ViewManagerModel.
     *
     * @param viewManagerModel The model used to manage the view state and properties.
     */
    public RecipesPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Returns to the main screen by updating the ViewManagerModel.
     * This method is called when the user wishes to navigate back to the main screen.
     */
    public void return_to_main() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Navigates to the generated recipes screen with the provided data.
     *
     * @param selectedType The type of recipes to generate (e.g., holiday, season).
     * @param userInfo A flag indicating whether user-specific information is involved.
     * @param custom A custom string that may influence the recipe generation.
     */
    @Override
    public void go_to_generated(String selectedType, boolean userInfo, String custom) {
        viewManagerModel.setState("GENERATED_SCREEN");
        viewManagerModel.firePropertyChanged();
        viewManagerModel.setSelectedType(selectedType);
        viewManagerModel.setUserInfo(userInfo);
        viewManagerModel.setCustom(custom);
    }
}
