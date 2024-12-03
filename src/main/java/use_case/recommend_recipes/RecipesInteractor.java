package use_case.recommend_recipes;

import use_case.recommend_holiday.HolidayInteractor;

/**
 * The RecipesInteractor class implements the RecipesInputBoundary interface
 * and provides functionality for navigating recipe-related views.
 */
public class RecipesInteractor implements RecipesInputBoundary {
    private final RecipesOutputBoundary outputBoundary;
    private final HolidayInteractor holidayInteractor;

    /**
     * Constructs a RecipesInteractor instance.
     *
     * @param outputBoundary   The output boundary to interact with the recipe view.
     * @param holidayInteractor The interactor for retrieving holiday-related data.
     */
    public RecipesInteractor(RecipesOutputBoundary outputBoundary, HolidayInteractor holidayInteractor) {
        this.outputBoundary = outputBoundary;
        this.holidayInteractor = holidayInteractor;
    }

    @Override
    public void returnTomain() {
        outputBoundary.return_to_main();
    }

    @Override
    public void goToGenerated(String selectedType, boolean userInfo, String custom) {
        outputBoundary.go_to_generated(selectedType, userInfo, custom);
    }

    @Override
    public String getHoliday() {
        return holidayInteractor.getHoliday();
    }
}
