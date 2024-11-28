package use_case.recommend_recipes;

import data_access.InMemoryDAO;

public class RecipesInteractor implements RecipesInputBoundary {
    private final RecipesOutputBoundary outputBoundary;
    private final InMemoryDAO inMemoryDAO;

    public RecipesInteractor(RecipesOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.inMemoryDAO = new InMemoryDAO();
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    @Override
    public void go_to_generated(String selectedType, boolean userInfo, String custom) {
        outputBoundary.go_to_generated(selectedType, userInfo, custom);
    }

    @Override
    public String getHoliday() {
        return inMemoryDAO.get_holiday();
    }
}