package use_case.recommend_recipes;

import data_access.SpoonacularDAO;
import entity.CommonRecipeFactory;
import entity.Recipe;
import entity.RecipeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipesInteractor implements RecipesInputBoundary {
    private final RecipesOutputBoundary outputBoundary;

    public RecipesInteractor(RecipesOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void getRecipesFromIngredients(List<String> ingredients, int missingIngredients) {
        HashMap<Integer, List<String>> recipes = new HashMap<>();
        // Example logic to populate recipes
        recipes.put(1, List.of("Recipe1", "Recipe2"));
        outputBoundary.presentRecipes(recipes);
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    public void generateRecipes(boolean ingredients, boolean userInfo, boolean season, boolean holiday, boolean drinkItem) {
        RecipeFactory recipeFactory = new CommonRecipeFactory();
        SpoonacularDAO spoonacularDAO = new SpoonacularDAO(recipeFactory);
        if (!(ingredients || userInfo || season || holiday || drinkItem)){
            throw new IllegalArgumentException("At least one of the parameters must be true");
            // TODO maybe make this a popup?
        }
        if (ingredients){
            // TODO
        }
        if (userInfo){
            // TODO
        }
        if (season){
            // TODO
        }
        if (holiday){
            // TODO
        }
        if (drinkItem){
            // TODO
        }
        // TODO most of this is temp to fill the area for now
        ArrayList<String> ingredientsList = new ArrayList<>();
        ingredientsList.add("sugar");
        ArrayList<Recipe> recipes;
        try {
            recipes = spoonacularDAO.getRecipesFromIngredients(ingredientsList, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HashMap<Integer, List<String>> recipeMap = new HashMap<>();
        for (int i = 0; i < recipes.size(); i++) {
            recipeMap.put(i, List.of(String.valueOf(recipes.get(i))));
        }
        outputBoundary.presentRecipes(recipeMap);
    }

    // TODO you'll need these for the different use cases
    @Override
    public void generateRecipesFromIngredients(boolean ingredients) {
        // Implementation
    }

    @Override
    public void generateRecipesFromUserInfo(boolean userInfo) {
        // Implementation
    }

    @Override
    public void generateRecipesFromSeason(boolean season) {
        // Implementation
    }

    @Override
    public void generateRecipesFromHoliday(boolean holiday) {
        // Implementation
    }

    @Override
    public void generateRecipesFromDrinkItem(boolean drinkItem) {
        // Implementation
    }
}