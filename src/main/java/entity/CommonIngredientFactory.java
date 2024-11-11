package entity;

public class CommonIngredientFactory implements IngredientFactory {
    @Override
    public Ingredient create(String name, int amount, String unit) {
        return new CommonIngredient(name, amount, unit);
    }
}
