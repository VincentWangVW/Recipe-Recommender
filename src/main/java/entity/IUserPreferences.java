package entity;

import java.util.HashSet;

public interface IUserPreferences {
    void add_allergies(String name);
    void set_itemsToBuyLimit(int num);
    void add_dietaryRestrictions(String name);
    void includeAlcohol(boolean include);
    boolean hasAlcohol();
    int getLimit();
    HashSet<String> getDietaryRestrictions();
    HashSet<String> getAllergies();

}
