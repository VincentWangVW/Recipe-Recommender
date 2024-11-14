package interface_adapter.recommend_recipes;

import java.util.ArrayList;
import java.util.HashMap;

public interface RecipesDataAccessInterface {
    public HashMap<Integer, ArrayList<String>> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                                         int missingIngredients) throws Exception;
}
