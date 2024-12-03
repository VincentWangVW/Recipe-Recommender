package use_case.user_info;

import java.util.HashSet;

import entity.UserPreferences;

/**
 * The UserInfoInteractor class implements the UserInfoInputBoundary interface and provides
 * functionality for managing user allergies, shopping amounts, and navigating to the main screen.
 * It interacts with the UserPreferences object to update the user's preferences and the output boundary
 * to notify changes.
 */
public class UserInfoInteractor implements UserInfoInputBoundary {
    private final HashSet<String> allergies = new HashSet<>();
    private final UserInfoOutputBoundary outputBoundary;
    private final UserPreferences userPreferences;

    /**
     * Constructs a new UserInfoInteractor.
     *
     * @param outputBoundary the output boundary used to navigate the user interface
     * @param userPreferences the user preferences object that stores the user's allergies and shopping limits
     */
    public UserInfoInteractor(UserInfoOutputBoundary outputBoundary, UserPreferences userPreferences) {
        this.outputBoundary = outputBoundary;
        this.userPreferences = userPreferences;
    }

    /**
     * Instructs the system to return to the main screen.
     */
    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    /**
     * Adds a new allergy to the user's profile.
     *
     * @param allergyName the name of the allergy to be added
     */
    public void addAllergy(String allergyName) {
        allergies.add(allergyName);
        userPreferences.addAllergy(allergyName);
    }

    /**
     * Deletes an allergy from the user's profile.
     *
     * @param allergyName the name of the allergy to be deleted
     */
    public void deleteAllergy(String allergyName) {
        allergies.remove(allergyName);
        userPreferences.removeAllergy(allergyName);
    }

    /**
     * Changes the user's shopping amount to the specified value.
     *
     * @param shopAmount the new shopping amount to be set
     * @return the updated shopping amount
     */
    public int changeShopAmount(int shopAmount) {
        userPreferences.setMissingIngredientsLimit(shopAmount);
        return shopAmount;
    }

    /**
     * Retrieves the list of allergies stored in the user's profile.
     *
     * @return an array of allergy names
     */
    @Override
    public String[] getAllergies() {
        return allergies.toArray(new String[0]);
    }
}
