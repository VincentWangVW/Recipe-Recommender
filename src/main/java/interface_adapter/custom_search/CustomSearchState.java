package interface_adapter.custom_search;

import java.util.HashMap;
import java.util.List;

public class CustomSearchState {
    private HashMap<Integer, List<String>> recipes; // Stores the search results
    private String errorMessage; // Stores any error messages

    /**
     * Gets the custom search results.
     * @return A map of recipe IDs to their details.
     */
    public HashMap<Integer, List<String>> getRecipes() {
        return recipes;
    }

    /**
     * Sets the custom search results.
     * @param recipes A map of recipe IDs to their details.
     */
    public void setRecipes(HashMap<Integer, List<String>> recipes) {
        this.recipes = recipes;
    }

    /**
     * Gets the error message.
     * @return The error message, or null if no error.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
