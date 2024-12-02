package entity;

public class UserPreferences implements IUserPreferences{
    int missingIngredientsLimit;
    boolean dairyFree;
    boolean glutenFree;
    String[] allergies;

    public UserPreferences(int missingIngredientsLimit, boolean dairyFree, boolean glutenFree, String[] allergies) {
        this.missingIngredientsLimit = missingIngredientsLimit;
        this.dairyFree = dairyFree;
        this.glutenFree = glutenFree;
        this.allergies = allergies;
    }

    @Override
    public int getMissingIngredientsLimit() {
        return missingIngredientsLimit;
    }

    @Override
    public boolean getDairyFree() {
        return dairyFree;
    }

    @Override
    public boolean getGlutenFree() {
        return glutenFree;
    }

    @Override
    public String[] getAllergies() {
        return allergies;
    }

    @Override
    public void addAllergy(String allergyName) {
        String[] newAllergies = new String[allergies.length + 1];
        System.arraycopy(allergies, 0, newAllergies, 0, allergies.length);
        newAllergies[allergies.length] = allergyName;
        allergies = newAllergies;
    }

    @Override
    public void removeAllergy(String allergyName) {
        String[] newAllergies = new String[allergies.length - 1];
        int index = 0;
        for (String allergy : allergies) {
            if (!allergy.equals(allergyName)) {
                newAllergies[index++] = allergy;
            }
        }
        allergies = newAllergies;
    }

    @Override
    public void setMissingIngredientsLimit(int shopAmount) {
        missingIngredientsLimit = shopAmount;
    }
}