package interface_adapter.user_info;

import use_case.user_info.UserInfoInputBoundary;

/**
 * The UserInfoController class acts as the controller for managing user information.
 * It communicates with the UserInfoInputBoundary to handle user-related operations,
 * such as managing allergies and updating shopping preferences.
 */
public class UserInfoController {
    private final UserInfoInputBoundary userInfoInputBoundary;

    /**
     * Constructs a UserInfoController with the specified input boundary.
     *
     * @param userInfoInputBoundary The input boundary to interact with user information use cases.
     */
    public UserInfoController(UserInfoInputBoundary userInfoInputBoundary) {
        this.userInfoInputBoundary = userInfoInputBoundary;
    }

    /**
     * Adds a new allergy to the user's list of allergies.
     *
     * @param allergyName The name of the allergy to be added.
     */
    public void addAllergy(String allergyName) {
        userInfoInputBoundary.addAllergy(allergyName);
    }

    /**
     * Deletes an allergy from the user's list of allergies.
     *
     * @param allergyName The name of the allergy to be removed.
     */
    public void deleteAllergy(String allergyName) {
        userInfoInputBoundary.deleteAllergy(allergyName);
    }

    /**
     * Returns the user to the main screen.
     */
    public void returnTomain() {
        userInfoInputBoundary.returnTomain();
    }

    public String[] getAllergies() {
        return userInfoInputBoundary.getAllergies();
    }

    /**
     * Updates the shopping amount preference for the user.
     *
     * @param shopAmount The new shopping amount to set.
     */
    public void changeShopAmount(int shopAmount) {
        userInfoInputBoundary.changeShopAmount(shopAmount);
    }
}
