package use_case.generated_manager;

import entity.Recipe;

import java.util.ArrayList;

public interface GeneratedInputBoundary {
    void return_to_main();

    ArrayList<Recipe> generateRecipes();

    String getGenerationType();

    String getHoliday();
}