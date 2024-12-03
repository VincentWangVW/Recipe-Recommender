package interface_adapter.user_info;

import use_case.user_info.UserInfoInputBoundary;

/**
 * Controller class for managing user information interactions related to allergies and shopping preferences.
 * This class acts as an intermediary between the input boundary and the view, handling user commands and actions.
 */
public class UserInfoController {
    private final UserInfoInputBoundary userInfoInputBoundary;

    /**
     * Constructor for UserInfoController.
     * Initializes the controller with the given UserInfoInputBoundary to handle business logic.
     *
     * @param userInfoInputBoundary The input boundary used to process user information logic.
     */
    public UserInfoController(UserInfoInputBoundary userInfoInputBoundary) {
        this.userInfoInputBoundary = userInfoInputBoundary;
    }

    /**
     * Adds a new allergy to the user's profile.
     *
     * @param allergyName The name of the allergy to be added.
     */
    public void addAllergy(String allergyName) {
        userInfoInputBoundary.addAllergy(allergyName);
    }

    /**
     * Deletes an allergy from the user's profile.
     *
     * @param allergyName The name of the allergy to be deleted.
     */
    public void deleteAllergy(String allergyName) {
        userInfoInputBoundary.deleteAllergy(allergyName);
    }

    /**
     * Returns the user to the main screen.
     */
    public void return_to_main() {
        userInfoInputBoundary.return_to_main();
    }

    /**
     * Retrieves the list of allergies for the user.
     *
     * @return An array of allergy names.
     */
    public String[] getAllergies() {
        return userInfoInputBoundary.getAllergies();
    }

    /**
     * Changes the shopping amount for the user.
     *
     * @param shopAmount The new shopping amount to be set.
     */
    public void changeShopAmount(int shopAmount) {
        userInfoInputBoundary.changeShopAmount(shopAmount);
    }
}
