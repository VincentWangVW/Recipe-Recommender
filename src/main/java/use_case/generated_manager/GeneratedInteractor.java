package use_case.generated_manager;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_custom.CustomSearchInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_season.SeasonInteractor;

/**
 * The GeneratedInteractor class is the implementation of the GeneratedInputBoundary interface.
 * It handles the business logic for generating recipes based on various selection criteria,
 * such as ingredients, season, holiday, or custom queries. The interactor uses various use cases
 * (e.g., IngredientsInteractor, SeasonInteractor, HolidayInteractor, CustomSearchInteractor)
 * to gather and generate recipes based on the user's preferences.
 */
public class GeneratedInteractor implements GeneratedInputBoundary {
    private final GeneratedOutputBoundary outputBoundary;
    private final IngredientsInteractor ingredientsInteractor;
    private final SeasonInteractor seasonInteractor;
    private final HolidayInteractor holidayInteractor;
    private final CustomSearchInteractor customSearchInteractor;
    private final UserPreferences userPreferences;

    /**
     * Constructor for the GeneratedInteractor class. Initializes the necessary interactors
     * and output boundary to manage the recipe generation process.
     *
     * @param outputBoundary the output boundary used for communication with the presenter
     * @param ingredientsInteractor the interactor that manages ingredients-related logic
     * @param seasonInteractor the interactor that manages season-based logic
     * @param holidayInteractor the interactor that manages holiday-based logic
     * @param customSearchInteractor the interactor that handles custom search logic
     * @param userPreferences the user's preferences to tailor the recipe generation
     */
    public GeneratedInteractor(GeneratedOutputBoundary outputBoundary, IngredientsInteractor ingredientsInteractor,
                               SeasonInteractor seasonInteractor, HolidayInteractor holidayInteractor,
                               CustomSearchInteractor customSearchInteractor,
                               UserPreferences userPreferences) {
        this.outputBoundary = outputBoundary;
        this.ingredientsInteractor = ingredientsInteractor;
        this.holidayInteractor = holidayInteractor;
        this.seasonInteractor = seasonInteractor;
        this.customSearchInteractor = customSearchInteractor;
        this.userPreferences = userPreferences;
    }

    /**
     * Returns to the main view by invoking the corresponding method from the output boundary.
     */
    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    /**
     * Generates a list of recipes based on the selected generation type and user preferences.
     * The available generation types include "Ingredients", "Season", "Holiday", and "Custom".
     * The appropriate interactor is called based on the selected type to retrieve the recipes.
     *
     * @return a list of generated recipes based on the user's selection and preferences
     */
    @Override
    public ArrayList<Recipe> generateRecipes() {
        final boolean userInfo = outputBoundary.getViewManagerModel().isUserInfo();
        final String selectedType = getGenerationType();
        final String custom = outputBoundary.getViewManagerModel().getCustom();
        final ArrayList<String> ingredients = ingredientsInteractor.getIngredientsArray();

        switch (selectedType) {
            case "Ingredients" -> {
                return ingredientsInteractor.getRecipesFromIngredients(ingredients, userPreferences, userInfo);
            }
            case "Season" -> {
                return seasonInteractor.getRecipesFromSeason(userPreferences, userInfo);
            }
            case "Holiday" -> {
                return holidayInteractor.getRecipesFromHoliday(userPreferences, userInfo);
            }
            case "Custom" -> {
                return customSearchInteractor.getRecipesFromCustom(custom, userPreferences, userInfo);
            }
        }
        return null;
    }

    /**
     * Gets the selected generation type from the output boundary, which determines the recipe generation method.
     *
     * @return the generation type (e.g., "Ingredients", "Season", "Holiday", "Custom")
     */
    @Override
    public String getGenerationType() {
        return outputBoundary.getViewManagerModel().getSelectedType();
    }

    /**
     * Gets the holiday information from the holiday interactor, which is used for generating holiday-based recipes.
     *
     * @return the holiday associated with the recipe generation, if any
     */
    @Override
    public String getHoliday() {
        return holidayInteractor.getHoliday();
    }
}
