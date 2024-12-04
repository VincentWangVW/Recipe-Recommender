package entity;

/**
 * Represents a recipe.
 */
public class CommonRecipe implements Recipe {
    private final String name;
    private final String url;
    private final Integer missingItems;

    /**
     * Constructs a {@code CommonRecipe} instance.
     * @param name the name of the recipe
     * @param url the URL of the recipe
     * @param missingItems the number of missing items
     */
    public CommonRecipe(String name, String url, Integer missingItems) {
        this.name = name;
        this.url = url;
        this.missingItems = missingItems;
    }

    /**
     * Returns the name of the recipe.
     * @return the name of the recipe.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the URL of the recipe.
     * @return the URL of the recipe.
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * Returns the number of missing items.
     * @return the number of missing items.
     */
    @Override
    public Integer getMissingItems() {
        return missingItems;
    }
}
