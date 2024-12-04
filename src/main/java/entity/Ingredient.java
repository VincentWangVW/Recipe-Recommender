package entity;

/**
 * Represents an ingredient in a recipe.
 */
public interface Ingredient {
    /**
     * Returns the name of the ingredient.
     * @return the name of the ingredient.
     */
    String getName();

    /**
     * Returns the amount of the ingredient.
     * @return the amount of the ingredient.
     */
    int getAmount();
}
