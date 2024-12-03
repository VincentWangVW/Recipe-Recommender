package interface_adapter.main;

import use_case.mainwindow.MainInputBoundary;

public class MainController {
    private final MainInputBoundary mainInteractor;

    public MainController(MainInputBoundary mainInteractor) {
        this.mainInteractor = mainInteractor;
    }
    public void switch_to_DateView() {
        mainInteractor.switchToDateView();
    }

    public void switch_to_RecipeView() {
        mainInteractor.switchToRecipeView();
    }

    public void switch_to_IngredientsView(){mainInteractor.switchToIngredientsView(); }

    public void switch_to_UserInfoView(){mainInteractor.switchToUserInfoView(); }
}
