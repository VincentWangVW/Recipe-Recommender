package interface_adapter.season;

import interface_adapter.ViewManagerModel;
import use_case.recommend_season.SeasonOutputBoundary;

public class SeasonPresenter implements SeasonOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public SeasonPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void return_to_main() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }
}
