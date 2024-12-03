package interface_adapter.ingredients_manager;

import java.util.HashMap;
import java.util.List;

/**
 * The state class responsible for storing ingredients data and error messages.
 * This class holds the ingredient information and any associated error messages
 * that may occur during ingredient management.
 */
public class IngredientsState {
    private HashMap<Integer, List<String>> ingredientsInfo;
    private String errorMessage;

    /**
     * Gets the stored ingredients information.
     * @return a map where the key is an integer (ingredient identifier)
     *         and the value is a list of strings representing ingredient details.
     */
    public HashMap<Integer, List<String>> getingredientsInfoInfo() {
        return ingredientsInfo;
    }

    /**
     * Sets the ingredients information in the state.
     * @param ingredientsInfo a map containing ingredient details,
     *                        where the key is the ingredient identifier
     *                        and the value is a list of strings.
     */
    public void setRecipeInfo(HashMap<Integer, List<String>> ingredientsInfo) {
        this.ingredientsInfo = ingredientsInfo;
    }
}
