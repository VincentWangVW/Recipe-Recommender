package entity;

/**
 * Factory for creating Recipe objects.
 */
public interface RecipeFactory {
    /**
     * Creates a new Recipe.
     * @param name the name of the new recipe
     * @param url the url of the new recipe
     * @param missingItems the missing items of the new recipe
     * @return the new recipe
     */
    Recipe create(String name, String url, Integer missingItems);
}
