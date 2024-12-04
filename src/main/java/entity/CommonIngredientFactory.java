package entity;

/**
 * A factory for creating common ingredients.
 */
public class CommonIngredientFactory implements IngredientFactory {

    /**
     * Creates a common ingredient with the given name and amount.
     * @param name the name of the ingredient
     * @param amount the amount of the ingredient
     * @return a common ingredient with the given name and amount
     */
    @Override
    public Ingredient create(String name, int amount) {
        return new CommonIngredient(name, amount);
    }
}
