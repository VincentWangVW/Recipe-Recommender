package use_case.recommend_recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface RecipesOutputBoundary {
    void presentRecipes(HashMap<Integer, List<String>> recipeInfo);
    // TODO

    void return_to_main();
}
