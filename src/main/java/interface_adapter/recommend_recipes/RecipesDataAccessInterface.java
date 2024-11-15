package interface_adapter.recommend_recipes;

import entity.Recipe;

import java.util.ArrayList;

public interface RecipesDataAccessInterface {
    public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                       int missingIngredients) throws Exception;
}
