package interface_adapter.datescreen;

import interface_adapter.ViewManagerModel;
import use_case.recommend_holiday.HolidayOutputBoundary;
import use_case.recommend_season.SeasonInputBoundary;
import use_case.recommend_season.SeasonOutputBoundary;

public class DatePresenter implements SeasonOutputBoundary, HolidayOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public DatePresenter(ViewManagerModel viewManagerModel) {
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