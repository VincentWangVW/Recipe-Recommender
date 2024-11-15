package interface_adapter.recommend_recipes;

import use_case.recommend_recipes.RecipesInputBoundary;
import java.util.List;

public class RecipesController {
    private final RecipesInputBoundary inputBoundary;

    public RecipesController(RecipesInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void getRecipesFromIngredients(List<String> ingredients, int missingIngredients) {
        inputBoundary.getRecipesFromIngredients(ingredients, missingIngredients);
    }

    public void return_to_main() {
        inputBoundary.return_to_main();
    }

//    public void generateRecipes(boolean ingredients, boolean userInfo, boolean season, boolean holiday,
//                                boolean drinkItem) {
//        inputBoundary.generateRecipes(ingredients, userInfo, season, holiday, drinkItem);
//    }

    // TODO also change this for each of the use cases
    public void generateRecipes(boolean ingredients, boolean userInfo, boolean season, boolean holiday,
                                boolean drinkItem) {
        if (ingredients) {
            inputBoundary.generateRecipesFromIngredients(ingredients);
        }
        if (userInfo) {
            inputBoundary.generateRecipesFromUserInfo(userInfo);
        }
        if (season) {
            inputBoundary.generateRecipesFromSeason(season);
        }
        if (holiday) {
            inputBoundary.generateRecipesFromHoliday(holiday);
        }
        if (drinkItem) {
            inputBoundary.generateRecipesFromDrinkItem(drinkItem);
        }
    }
}