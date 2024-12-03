package interface_adapter.recommend_recipes;

import interface_adapter.ViewModel;

/**
 * ViewModel class for managing the state and presentation of the Recipes Screen.
 * This class binds the RecipesState to the view and allows the updating of the view's state.
 */
public class RecipesViewModel extends ViewModel<RecipesState> {

    /**
     * Constructor for RecipesViewModel.
     * Initializes the view model with a default state and sets the view name to "RECIPES_SCREEN".
     */
    public RecipesViewModel() {
        super("RECIPES_SCREEN");
        setState(new RecipesState());
    }
}
