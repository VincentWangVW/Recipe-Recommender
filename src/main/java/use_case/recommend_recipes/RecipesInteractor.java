package use_case.recommend_recipes;

public class RecipesInteractor implements RecipesInputBoundary {
    private final RecipesOutputBoundary outputBoundary;

    public RecipesInteractor(RecipesOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    @Override
    public void generateRecipesFromIngredients(boolean userInfo) {
        // Implementation
        // TODO
    }

    @Override
    public void generateRecipesFromSeason(boolean userInfo) {
        // Implementation
        // TODO move to season
    }

    @Override
    public void generateRecipesFromHoliday(boolean userInfo) {
        // Implementation
        // TODO
    }

    @Override
    public void generateRecipesFromCustom(boolean followUserInfo) {

    }
}