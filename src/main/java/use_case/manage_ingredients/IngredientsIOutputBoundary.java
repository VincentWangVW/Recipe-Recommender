package use_case.manage_ingredients;

import java.util.HashMap;
import java.util.List;

public interface IngredientsIOutputBoundary {
    void presentIngredients(HashMap<Integer, List<String>> ingredientsInfo);

    void return_to_main();
}
