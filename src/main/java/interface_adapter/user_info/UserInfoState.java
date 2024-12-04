package interface_adapter.user_info;

import java.util.HashSet;

/**
 * The UserInfoState class is responsible for holding the state of user information,
 * including the user's shopping amount and a list of allergies. This state is used
 * to manage and update user information across different components of the system.
 */
public class UserInfoState {
    private Integer shopAmount;
    private HashSet<String> allergies;

    /**
     * Gets the current shopping amount of the user.
     *
     * @return The shop amount as an Integer.
     */
    public Integer getShopAmount() {
        return shopAmount;
    }

    /**
     * Sets the shopping amount for the user.
     *
     * @param shopAmount The new shopping amount.
     */
    public void setShopAmount(Integer shopAmount) {
        this.shopAmount = shopAmount;
    }

    /**
     * Gets the list of allergies the user has.
     *
     * @return A HashSet containing the user's allergies.
     */
    public HashSet<String> getAllergies() {
        return allergies;
    }

    /**
     * Sets the list of allergies for the user.
     *
     * @param allergies A HashSet containing the new list of allergies.
     */
    public void setAllergies(HashSet<String> allergies) {
        this.allergies = allergies;
    }
}
