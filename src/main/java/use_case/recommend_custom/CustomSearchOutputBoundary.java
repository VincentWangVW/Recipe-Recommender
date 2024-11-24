package use_case.recommend_custom;

import java.util.HashMap;
import java.util.List;

public interface CustomSearchOutputBoundary {
    /**
     * Passes the results of the custom recipe search to the presenter.
     * @param recipes A map where each entry contains a recipe's ID and its details.
     */
    void presentCustomSearchResults(HashMap<Integer, List<String>> recipes);

    /**
     * Passes an error message to the presenter in case of a failure.
     * @param errorMessage The error message to be displayed.
     */
    void presentCustomSearchError(String errorMessage);
}
