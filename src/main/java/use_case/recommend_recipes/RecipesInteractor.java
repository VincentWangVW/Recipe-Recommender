package use_case.recommend_recipes;

import data_access.SpoonacularDAO;
import entity.Recipe;
import use_case.manage_ingredients.IngredientsInteractor;

import java.io.IOException;
import java.util.ArrayList;

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
    public void go_to_generated(String selectedType, boolean userInfo) {
        outputBoundary.go_to_generated(selectedType, userInfo);
    }
}