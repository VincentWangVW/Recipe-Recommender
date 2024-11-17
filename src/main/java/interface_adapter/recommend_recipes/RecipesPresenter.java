package interface_adapter.recommend_recipes;

import interface_adapter.ViewManagerModel;
import use_case.recommend_recipes.RecipesOutputBoundary;

public class RecipesPresenter implements RecipesOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public RecipesPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
    public void return_to_main() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void go_to_generated(String selectedType, boolean userInfo) {
        viewManagerModel.setState("GENERATED_SCREEN");
        viewManagerModel.firePropertyChanged();
        viewManagerModel.setSelectedType(selectedType);
        viewManagerModel.setUserInfo(userInfo);
    }
}