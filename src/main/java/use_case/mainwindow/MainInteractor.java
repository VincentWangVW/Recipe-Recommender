package use_case.mainwindow;

/**
 * The MainInteractor class implements the MainInputBoundary interface
 * and provides methods for switching views in the main window.
 */
public class MainInteractor implements MainInputBoundary {

    private final MainOutputBoundary mainpresenter;

    /**
     * Constructs a MainInteractor instance with the specified output boundary.
     *
     * @param mainOutputBoundary The output boundary used for switching views.
     */
    public MainInteractor(MainOutputBoundary mainOutputBoundary) {
        mainpresenter = mainOutputBoundary;
    }

    @Override
    public void switchToIngredientsView() {
        mainpresenter.switchToIngredientsView();
    }

    @Override
    public void switchToUserInfoView() {
        mainpresenter.switchToUserInfoView();
    }

    @Override
    public void switchToDateView() {
        mainpresenter.switchToDateView();
    }

    @Override
    public void switchToRecipeView() {
        mainpresenter.switchToRecipeView();
    }
}
