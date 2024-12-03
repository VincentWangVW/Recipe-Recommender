package interface_adapter.ingredients_manager;

import java.util.List;
import java.util.Map;

/**
 * Represents the state of the ingredients manager, holding
 * information about ingredients and any potential error messages.
 */
public class IngredientsState {
    private Map<Integer, List<String>> ingredientsInfo;
    private String errorMessage;

    /**
     * Retrieves the information about ingredients.
     *
     * @return A map where each entry contains an ingredient's ID and its details.
     */
    public Map<Integer, List<String>> getingredientsInfoInfo() {
        return ingredientsInfo;
    }

    public void setRecipeInfo(Map<Integer, List<String>> ingredientsInfo) {
        this.ingredientsInfo = ingredientsInfo;
    }
}
