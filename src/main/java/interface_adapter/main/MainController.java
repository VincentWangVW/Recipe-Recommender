package interface_adapter.main;

import use_case.mainwindow.MainInputBoundary;

public class MainController {
    private final MainInputBoundary mainInteractor;

    public MainController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }
    public void switch_to_SeasonView() {
        mainInteractor.switch_to_SeasonView();
    }

    public void switch_to_RecipeView() {
        mainInteractor.switch_to_RecipeView();
    }
}
