package entity;

/**
 * A factory for creating common recipes.
 */
public class CommonRecipeFactory implements RecipeFactory {

    /**
     * Creates a common recipe with the given name, URL, and number of missing items.
     * @param name the name of the recipe
     * @param url the URL of the recipe
     * @param missingItems the number of missing items
     * @return a common recipe with the given name, URL, and number of missing items
     */
    @Override
    public Recipe create(String name, String url, Integer missingItems) {
        return new CommonRecipe(name, url, missingItems);
    }
}
