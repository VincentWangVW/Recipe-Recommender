package use_case.generated_manager;

import java.util.ArrayList;

import entity.Recipe;
import entity.UserPreferences;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_custom.CustomSearchInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_season.SeasonInteractor;

/**
 * The GeneratedInteractor class implements the GeneratedInputBoundary interface
 * and provides methods for generating recipes based on various types and managing output.
 */
public class GeneratedInteractor implements GeneratedInputBoundary {
    private final GeneratedOutputBoundary outputBoundary;
    private final IngredientsInteractor ingredientsInteractor;
    private final SeasonInteractor seasonInteractor;
    private final HolidayInteractor holidayInteractor;
    private final CustomSearchInteractor customSearchInteractor;
    private final UserPreferences userPreferences;

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

    @Override
    public void returnTomain() {
        outputBoundary.returnTomain();
    }

    @Override
    public ArrayList<Recipe> generateRecipes() {
        final boolean userInfo = outputBoundary.getViewManagerModel().isUserInfo();
        final String selectedType = getGenerationType();
        final String custom = outputBoundary.getViewManagerModel().getCustom();
        final ArrayList<String> ingredients = ingredientsInteractor.getIngredientsArray();

        return switch (selectedType) {
            case "Ingredients" -> ingredientsInteractor.getRecipesFromIngredients(ingredients,
                    userPreferences, userInfo);
            case "Season" -> seasonInteractor.getRecipesFromSeason(userPreferences, userInfo);
            case "Holiday" -> holidayInteractor.getRecipesFromHoliday(userPreferences, userInfo);
            case "Custom" -> customSearchInteractor.getRecipesFromCustom(custom, userPreferences, userInfo);
            default -> null;
        };
    }

    @Override
    public String getGenerationType() {
        return outputBoundary.getViewManagerModel().getSelectedType();
    }

    @Override
    public String getHoliday() {
        return holidayInteractor.getHoliday();
    }
}
