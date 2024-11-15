package use_case.recommend_recipes;

import java.util.List;

public interface RecipesInputBoundary {
    void return_to_main();
    // TODO move these to respective input boundaries
    public void generateRecipesFromIngredients(boolean userInfo);
    public void generateRecipesFromSeason(boolean userInfo);
    public void generateRecipesFromHoliday(boolean userInfo);
    public void generateRecipesFromCustom(boolean followUserInfo);
}