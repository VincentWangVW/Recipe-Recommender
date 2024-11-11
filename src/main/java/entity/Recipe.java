package entity;

import java.util.ArrayList;

public interface Recipe {
    /**
     * Returns the name of the recipe.
     * @return the name of the recipe.
     */
    String getName();

    /**
     * Returns the url of the recipe.
     * @return the url of the recipe.
     */
    String getUrl();

    /**
     * Returns a list of missing items.
     * @return a list of missing items.
     */
    ArrayList<String> getMissingItems();
}
