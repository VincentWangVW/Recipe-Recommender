package interface_adapter.main;

import interface_adapter.ViewManagerModel;
import interface_adapter.datescreen.DateViewModel;
import interface_adapter.ingredients_manager.IngredientsViewModel;
import interface_adapter.recommend_recipes.RecipesViewModel;
import interface_adapter.user_info.UserInfoViewModel;
import use_case.mainwindow.MainOutputBoundary;

/**
 * Presenter for handling transitions between different views in the main interface.
 * Responsible for updating the {@link ViewManagerModel} with the appropriate state based on user actions.
 */
public class MainPresenter implements MainOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DateViewModel dateViewModel;
    private final RecipesViewModel recipeViewModel;
    private final IngredientsViewModel ingredientsViewModel;
    private final UserInfoViewModel userInfoViewModel;

    public MainPresenter(ViewManagerModel viewManagerModel, DateViewModel dateViewModel,
                         RecipesViewModel recipeViewModel, IngredientsViewModel ingredientsViewModel,
                         UserInfoViewModel userInfoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dateViewModel = dateViewModel;
        this.recipeViewModel = recipeViewModel;
        this.ingredientsViewModel = ingredientsViewModel;
        this.userInfoViewModel = userInfoViewModel;
    }

    @Override
    public void switchToIngredientsView() {
        viewManagerModel.setState(ingredientsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToUserInfoView() {
        viewManagerModel.setState(userInfoViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToDateView() {
        viewManagerModel.setState(dateViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecipeView() {
        viewManagerModel.setState(recipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
