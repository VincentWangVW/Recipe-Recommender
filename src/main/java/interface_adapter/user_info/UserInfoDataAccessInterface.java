package interface_adapter.user_info;

import java.util.List;

/**
 * Interface for user information data access.
 */
public interface UserInfoDataAccessInterface {

    /**
     * Retrieves the shop amount set for the user.
     *
     * @return The shop amount as an Integer, or {@code null} if not set.
     */
    Integer getShopAmount();

    /**
     * Retrieves the list of user allergies.
     *
     * @return A list of allergy names as Strings, or {@code null} if no allergies are recorded.
     */
    List<String> getAllergies();
}
