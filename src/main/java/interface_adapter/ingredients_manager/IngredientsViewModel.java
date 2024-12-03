package interface_adapter.ingredients_manager;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Ingredients Manager screen.
 * This class manages the state and user interface labels for the ingredients management screen.
 */
public class IngredientsViewModel extends ViewModel<IngredientsState> {
    public final String TITLE = "Ingredients Manager";
    public final String INGREDIENT_NAME = "Ingredient Name";
    public final String QUANTITY = "Quantity (integer)";
    public final String ADD_BUTTON = "Add Ingredient";
    public final String DELETE_BUTTON = "Delete Selected";
    public final String RETURN_BUTTON = "Return to Main";
    public final String INCREASE_BUTTON = "+";
    public final String DECREASE_BUTTON = "-";

    /**
     * Constructs an IngredientsViewModel instance, initializing the screen state.
     * Sets the state to a new IngredientsState, which holds the data related to ingredient management.
     */
    public IngredientsViewModel() {
        super("INGREDIENTS_SCREEN");
        setState(new IngredientsState());
    }
}
