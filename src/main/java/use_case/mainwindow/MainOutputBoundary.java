package use_case.mainwindow;

/**
 * The MainOutputBoundary interface defines the methods for switching between different views in the main window.
 * It acts as the output boundary for the MainInteractor, allowing it to communicate with the presenter
 * to update the view based on user interactions or application logic.
 */
public interface MainOutputBoundary {

    /**
     * Switches the current view to the Ingredients view.
     */
    void switch_to_IngredientsView();

    /**
     * Switches the current view to the User Info view.
     */
    void switch_to_UserInfoView();

    /**
     * Switches the current view to the Date view.
     */
    void switch_to_DateView();

    /**
     * Switches the current view to the Recipe view.
     */
    void switch_to_RecipeView();
}
