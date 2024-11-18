package entity;

/**
 * Factory for creating Ingredient objects.
 */
public interface IngredientFactory {
    /**
     * Creates a new Ingredient.
     * @param name the name of the new ingredient
     * @param amount the amount of the new ingredient
     * @return the new ingredient
     */
    Ingredient create(String name, int amount);
}