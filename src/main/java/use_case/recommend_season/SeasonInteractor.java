package use_case.recommend_season;

import data_access.InMemoryDAO;

public class SeasonInteractor implements SeasonInputBoundary {
    public final SeasonOutputBoundary seasonpresenter;
    private final InMemoryDAO inMemoryDAO;
    public SeasonInteractor(SeasonOutputBoundary seasonpresenter, InMemoryDAO inMemoryDAO) {
        this.seasonpresenter = seasonpresenter;
        this.inMemoryDAO = inMemoryDAO;
    }

    @Override
    public void return_to_main() {
        seasonpresenter.return_to_main();
    }

    @Override
    public String getDate() {

        return seasonpresenter.getDate(inMemoryDAO.get_date());
    }

    @Override
    public String getSeason() {
        return seasonpresenter.getSeason(inMemoryDAO.get_season());
    }

    @Override
    public String getHoliday() {
        return seasonpresenter.getHoliday(inMemoryDAO.get_holiday());
    }
}