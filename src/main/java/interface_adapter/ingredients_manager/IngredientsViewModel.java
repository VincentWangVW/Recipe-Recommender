package interface_adapter.ingredients_manager;

import interface_adapter.ViewModel;

public class IngredientsViewModel extends ViewModel<IngredientsState> {
    public IngredientsViewModel() {
        super("INGREDIENTS_SCREEN");
        setState(new IngredientsState());
    }
}
