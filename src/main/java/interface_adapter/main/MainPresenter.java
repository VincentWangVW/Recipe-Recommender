package interface_adapter.main;

import interface_adapter.ViewManagerModel;
import interface_adapter.ingredients_manager.IngredientsViewModel;
import interface_adapter.recommend_recipes.RecipesViewModel;
import interface_adapter.season.SeasonViewModel;
import use_case.mainwindow.MainOutputBoundary;

public class MainPresenter implements MainOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SeasonViewModel seasonViewModel;
    private final RecipesViewModel recipeViewModel;
    private final IngredientsViewModel ingredientsViewModel;

    public MainPresenter(ViewManagerModel viewManagerModel, SeasonViewModel seasonViewModel,
                         RecipesViewModel recipeViewModel, IngredientsViewModel ingredientsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.seasonViewModel = seasonViewModel;
        this.recipeViewModel = recipeViewModel;
        this.ingredientsViewModel = ingredientsViewModel;
    }

    @Override
    public void switch_to_IngredientsView() {
        viewManagerModel.setState(ingredientsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
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
