package interface_adapter.user_info;

import java.util.List;

/**
 * Interface for accessing user information related to shopping preferences and allergies.
 * This interface provides methods to retrieve the user's shopping amount and allergies.
 */
public interface UserInfoDataAccessInterface {

    /**
     * Retrieves the shopping amount for the user.
     *
     * @return The shopping amount set for the user.
     */
    Integer getShopAmount();

    /**
     * Retrieves the list of allergies associated with the user.
     *
     * @return A list of allergy names.
     */
    List<String> getAllergies();
}
