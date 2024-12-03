package interface_adapter.main;

import interface_adapter.ViewManagerModel;
import interface_adapter.datescreen.DateViewModel;
import interface_adapter.ingredients_manager.IngredientsViewModel;
import interface_adapter.recommend_recipes.RecipesViewModel;
import interface_adapter.user_info.UserInfoViewModel;
import use_case.mainwindow.MainOutputBoundary;

/**
 * The presenter for managing view transitions in the main screen.
 * This class interacts with the ViewManagerModel and various view models (Date, Recipes, Ingredients, and User Info)
 * to switch between different screens based on user actions.
 */
public class MainPresenter implements MainOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final DateViewModel dateViewModel;
    private final RecipesViewModel recipeViewModel;
    private final IngredientsViewModel ingredientsViewModel;
    private final UserInfoViewModel userInfoViewModel;

    /**
     * Constructs a MainPresenter with the provided ViewManagerModel and view models.
     * @param viewManagerModel The ViewManagerModel used to manage the screen state.
     * @param dateViewModel The DateViewModel for managing the Date screen.
     * @param recipeViewModel The RecipesViewModel for managing the Recipe screen.
     * @param ingredientsViewModel The IngredientsViewModel for managing the Ingredients screen.
     * @param userInfoViewModel The UserInfoViewModel for managing the User Info screen.
     */
    public MainPresenter(ViewManagerModel viewManagerModel, DateViewModel dateViewModel,
                         RecipesViewModel recipeViewModel, IngredientsViewModel ingredientsViewModel,
                         UserInfoViewModel userInfoViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.dateViewModel = dateViewModel;
        this.recipeViewModel = recipeViewModel;
        this.ingredientsViewModel = ingredientsViewModel;
        this.userInfoViewModel = userInfoViewModel;
    }

    /**
     * Switches the view to the IngredientsView.
     */
    @Override
    public void switch_to_IngredientsView() {
        viewManagerModel.setState(ingredientsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the UserInfoView.
     */
    @Override
    public void switch_to_UserInfoView() {
        viewManagerModel.setState(userInfoViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the DateView.
     */
    @Override
    public void switch_to_DateView() {
        viewManagerModel.setState(dateViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the view to the RecipeView.
     */
    @Override
    public void switch_to_RecipeView() {
        viewManagerModel.setState(recipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
