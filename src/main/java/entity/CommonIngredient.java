package entity;

/**
 * Represents an ingredient in a recipe.
 */
public class CommonIngredient implements Ingredient{
    private final String name;
    private final int amount;
    private final String unit;

    public CommonIngredient(String name, int amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String getUnit() {
        return unit;
    }
}
