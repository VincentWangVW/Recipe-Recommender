package use_case.recommend_custom;

import data_access.SpoonacularDAO;
import entity.Recipe;
import use_case.manage_ingredients.IngredientsInputBoundary;
import entity.UserPreferences;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CustomSearchInteractor implements CustomSearchInputBoundary {
    private final CustomSearchOutputBoundary outputBoundary;
    private final SpoonacularDAO spoonacularDAO;
    private final IngredientsInputBoundary ingredientsInputBoundary;
    private final UserPreferences userPreferences;

    public CustomSearchInteractor(CustomSearchOutputBoundary outputBoundary,
                                  SpoonacularDAO spoonacularDAO,
                                  IngredientsInputBoundary ingredientsInputBoundary,
                                  UserPreferences userPreferences) {
        this.outputBoundary = outputBoundary;
        this.spoonacularDAO = spoonacularDAO;
        this.ingredientsInputBoundary = ingredientsInputBoundary;
        this.userPreferences = userPreferences;
    }

    @Override
    public void performCustomSearch(boolean followUserInfo) {
        try {
            // Step 1: Fetch user's ingredients
            List<String> ingredients = ingredientsInputBoundary.getIngredients()
                    .stream()
                    .map(s -> s.split(" - ")[0]) // Extract ingredient names
                    .collect(Collectors.toList());

            // Step 2: Query recipes using the Spoonacular API
            List<Recipe> recipes = spoonacularDAO.getRecipesFromIngredients(ingredients, 5);

            // Step 3: Filter recipes based on user preferences
            List<Recipe> filteredRecipes = filterRecipesByUserPreferences(recipes);

            // Step 4: Pass the results to the output boundary
            outputBoundary.presentCustomSearchResults(formatRecipes(filteredRecipes));
        } catch (Exception e) {
            // Handle errors and pass to output boundary
            outputBoundary.presentCustomSearchError("Error during custom recipe search: " + e.getMessage());
        }
    }

    private List<Recipe> filterRecipesByUserPreferences(List<Recipe> recipes) {
        return recipes.stream()
                // Exclude recipes containing allergens in their name
                .filter(recipe -> userPreferences.getAllergies().stream()
                        .noneMatch(allergen -> recipe.getName().toLowerCase().contains(allergen.toLowerCase())))
                // Include only recipes matching dietary restrictions in their name
                .filter(recipe -> userPreferences.getDietaryRestrictions().isEmpty() ||
                        userPreferences.getDietaryRestrictions().stream()
                                .anyMatch(restriction -> recipe.getName().toLowerCase()
                                        .contains(restriction.toLowerCase())))
                .collect(Collectors.toList());
    }

    private HashMap<Integer, List<String>> formatRecipes(List<Recipe> recipes) {
        HashMap<Integer, List<String>> formattedRecipes = new HashMap<>();
        for (int i = 0; i < recipes.size(); i++) {
            formattedRecipes.put(i, List.of(
                    recipes.get(i).getName(),
                    recipes.get(i).getUrl(),
                    "Missing Ingredients: " + recipes.get(i).getMissingItems()));
        }
        return formattedRecipes;
    }
}