package use_case.manage_ingredients;

import entity.Recipe;
import entity.UserPreferences;

import java.util.ArrayList;
import java.util.List;

public interface IngredientsInputBoundary {
    void return_to_main();
    void addIngredient(String ingredientName, int quantity);
    void deleteIngredient(String ingredientName);
    int changeIngredientAmount(String ingredientName, int delta);
    List<String> getIngredients();
    ArrayList<String> getIngredientsArray();
    ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients, UserPreferences userPreferences,
                                                       boolean userInfo);
}
