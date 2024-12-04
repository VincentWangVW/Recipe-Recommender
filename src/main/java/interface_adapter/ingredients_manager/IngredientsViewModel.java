package interface_adapter.ingredients_manager;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Ingredients Manager screen.
 * This class manages the state and user interface labels for the ingredients management screen.
 */
public class IngredientsViewModel extends ViewModel<IngredientsState> {
    public static final String TITLE = "Ingredients Manager";
    public static final String INGREDIENT_NAME = "Ingredient Name";
    public static final String QUANTITY = "Quantity (integer)";
    public static final String ADD_BUTTON = "Add Ingredient";
    public static final String DELETE_BUTTON = "Delete Selected";
    public static final String RETURN_BUTTON = "Return to Main";
    public static final String INCREASE_BUTTON = "+";
    public static final String DECREASE_BUTTON = "-";

    /**
     * Constructs an IngredientsViewModel instance, initializing the screen state.
     * Sets the state to a new IngredientsState, which holds the data related to ingredient management.
     */
    public IngredientsViewModel() {
        super("INGREDIENTS_SCREEN");
        setState(new IngredientsState());
    }
}
