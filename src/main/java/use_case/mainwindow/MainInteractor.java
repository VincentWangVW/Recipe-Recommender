package use_case.mainwindow;

/**
 * The MainInteractor class is the implementation of the MainInputBoundary interface.
 * It acts as the interactor for handling the logic of switching between different views in the main window.
 * It communicates with the MainOutputBoundary (the presenter) to notify the views of state changes.
 */
public class MainInteractor implements MainInputBoundary {
    public final MainOutputBoundary mainpresenter;

    /**
     * Constructs a MainInteractor with a given MainOutputBoundary (presenter).
     *
     * @param mainOutputBoundary the MainOutputBoundary responsible for updating the views.
     */
    public MainInteractor(MainOutputBoundary mainOutputBoundary) {
        mainpresenter = mainOutputBoundary;
    }

    /**
     * Switches the current view to the Ingredients view by invoking the appropriate method on the presenter.
     */
    @Override
    public void switch_to_IngredientsView() {
        mainpresenter.switch_to_IngredientsView();
    }

    /**
     * Switches the current view to the User Info view by invoking the appropriate method on the presenter.
     */
    @Override
    public void switch_to_UserInfoView() {
        mainpresenter.switch_to_UserInfoView();
    }

    /**
     * Switches the current view to the Date view by invoking the appropriate method on the presenter.
     */
    @Override
    public void switch_to_DateView() {
        mainpresenter.switch_to_DateView();
    }

    /**
     * Switches the current view to the Recipe view by invoking the appropriate method on the presenter.
     */
    @Override
    public void switch_to_RecipeView() {
        mainpresenter.switch_to_RecipeView();
    }
}
