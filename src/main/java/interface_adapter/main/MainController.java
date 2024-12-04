package interface_adapter.main;

import use_case.mainwindow.MainInputBoundary;

/**
 * The controller for managing the main screen view transitions.
 * This class acts as the intermediary between the user interface and the main use case logic,
 * allowing for switching between different views such as DateView, RecipeView, IngredientsView, and UserInfoView.
 */
public class MainController {
    private final MainInputBoundary mainInteractor;

    /**
     * Constructs a MainController with the provided MainInputBoundary.
     * @param mainInteractor The MainInputBoundary used for view transitions.
     */
    public MainController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    /**
     * Switches the view to the DateView.
     */
    public void switchToDateView() {
        mainInteractor.switchToDateView();
    }

    /**
     * Switches the view to the RecipeView.
     */
    public void switchToRecipeView() {
        mainInteractor.switchToRecipeView();
    }

    /**
     * Switches the view to the IngredientsView.
     */
    public void switchToIngredientsView() {
        mainInteractor.switchToIngredientsView();
    }

    /**
     * Switches the view to the UserInfoView.
     */
    public void switchToUserInfoView() {
        mainInteractor.switchToUserInfoView();
    }
}
