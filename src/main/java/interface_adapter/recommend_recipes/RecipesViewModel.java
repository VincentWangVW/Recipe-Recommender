package interface_adapter.recommend_recipes;

import interface_adapter.ViewModel;

public class RecipesViewModel extends ViewModel<RecipesState> {
    public RecipesViewModel() {
        super("RECIPES_SCREEN");
        setState(new RecipesState());
    }
}
