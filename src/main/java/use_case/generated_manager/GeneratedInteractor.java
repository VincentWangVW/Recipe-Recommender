package use_case.generated_manager;

import entity.Recipe;
import entity.UserPreferences;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_custom.CustomSearchInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_season.SeasonInteractor;

import java.util.ArrayList;

public class GeneratedInteractor implements GeneratedInputBoundary {
    private final GeneratedOutputBoundary outputBoundary;
    private final IngredientsInteractor ingredientsInteractor;
    private final SeasonInteractor seasonInteractor;
    private final HolidayInteractor holidayInteractor;
    private final CustomSearchInteractor customSearchInteractor;
    private final UserPreferences userPreferences;

    public GeneratedInteractor(GeneratedOutputBoundary outputBoundary, IngredientsInteractor ingredientsInteractor,
                               SeasonInteractor seasonInteractor, HolidayInteractor holidayInteractor, CustomSearchInteractor customSearchInteractor,
                               UserPreferences userPreferences) {
        this.outputBoundary = outputBoundary;
        this.ingredientsInteractor = ingredientsInteractor;
        this.holidayInteractor = holidayInteractor;
        this.seasonInteractor = seasonInteractor;
        this.customSearchInteractor = customSearchInteractor;
        this.userPreferences = userPreferences;
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    @Override
    public ArrayList<Recipe> generateRecipes() {
        boolean userInfo = outputBoundary.getViewManagerModel().isUserInfo();
        String selectedType = getGenerationType();
        String custom = outputBoundary.getViewManagerModel().getCustom();
        ArrayList<String> ingredients = ingredientsInteractor.getIngredientsNEW();

        switch (selectedType) {
            case "Ingredients" -> {
                return ingredientsInteractor.getRecipesFromIngredients(ingredients, userPreferences, userInfo);
            }
            case "Season" -> {
                return seasonInteractor.getRecipesFromSeason(userPreferences, userInfo);
            }
            case "Holiday" -> {
                return holidayInteractor.getRecipeFromHoliday(userPreferences, userInfo);
            }
            case "Custom" -> {
                return customSearchInteractor.getRecipesFromCustom(custom, userPreferences, userInfo);
            }
        }
        return null;
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