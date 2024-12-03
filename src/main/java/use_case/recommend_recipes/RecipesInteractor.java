package use_case.recommend_recipes;

import use_case.recommend_holiday.HolidayInteractor;

/**
 * The RecipesInteractor class implements the RecipesInputBoundary interface, handling user interactions
 * related to recipe recommendations. It coordinates with the HolidayInteractor to retrieve holiday information
 * and communicates with the RecipesOutputBoundary to navigate between different screens.
 */
public class RecipesInteractor implements RecipesInputBoundary {
    private final RecipesOutputBoundary outputBoundary;
    private final HolidayInteractor holidayInteractor;

    /**
     * Constructs a new RecipesInteractor instance.
     *
     * @param outputBoundary the output boundary that handles UI updates or screen transitions
     * @param holidayInteractor the HolidayInteractor instance used to retrieve holiday information
     */
    public RecipesInteractor(RecipesOutputBoundary outputBoundary, HolidayInteractor holidayInteractor) {
        this.outputBoundary = outputBoundary;
        this.holidayInteractor = holidayInteractor;
    }

    /**
     * Returns to the main screen by invoking the return_to_main method on the output boundary.
     */
    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    /**
     * Navigates to the generated recipes screen with the provided parameters.
     *
     * @param selectedType the type of recipe generation (e.g., ingredients, season, holiday, etc.)
     * @param userInfo a boolean flag indicating if user-specific information should be considered
     * @param custom a custom string parameter for personalized recipe searches
     */
    @Override
    public void go_to_generated(String selectedType, boolean userInfo, String custom) {
        outputBoundary.go_to_generated(selectedType, userInfo, custom);
    }

    /**
     * Retrieves the current holiday information by calling the getHoliday method on the HolidayInteractor.
     *
     * @return the current holiday as a String
     */
    @Override
    public String getHoliday() {
        return holidayInteractor.getHoliday();
    }
}
