package use_case.mainwindow;

public class MainInteractor implements MainInputBoundary{
    public final MainOutputBoundary mainpresenter;

    public MainInteractor(MainOutputBoundary mainOutputBoundary){
        mainpresenter=mainOutputBoundary;
    }
    @Override
    public void switch_to_IngredientsView() { mainpresenter.switch_to_IngredientsView(); }

    @Override
    public void switch_to_UserInfoView() {
        mainpresenter.switch_to_UserInfoView();
    }

    @Override
    public void switch_to_DateView() {
        mainpresenter.switch_to_DateView();
    }

    @Override
    public void switch_to_RecipeView() {
        mainpresenter.switch_to_RecipeView();
    }
}
