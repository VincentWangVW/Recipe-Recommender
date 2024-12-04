package entity;

/**
 * Represents an ingredient in a recipe.
 */
public class CommonIngredient implements Ingredient {
    private final String name;
    private final int amount;

    /**
     * Constructs a {@code CommonIngredient} instance.
     * @param name the name of the ingredient
     * @param amount the amount of the ingredient
     */
    public CommonIngredient(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Returns the name of the ingredient.
     * @return the name of the ingredient.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the amount of the ingredient.
     * @return the amount of the ingredient.
     */
    @Override
    public int getAmount() {
        return amount;
    }
}
