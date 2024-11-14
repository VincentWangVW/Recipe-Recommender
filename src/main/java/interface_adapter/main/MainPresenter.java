package interface_adapter.main;

import interface_adapter.ViewManagerModel;
import interface_adapter.recommend_recipes.RecipesViewModel;
import interface_adapter.season.SeasonViewModel;
import use_case.mainwindow.MainOutputBoundary;

public class MainPresenter implements MainOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SeasonViewModel seasonViewModel;
    private final RecipesViewModel recipeViewModel;

    public MainPresenter(ViewManagerModel viewManagerModel, SeasonViewModel seasonViewModel,
                         RecipesViewModel recipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.seasonViewModel = seasonViewModel;
        this.recipeViewModel = recipeViewModel;
    }

    @Override
    public void switch_to_IngredientsView() {

    }

    @Override
    public void switch_to_UserInfoView() {

    }

    @Override
    public void switch_to_SeasonView() {
        viewManagerModel.setState(seasonViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switch_to_RecipeView() {
        viewManagerModel.setState(recipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
