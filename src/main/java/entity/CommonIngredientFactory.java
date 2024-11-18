package entity;

public class CommonIngredientFactory implements IngredientFactory {
    @Override
    public Ingredient create(String name, int amount) {
        return new CommonIngredient(name, amount);
    }
}