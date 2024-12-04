package use_case.user_info;

import java.util.Set;
import java.util.TreeSet;

import entity.UserPreferences;

/**
 * Interactor class for managing user information such as allergies and shopping preferences.
 */
public class UserInfoInteractor implements UserInfoInputBoundary {
    private final Set<String> allergies = new TreeSet<>();
    private final UserInfoOutputBoundary outputBoundary;
    private final UserPreferences userPreferences;

    /**
     * Constructs a UserInfoInteractor instance.
     *
     * @param outputBoundary   The output boundary to interact with the user interface.
     * @param userPreferences  The user preferences entity to manage data.
     */
    public UserInfoInteractor(UserInfoOutputBoundary outputBoundary, UserPreferences userPreferences) {
        this.outputBoundary = outputBoundary;
        this.userPreferences = userPreferences;
    }

    @Override
    public void returnTomain() {
        outputBoundary.returnTomain();
    }

    /**
     * Adds an allergy to the user's list of allergies.
     *
     * @param allergyName The name of the allergy to add.
     */
    public void addAllergy(String allergyName) {
        allergies.add(allergyName);
        userPreferences.addAllergy(allergyName);
    }

    /**
     * Removes an allergy from the user's list of allergies.
     *
     * @param allergyName The name of the allergy to remove.
     */
    public void deleteAllergy(String allergyName) {
        allergies.remove(allergyName);
        userPreferences.removeAllergy(allergyName);
    }

    /**
     * Changes the limit for missing ingredients in the user's shopping preferences.
     *
     * @param shopAmount The new limit for missing ingredients.
     * @return The updated shop amount.
     */
    public int changeShopAmount(int shopAmount) {
        userPreferences.setMissingIngredientsLimit(shopAmount);
        return shopAmount;
    }

    @Override
    public String[] getAllergies() {
        return allergies.toArray(new String[0]);
    }
}
