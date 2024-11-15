package use_case.recommend_recipes;

import java.util.List;

public interface RecipesInputBoundary {
    void getRecipesFromIngredients(List<String> ingredients, int missingIngredients);
    void return_to_main();
    void generateRecipes(boolean ingredients, boolean userInfo, boolean season, boolean holiday, boolean drinkItem);
    public void generateRecipesFromIngredients(boolean ingredients);
    public void generateRecipesFromUserInfo(boolean userInfo);
    public void generateRecipesFromSeason(boolean season);
    public void generateRecipesFromHoliday(boolean holiday);
    public void generateRecipesFromDrinkItem(boolean drinkItem);
    // TODO you will need these for the different use cases
}