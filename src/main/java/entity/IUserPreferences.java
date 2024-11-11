package entity;

import java.util.HashSet;

public interface IUserPreferences {

    /**
     * Adds a specific allergy to the user's allergy list.
     *
     * @param name the name of the allergy to add.
     */
    void add_allergies(String name);

    /**
     * Sets a limit on the number of items to buy for the user.
     *
     * @param num the maximum number of items the user can buy.
     */
    void set_itemsToBuyLimit(int num);

    /**
     * Adds a dietary restriction to the user's dietary restrictions list.
     *
     * @param name the name of the dietary restriction to add.
     */
    void add_dietaryRestrictions(String name);

    /**
     * Sets whether the user includes alcohol in their preferences.
     *
     * @param include {@code true} if alcohol should be included; {@code false} otherwise.
     */
    void includeAlcohol(boolean include);

    /**
     * Checks if the user has included alcohol in their preferences.
     *
     * @return {@code true} if alcohol is included; {@code false} otherwise.
     */
    boolean hasAlcohol();

    /**
     * Gets the user's limit on the number of items they can buy.
     *
     * @return the maximum number of items the user can buy.
     */
    int getLimit();

    /**
     * Retrieves the user's dietary restrictions as a set.
     *
     * @return a {@code HashSet<String>} containing the user's dietary restrictions.
     */
    HashSet<String> getDietaryRestrictions();

    /**
     * Retrieves the user's allergies as a set.
     *
     * @return a {@code HashSet<String>} containing the user's allergies.
     */

    HashSet<String> getAllergies();

}
