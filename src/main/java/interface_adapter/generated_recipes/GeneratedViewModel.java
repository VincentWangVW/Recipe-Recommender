package interface_adapter.generated_recipes;

import entity.Recipe;
import interface_adapter.ViewModel;

import java.util.ArrayList;

public class GeneratedViewModel extends ViewModel<GeneratedState> {
    public GeneratedViewModel() {
        super("GENERATED_SCREEN");
        setState(new GeneratedState());
    }

    public void setGeneratedRecipes(ArrayList<Recipe> generatedRecipes) {
        getState().setGeneratedRecipes(generatedRecipes);
        firePropertyChanged("generatedRecipes");
    }
}
