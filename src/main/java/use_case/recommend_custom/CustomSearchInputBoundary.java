package use_case.recommend_custom;

public interface CustomSearchInputBoundary {
    /**
     * Performs a custom recipe search based on the user's preferences and ingredients.
     * @param followUserInfo If true, filters the results based on user preferences.
     */
    void performCustomSearch(boolean followUserInfo);
}