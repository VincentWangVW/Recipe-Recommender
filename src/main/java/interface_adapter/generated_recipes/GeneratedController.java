// src/main/java/interface_adapter/generated_recipes/GeneratedController.java
package interface_adapter.generated_recipes;

import entity.Recipe;
import interface_adapter.recommend_recipes.RecipesController;
import use_case.generated_manager.GeneratedInputBoundary;

import java.util.ArrayList;

public class GeneratedController {
    private final GeneratedInputBoundary generatedInputBoundary;

    public GeneratedController(GeneratedInputBoundary inputBoundary) {
        this.generatedInputBoundary = inputBoundary;
    }

    public void return_to_main() {
        generatedInputBoundary.returnTomain();
    }

    public ArrayList<Recipe> generateRecipes() {
        return generatedInputBoundary.generateRecipes();
    }

    public String getGenerationType() {
        return generatedInputBoundary.getGenerationType();
    }

    public String getHoliday() {
        return generatedInputBoundary.getHoliday();
    }
}