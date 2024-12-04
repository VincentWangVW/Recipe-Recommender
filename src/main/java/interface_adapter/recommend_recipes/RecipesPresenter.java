package interface_adapter.recommend_recipes;

import interface_adapter.ViewManagerModel;
import use_case.recommend_recipes.RecipesOutputBoundary;

/**
 * The RecipesPresenter class acts as the presenter in the Clean Architecture.
 * It handles the communication between the interactor and the view model.
 */
public class RecipesPresenter implements RecipesOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public RecipesPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Returns the user to the main screen by updating the view state.
     */
    public void returnTomain() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void goToGenerated(String selectedType, boolean userInfo, String custom) {
        viewManagerModel.setState("GENERATED_SCREEN");
        viewManagerModel.firePropertyChanged();
        viewManagerModel.setSelectedType(selectedType);
        viewManagerModel.setUserInfo(userInfo);
        viewManagerModel.setCustom(custom);
    }
}
