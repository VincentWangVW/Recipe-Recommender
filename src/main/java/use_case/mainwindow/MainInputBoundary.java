package use_case.mainwindow;

/**
 * The MainInputBoundary interface defines the contract for the input boundary in the main window use case.
 * It provides methods for switching between different views within the main window, such as the ingredients view,
 * user info view, date view, and recipe view.
 */
public interface MainInputBoundary {

    /**
     * Switches the current view to the Ingredients view.
     * This method is intended to be called when the user navigates to the Ingredients section of the application.
     */
    void switch_to_IngredientsView();

    /**
     * Switches the current view to the User Info view.
     * This method is intended to be called when the user navigates to the User Info section of the application.
     */
    void switch_to_UserInfoView();

    /**
     * Switches the current view to the Date view.
     * This method is intended to be called when the user navigates to the Date section of the application.
     */
    void switch_to_DateView();

    /**
     * Switches the current view to the Recipe view.
     * This method is intended to be called when the user navigates to the Recipe section of the application.
     */
    void switch_to_RecipeView();

}
