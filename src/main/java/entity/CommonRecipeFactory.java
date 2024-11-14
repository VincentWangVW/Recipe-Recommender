package entity;

import java.util.ArrayList;

public class CommonRecipeFactory implements RecipeFactory {
    @Override
    public Recipe create(String name, String url, Integer missingItems) {
        return new CommonRecipe(name, url, missingItems);
    }
}
