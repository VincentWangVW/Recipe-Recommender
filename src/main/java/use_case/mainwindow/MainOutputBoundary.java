package use_case.mainwindow;

/**
 * Interface defining the output boundary for the main window use case.
 */
public interface MainOutputBoundary {

    /**
     * Switches the view to the ingredients view.
     */
    void switchToIngredientsView();

    /**
     * Switches the view to the user info view.
     */
    void switchToUserInfoView();

    /**
     * Switches the view to the date view.
     */
    void switchToDateView();

    /**
     * Switches the view to the recipe view.
     */
    void switchToRecipeView();
}
