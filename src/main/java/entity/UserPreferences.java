package entity;

/**
 * Represents the user's preferences.
 */
public class UserPreferences implements InterfaceUserPreferences {
    private int missingIngredientsLimit;
    private boolean dairyFree;
    private boolean glutenFree;
    private String[] allergies;

    /**
     * Constructs a {@code UserPreferences} instance.
     * @param missingIngredientsLimit the limit of missing ingredients
     * @param dairyFree whether the user is dairy free
     * @param glutenFree whether the user is gluten free
     * @param allergies the users allergies
     */
    public UserPreferences(int missingIngredientsLimit, boolean dairyFree, boolean glutenFree, String[] allergies) {
        this.missingIngredientsLimit = missingIngredientsLimit;
        this.dairyFree = dairyFree;
        this.glutenFree = glutenFree;
        this.allergies = allergies;
    }
    /**
     * Returns the limit of missing ingredients.
     * @return the limit of missing ingredients.
     */
    @Override
    public int getMissingIngredientsLimit() {
        return missingIngredientsLimit;
    }

    /**
     * Returns whether the user is dairy free.
     * @return whether the user is dairy free.
     */
    @Override
    public boolean getDairyFree() {
        return dairyFree;
    }

    /**
     * Returns whether the user is gluten free.
     * @return whether the user is gluten free.
     */
    @Override
    public boolean getGlutenFree() {
        return glutenFree;
    }

    /**
     * Returns the users allergies.
     * @return the users allergies.
     */
    @Override
    public String[] getAllergies() {
        return allergies;
    }

    /**
     * Adds an allergy to the users allergies.
     * @param allergyName the name of the allergy to add.
     */
    @Override
    public void addAllergy(String allergyName) {
        final String[] newAllergies = new String[allergies.length + 1];
        System.arraycopy(allergies, 0, newAllergies, 0, allergies.length);
        newAllergies[allergies.length] = allergyName;
        allergies = newAllergies;
    }

    /**
     * Removes an allergy from the users allergies.
     * @param allergyName the name of the allergy to remove.
     */
    @Override
    public void removeAllergy(String allergyName) {
        final String[] newAllergies = new String[allergies.length - 1];
        int index = 0;
        for (String allergy : allergies) {
            if (!allergy.equals(allergyName)) {
                newAllergies[index++] = allergy;
            }
        }
        allergies = newAllergies;
    }

    /**
     * Sets the users missing ingredients limit.
     * @param shopAmount the limit of missing ingredients.
     */
    @Override
    public void setMissingIngredientsLimit(int shopAmount) {
        missingIngredientsLimit = shopAmount;
    }
}
