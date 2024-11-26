package interface_adapter.custom_search;

import use_case.recommend_custom.CustomSearchInputBoundary;

public class CustomSearchController {
    private final CustomSearchInputBoundary customSearchInteractor;

    // Constructor to inject the interactor
    public CustomSearchController(CustomSearchInputBoundary customSearchInteractor) {
        this.customSearchInteractor = customSearchInteractor;
    }

    /**
     * Initiates the custom recipe search with the given user preferences.
     * @param followUserInfo If true, filters recipes based on user preferences.
     */
    public void performCustomSearch(boolean followUserInfo) {
        customSearchInteractor.performCustomSearch(followUserInfo);
    }

    /**
     * Returns to the main screen.
     */
    public void returnToMain() {
        // Optional: Add logic here if needed (like saving state).
        // For now, this can call the presenter's `returnToMain` method directly.
    }
}

