package interface_adapter.generated_recipes;

import java.util.ArrayList;

import entity.Recipe;
import interface_adapter.ViewModel;

/**
 * The ViewModel for displaying generated recipes in the application.
 * This class manages the state related to the generated recipes and handles UI-related actions such as setting recipes.
 */
public class GeneratedViewModel extends ViewModel<GeneratedState> {
    public static final String TITLE_LABLE = "Recipes";
    public static final String COLUMN_NAME_1 = "Name";
    public static final String COLUMN_NAME_2 = "URL";
    public static final String COLUMN_NAME_3 = "Missing Ingredients";
    public static final String GENERATE_BUTTON = "Generate Recipes";
    public static final String RETURN_BUTTON = "Return";
    public static final String NO_HOLIDAY_TODAY = "No Holiday Today!";
    public static final String HOLIDAY_TITLE = "No Holiday";
    public static final String NO_RECIPES_TITLE = "No Recipes Found";
    public static final String NO_RECIPES = "No Recipes Found, Try Again.";
    public static final String ERROR_MESSAGE = "An error occurred while generating recipes: ";
    public static final String ERROR_TITLE = "Error";

    /**
     * Constructs a new GeneratedViewModel.
     * Initializes the state for the generated recipes screen.
     */
    public GeneratedViewModel() {
        super("GENERATED_SCREEN");
        setState(new GeneratedState());
    }

    /**
     * Sets the generated recipes in the view model's state and notifies listeners about the change.
     * This method updates the view model state with the list of generated recipes.
     *
     * @param generatedRecipes the list of {@link Recipe} objects to be set as generated recipes
     */
    public void setGeneratedRecipes(ArrayList<Recipe> generatedRecipes) {
        getState().setGeneratedRecipes(generatedRecipes);
        firePropertyChanged("generatedRecipes");
    }
}
