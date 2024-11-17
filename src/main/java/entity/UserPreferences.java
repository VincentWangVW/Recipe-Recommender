package entity;
import java.util.HashSet;

public class UserPreferences implements IUserPreferences{
    private HashSet<String> allergies;
    private HashSet<String> dietaryRestrictions;
    private int limit;
    private boolean Alcohol;

    public UserPreferences(HashSet<String> allergies, HashSet<String> dietaryRestrictions) {
        this.allergies = allergies;
        this.dietaryRestrictions = dietaryRestrictions;

    }
    @Override
    public boolean hasAlcohol() {
        return Alcohol;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public HashSet<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    @Override
    public HashSet<String> getAllergies() {
        return allergies;
    }

    @Override
    public void add_allergies(String name) {
        allergies.add(name);
    }

    @Override
    public void set_itemsToBuyLimit(int num) {
        limit=num;
    }

    @Override
    public void add_dietaryRestrictions(String name) {
        dietaryRestrictions.add(name);
    }

    @Override
    public void includeAlcohol(boolean include) {
        Alcohol=include;
    }

}