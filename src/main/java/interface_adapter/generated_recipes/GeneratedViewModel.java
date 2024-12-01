package interface_adapter.generated_recipes;

import entity.Recipe;
import interface_adapter.ViewModel;

import java.util.ArrayList;

public class GeneratedViewModel extends ViewModel<GeneratedState> {
    public final String TITLE_LABLE = "Recipes";
    public final String COLUMN_NAME_1 = "Name";
    public final String COLUMN_NAME_2 = "URL";
    public final String COLUMN_NAME_3 = "Missing Ingredients";
    public final String GENERATE_BUTTON = "Generate Recipes";
    public final String RETURN_BUTTON = "Return";
    public final String NO_HOLIDAY_TODAY = "No Holiday Today!";
    public final String HOLIDAY_TITLE = "No Holiday";
    public final String NO_RECIPES_TITLE = "No Recipes Found";
    public final String NO_RECIPES = "No Recipes Found, Try Again.";
    public final String ERROR_MESSAGE = "An error occurred while generating recipes: ";
    public final String ERROR_TITLE = "Error";

    public GeneratedViewModel() {
        super("GENERATED_SCREEN");
        setState(new GeneratedState());
    }

    public void setGeneratedRecipes(ArrayList<Recipe> generatedRecipes) {
        getState().setGeneratedRecipes(generatedRecipes);
        firePropertyChanged("generatedRecipes");
    }
}
