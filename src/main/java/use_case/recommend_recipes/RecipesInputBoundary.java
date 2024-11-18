package use_case.recommend_recipes;

public interface RecipesInputBoundary {
    void return_to_main();
    void go_to_generated(String selectedType, boolean userInfo);
}