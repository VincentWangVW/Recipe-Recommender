package entity;

/**
 * Represents an ingredient in a recipe.
 */
public class CommonIngredient implements Ingredient{
    private final String name;
    private final int amount;

    public CommonIngredient(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
