package use_case.generated_manager;

import data_access.SpoonacularDAO;
import entity.Recipe;
import use_case.manage_ingredients.IngredientsInteractor;

import java.io.IOException;
import java.util.ArrayList;

public class GeneratedInteractor implements GeneratedInputBoundary {
    private final GeneratedOutputBoundary outputBoundary;
    private final IngredientsInteractor ingredientsInteractor;
    private final SpoonacularDAO spoonacularDAO;

    public GeneratedInteractor(GeneratedOutputBoundary outputBoundary, IngredientsInteractor ingredientsInteractor) {
        this.outputBoundary = outputBoundary;
        this.ingredientsInteractor = ingredientsInteractor;
        this.spoonacularDAO = new SpoonacularDAO();
    }

    @Override
    public void return_to_main() {
        outputBoundary.return_to_main();
    }

    public ArrayList<Recipe> generateRecipes() {
        boolean userInfo = outputBoundary.getViewManagerModel().isUserInfo();
        String selectedType = outputBoundary.getViewManagerModel().getSelectedType();
        ArrayList<String> ingredients = ingredientsInteractor.getIngredientsNEW();

        System.out.println("selectedType: " + selectedType);
        System.out.println("userInfo: " + userInfo);

        if (userInfo) {
            // TODO
        }
        if (selectedType.equals("Ingredients")) {
            try {
                return spoonacularDAO.getRecipesFromIngredients(ingredients, 0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("messed up");
        return null;
    }
}