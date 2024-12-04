package use_case.user_info;

/**
 * Interface defining the input boundary for user information management operations.
 */
public interface UserInfoInputBoundary {

    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Adds an allergy to the user's allergy list.
     *
     * @param allergyName The name of the allergy to be added.
     */
    void addAllergy(String allergyName);

    /**
     * Deletes an allergy from the user's allergy list.
     *
     * @param allergyName The name of the allergy to be removed.
     */
    void deleteAllergy(String allergyName);

    /**
     * Changes the shop amount for the user.
     *
     * @param shopAmount The new shop amount to be set.
     * @return The updated shop amount.
     */
    int changeShopAmount(int shopAmount);

    /**
     * Retrieves the user's list of allergies.
     *
     * @return An array of allergy names.
     */
    String[] getAllergies();
}
