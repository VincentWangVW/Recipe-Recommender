package interface_adapter.recommend_recipes;

import entity.Recipe;
import entity.UserPreferences;

import java.io.IOException;
import java.util.ArrayList;

public interface RecipesDataAccessInterface {
    ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                UserPreferences userPreferences) throws Exception;
    ArrayList<Recipe> getRecipesFromQuery(String query, UserPreferences userPreferences) throws Exception;
}
