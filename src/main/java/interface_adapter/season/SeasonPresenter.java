package interface_adapter.season;

import interface_adapter.ViewManagerModel;
import use_case.recommend_season.SeasonOutputBoundary;
import data_access.InMemoryDAO;

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

    @Override
    public String getDate(String date) {
        return date;
    }

    @Override
    public String getSeason(String season) {
        return season;
    }

    @Override
    public String getHoliday(String holiday) {
        return holiday;
    }
}