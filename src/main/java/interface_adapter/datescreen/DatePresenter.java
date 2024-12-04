package interface_adapter.datescreen;

import interface_adapter.ViewManagerModel;
import use_case.recommend_holiday.HolidayOutputBoundary;
import use_case.recommend_season.SeasonOutputBoundary;

/**
 * The presenter for handling date-related output.
 * Implements both season and holiday output boundaries.
 */
public class DatePresenter implements SeasonOutputBoundary, HolidayOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a {@code DatePresenter} with the specified {@code ViewManagerModel}.
     *
     * @param viewManagerModel the model managing view states and updates
     */
    public DatePresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Returns the user to the main screen by updating the view state.
     */
    @Override
    public void returnTomain() {
        viewManagerModel.setState("MAIN_SCREEN");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Provides the current date to the view.
     *
     * @param date the current date in "yyyy-MM-dd" format
     * @return the current date as a string
     */
    @Override
    public String getDate(String date) {
        return date;
    }

    /**
     * Provides the current season to the view.
     *
     * @param season the current season (e.g., "Winter", "Spring")
     * @return the current season as a string
     */
    @Override
    public String getSeason(String season) {
        return season;
    }

    /**
     * Provides the current holiday to the view.
     *
     * @param holiday the name of the current holiday, or "No Holiday Today!" if none exists
     * @return the current holiday as a string
     */
    @Override
    public String getHoliday(String holiday) {
        return holiday;
    }
}
