package interface_adapter.season;

import use_case.recommend_season.SeasonInputBoundary;

public class SeasonController {
    private final SeasonInputBoundary seasonInputBoundary;

    public SeasonController(SeasonInputBoundary seasonInteractor) {
        this.seasonInputBoundary = seasonInteractor;
    }

    public String getDate() {
        return seasonInputBoundary.getDate();
    }

    public String getSeason() {
        return seasonInputBoundary.getSeason();
    }

    public String getHoliday() {
        return seasonInputBoundary.getHoliday();
    }

    public void return_to_main() {
        seasonInputBoundary.return_to_main();
    }
}