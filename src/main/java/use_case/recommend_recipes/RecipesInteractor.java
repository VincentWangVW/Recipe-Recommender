package use_case.recommend_recipes;

import use_case.recommend_holiday.HolidayInteractor;

public class RecipesInteractor implements RecipesInputBoundary {
    private final RecipesOutputBoundary outputBoundary;
    private final HolidayInteractor holidayInteractor;

    public RecipesInteractor(RecipesOutputBoundary outputBoundary, HolidayInteractor holidayInteractor) {
        this.outputBoundary = outputBoundary;
        this.holidayInteractor = holidayInteractor;
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
        return holidayInteractor.getHoliday();
    }
}