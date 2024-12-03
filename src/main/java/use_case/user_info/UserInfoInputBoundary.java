package use_case.user_info;

/**
 * The UserInfoInputBoundary interface defines the contract for the input boundary
 * used in the user information management use case. It provides methods to manage
 * the user's allergies, shopping amount, and navigate back to the main screen.
 */
public interface UserInfoInputBoundary {

    /**
     * Instructs the system to return to the main screen.
     */
    void return_to_main();

    /**
     * Adds a new allergy to the user's profile.
     *
     * @param allergyName the name of the allergy to be added
     */
    void addAllergy(String allergyName);

    /**
     * Deletes an allergy from the user's profile.
     *
     * @param allergyName the name of the allergy to be deleted
     */
    void deleteAllergy(String allergyName);

    /**
     * Changes the user's shopping amount by the specified value.
     *
     * @param shopAmount the new shopping amount to be set
     * @return the updated shopping amount
     */
    int changeShopAmount(int shopAmount);

    /**
     * Retrieves the list of allergies stored in the user's profile.
     *
     * @return an array of allergy names
     */
    String[] getAllergies();
}
