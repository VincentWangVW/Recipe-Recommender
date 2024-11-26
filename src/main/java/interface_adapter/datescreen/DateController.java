package interface_adapter.datescreen;

import use_case.recommend_holiday.HolidayInputBoundary;
import use_case.recommend_holiday.HolidayOutputBoundary;
import use_case.recommend_season.SeasonInputBoundary;

public class DateController implements SeasonInputBoundary, HolidayInputBoundary {
    private final SeasonInputBoundary seasonInputBoundary;
    private final HolidayInputBoundary holidayInputBoundary;

    public DateController(SeasonInputBoundary seasonInteractor, HolidayInputBoundary holidayInteractor) {
        this.seasonInputBoundary = seasonInteractor;
        this.holidayInputBoundary = holidayInteractor;
    }

    public String getDate() {
        return seasonInputBoundary.getDate();
    }

    public String getSeason() {
        return seasonInputBoundary.getSeason();
    }

    public String getHoliday() {
        return holidayInputBoundary.getHoliday();
    }

    public void return_to_main() {
        seasonInputBoundary.return_to_main();
    }
}
