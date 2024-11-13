package interface_adapter.main;

import interface_adapter.ViewManagerModel;
import interface_adapter.season.SeasonViewModel;
import use_case.mainwindow.MainOutputBoundary;

public class MainPresenter implements MainOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SeasonViewModel seasonViewModel;

    public MainPresenter(ViewManagerModel viewManagerModel, SeasonViewModel seasonViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.seasonViewModel = seasonViewModel;
    }

    @Override
    public void switch_to_IngredientsView() {

    }

    @Override
    public void switch_to_GenerateRecipeView() {

    }

    @Override
    public void switch_to_UserInfoView() {

    }

    @Override
    public void switch_to_SeasonView() {
        viewManagerModel.setState(seasonViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
