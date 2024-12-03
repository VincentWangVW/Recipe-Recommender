package interface_adapter.main;

import use_case.mainwindow.MainInputBoundary;

/**
 * Controller for handling main screen actions and navigation.
 * Responsible for delegating user interactions to the MainInputBoundary interactor.
 */
public class MainController {
    private final MainInputBoundary mainInteractor;

    /**
     * Constructs a MainController with the given MainInputBoundary.
     *
     * @param mainInteractor The interactor for handling main screen actions.
     */
    public MainController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    /**
     * Switches the view to the Date screen.
     */
    public void switchToDateView() {
        mainInteractor.switchToDateView();
    }

    /**
     * Switches the view to the Recipe screen.
     */
    public void switchToRecipeView() {
        mainInteractor.switchToRecipeView();
    }

    /**
     * Switches the view to the Ingredients screen.
     */
    public void switchToIngredientsView() {
        mainInteractor.switchToIngredientsView();
    }

    /**
     * Switches the view to the User Information screen.
     */
    public void switchToUserInfoView() {
        mainInteractor.switchToUserInfoView();
    }
}
