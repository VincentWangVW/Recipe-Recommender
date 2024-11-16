package interface_adapter.ingredients_manager;

import java.util.HashMap;
import java.util.List;

public class IngredientsState {
    private HashMap<Integer, List<String>> ingredientsInfo;
    private String errorMessage;

    public HashMap<Integer, List<String>> getingredientsInfoInfo() {
        return ingredientsInfo;
    }

    public void setRecipeInfo(HashMap<Integer, List<String>> ingredientsInfo) {
        this.ingredientsInfo = ingredientsInfo;
    }
}