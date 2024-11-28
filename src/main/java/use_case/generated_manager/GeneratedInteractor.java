package use_case.generated_manager;

import data_access.SpoonacularDAO;
import entity.Recipe;
import use_case.manage_ingredients.IngredientsInteractor;
import use_case.recommend_holiday.HolidayInteractor;
import use_case.recommend_season.SeasonInteractor;

import java.io.IOException;
import java.util.ArrayList;

public class GeneratedInteractor implements GeneratedInputBoundary {
    private final GeneratedOutputBoundary outputBoundary;
    private final IngredientsInteractor ingredientsInteractor;
    private final SeasonInteractor seasonInteractor;
    private final SpoonacularDAO spoonacularDAO;
    private final HolidayInteractor holidayInteractor;

    public GeneratedInteractor(GeneratedOutputBoundary outputBoundary, IngredientsInteractor ingredientsInteractor,
                               SeasonInteractor seasonInteractor, HolidayInteractor holidayInteractor) {
        this.outputBoundary = outputBoundary;
        this.ingredientsInteractor = ingredientsInteractor;
        this.holidayInteractor = holidayInteractor;
        this.spoonacularDAO = new SpoonacularDAO();
        this.seasonInteractor = seasonInteractor;
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    @Override
    public ArrayList<Recipe> generateRecipes() {
        boolean userInfo = outputBoundary.getViewManagerModel().isUserInfo();
        String selectedType = getGenerationType();
        ArrayList<String> ingredients = ingredientsInteractor.getIngredientsNEW();

        System.out.println("selectedType: " + selectedType);
        System.out.println("userInfo: " + userInfo);

        if (userInfo) {
            // TODO
        }
        switch (selectedType) {
            case "Ingredients" -> {
                try {
                    return spoonacularDAO.getRecipesFromIngredients(ingredients, 0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Season" -> {
                try {
                    String season = seasonInteractor.getSeason();
                    return spoonacularDAO.getRecipesFromQuery(season);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Holiday" -> {
                try {
                    String holiday = getHoliday();
                    return spoonacularDAO.getRecipesFromQuery(holiday);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "Custom" -> {
            }
            // TODO
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