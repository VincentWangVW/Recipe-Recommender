package use_case.generated_manager;

import data_access.SpoonacularDAO;
import entity.Recipe;
import entity.UserPreferences;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_season.SeasonInteractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GeneratedInteractor implements GeneratedInputBoundary {
    private final GeneratedOutputBoundary outputBoundary;
    private final IngredientsInteractor ingredientsInteractor;
    private final SeasonInteractor seasonInteractor;
    private final SpoonacularDAO spoonacularDAO;
    private final HolidayInteractor holidayInteractor;
    private final UserPreferences userPreferences;

    public GeneratedInteractor(GeneratedOutputBoundary outputBoundary, IngredientsInteractor ingredientsInteractor,
                               SeasonInteractor seasonInteractor, HolidayInteractor holidayInteractor,
                               UserPreferences userPreferences) {
        this.outputBoundary = outputBoundary;
        this.ingredientsInteractor = ingredientsInteractor;
        this.holidayInteractor = holidayInteractor;
        this.spoonacularDAO = new SpoonacularDAO();
        this.seasonInteractor = seasonInteractor;
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

        System.out.println("selectedType: " + selectedType);
        System.out.println("userInfo: " + userInfo);
        System.out.println("missing items: " + userPreferences.getMissingIngredientsLimit());
        System.out.println("allergies: " + Arrays.toString(userPreferences.getAllergies()));

        UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);

        switch (selectedType) {
            case "Ingredients" -> {
                try {
                    if (userInfo) {
                        return spoonacularDAO.getRecipesFromIngredients(ingredients, userPreferences);
                    }
                    else {
                        return spoonacularDAO.getRecipesFromIngredients(ingredients, nullPreferences);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Season" -> {
                try {
                    String season = seasonInteractor.getSeason();
                    if (userInfo) {
                        return spoonacularDAO.getRecipesFromQuery(season, userPreferences);
                    }
                    else {
                        return spoonacularDAO.getRecipesFromQuery(season, nullPreferences);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Holiday" -> {
                try {
                    String holiday = getHoliday();
                    if (userInfo) {
                        return spoonacularDAO.getRecipesFromQuery(holiday, userPreferences);
                    }
                    else {
                        return spoonacularDAO.getRecipesFromQuery(holiday, nullPreferences);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Custom" -> {
                try {
                    if (userInfo) {
                        return spoonacularDAO.getRecipesFromQuery(custom, userPreferences);
                    }
                    else {
                        return spoonacularDAO.getRecipesFromQuery(custom, nullPreferences);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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