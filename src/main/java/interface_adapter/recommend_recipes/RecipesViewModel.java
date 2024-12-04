package interface_adapter.recommend_recipes;

import interface_adapter.ViewModel;

/**
 * The RecipesViewModel class represents the view model for the recipes screen.
 * It extends the ViewModel class and provides the state management for recipes-related data.
 */
public class RecipesViewModel extends ViewModel<RecipesState> {
    public RecipesViewModel() {
        super("RECIPES_SCREEN");
        setState(new RecipesState());
    }
}
