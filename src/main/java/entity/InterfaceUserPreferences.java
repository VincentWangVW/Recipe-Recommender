package entity;

/**
 * Represents the user's preferences.
 */
public interface InterfaceUserPreferences {
    /**
     * Returns the limit of missing ingredients.
     * @return the limit of missing ingredients.
     */
    int getMissingIngredientsLimit();

    /**
     * Returns whether the user is dairy free.
     * @return whether the user is dairy free.
     */
    boolean getDairyFree();

    /**
     * Returns whether the user is gluten free.
     * @return whether the user is gluten free.
     */
    boolean getGlutenFree();

    /**
     * Returns the users allergies.
     * @return the users allergies.
     */
    String[] getAllergies();

    /**
     * Adds an allergy to the users allergies.
     * @param allergyName the name of the allergy to add.
     */
    void addAllergy(String allergyName);

    /**
     * Removes an allergy from the users allergies.
     * @param allergyName the name of the allergy to remove.
     */
    void removeAllergy(String allergyName);

    /**
     * Sets the users missing ingredients limit.
     * @param shopAmount the limit of missing ingredients.
     */
    void setMissingIngredientsLimit(int shopAmount);
}
