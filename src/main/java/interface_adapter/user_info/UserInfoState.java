package interface_adapter.user_info;

import java.util.Set;

/**
 * Represents the state of user information, including shop amount and allergies.
 *
 * @null The shopAmount field may be null if not set.
 */
public class UserInfoState {
    private Integer shopAmount;
    private Set<String> allergies;

    /**
     * Gets the shop amount.
     *
     * @return the shop amount, or null if not set.
     */
    public Integer getShopAmount() {
        return shopAmount;
    }

    /**
     * Sets the shop amount.
     *
     * @param shopAmount the shop amount to set.
     */
    public void setShopAmount(Integer shopAmount) {
        this.shopAmount = shopAmount;
    }

    /**
     * Gets the user's allergies.
     *
     * @return the set of allergies.
     */
    public Set<String> getAllergies() {
        return allergies;
    }

    /**
     * Sets the user's allergies.
     *
     * @param allergies the set of allergies to set.
     */
    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }
}
