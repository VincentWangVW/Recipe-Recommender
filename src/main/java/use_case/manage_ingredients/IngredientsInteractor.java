package use_case.manage_ingredients;

import java.util.ArrayList;
import java.util.List;

import data_access.SpoonacularDAO;
import entity.CommonIngredientFactory;
import entity.Ingredient;
import entity.IngredientFactory;
import entity.Recipe;
import entity.UserPreferences;

/**
 * The IngredientsInteractor class implements the IngredientsInputBoundary interface
 * and provides methods to manage ingredients and retrieve recipes.
 */
public class IngredientsInteractor implements IngredientsInputBoundary {
    private final List<Ingredient> localIngredientList = new ArrayList<>();
    private final IngredientsOutputBoundary outputBoundary;
    private final IngredientFactory ingredientFactory;
    private final SpoonacularDAO spoonacularDao;

    /**
     * Constructs an IngredientsInteractor instance with the specified output boundary.
     *
     * @param outputBoundary The output boundary to interact with the user interface.
     */
    public IngredientsInteractor(IngredientsOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
        this.ingredientFactory = new CommonIngredientFactory();
        this.spoonacularDao = new SpoonacularDAO();
    }

    @Override
    public void addIngredient(String ingredientName, int quantity) {
        boolean ingredientExists = false;
        for (Ingredient ingredient : localIngredientList) {
            if (ingredient.getName().equals(ingredientName)) {
                final int newAmount = ingredient.getAmount() + quantity;
                localIngredientList.remove(ingredient);
                localIngredientList.add(ingredientFactory.create(ingredientName, newAmount));
                ingredientExists = true;
                break;
            }
        }
        if (!ingredientExists) {
            final Ingredient newIngredient = ingredientFactory.create(ingredientName, quantity);
            localIngredientList.add(newIngredient);
        }
    }

    @Override
    public void deleteIngredient(String ingredientName) {
        localIngredientList.removeIf(ingredient -> ingredient.getName().equals(ingredientName));
    }

    @Override
    public int changeIngredientAmount(String ingredientName, int delta) {
        int newAmount = -1;
        for (int i = 0; i < localIngredientList.size(); i++) {
            final Ingredient ingredient = localIngredientList.get(i);
            if (ingredient.getName().equals(ingredientName)) {
                newAmount = ingredient.getAmount() + delta;
                if (newAmount < 0) {
                    localIngredientList.remove(i);
                }
                else {
                    localIngredientList.remove(i);
                    localIngredientList.add(ingredientFactory.create(ingredientName, newAmount));
                }
                break;
            }
        }
        return newAmount;
    }

    @Override
    public List<String> getIngredients() {
        final List<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName() + " - " + ingredient.getAmount());
        }
        return ingredientList;
    }

    @Override
    public ArrayList<String> getIngredientsArray() {
        final ArrayList<String> ingredientList = new ArrayList<>();
        for (Ingredient ingredient : localIngredientList) {
            ingredientList.add(ingredient.getName());
        }
        return ingredientList;
    }

    @Override
    public void returnTomain() {
        outputBoundary.returnTomain();
    }

    @Override
    public ArrayList<Recipe> getRecipesFromIngredients(ArrayList<String> ingredients,
                                                       UserPreferences userPreferences, boolean userInfo) {
        final UserPreferences nullPreferences = new UserPreferences(0, false, false, new String[0]);
        final ArrayList<Recipe> recipes;
        if (userInfo) {
            recipes = spoonacularDao.getRecipesFromIngredients(ingredients, userPreferences);
        }
        else {
            recipes = spoonacularDao.getRecipesFromIngredients(ingredients, nullPreferences);
        }
        return recipes;
    }
}
