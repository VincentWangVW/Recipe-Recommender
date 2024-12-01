package interface_adapter.ingredients_manager;

import interface_adapter.ViewModel;

public class IngredientsViewModel extends ViewModel<IngredientsState> {
    public final String TITLE = "Ingredients Manager";
    public final String INGREDIENT_NAME = "Ingredient Name";
    public final String QUANTITY = "Quantity (integer)";
    public final String ADD_BUTTON = "Add Ingredient";
    public final String DELETE_BUTTON = "Delete Selected";
    public final String RETURN_BUTTON = "Return to Main";
    public final String INCREASE_BUTTON = "+";
    public final String DECREASE_BUTTON = "-";

    public IngredientsViewModel() {
        super("INGREDIENTS_SCREEN");
        setState(new IngredientsState());
    }
}
