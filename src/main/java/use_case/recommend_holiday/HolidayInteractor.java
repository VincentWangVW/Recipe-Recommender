package use_case.recommend_holiday;

import data_access.InMemoryDAO;

public class HolidayInteractor implements HolidayInputBoundary {
    public final HolidayOutputBoundary holidaypresenter;
    private final InMemoryDAO inMemoryDAO;

    public HolidayInteractor(HolidayOutputBoundary holidaypresenter, InMemoryDAO inMemoryDAO) {
        this.holidaypresenter = holidaypresenter;
        this.inMemoryDAO = inMemoryDAO;
    }

    @Override
    public void return_to_main() {
        holidaypresenter.return_to_main();
    }

    @Override
    public String getHoliday() {
        return holidaypresenter.getHoliday(inMemoryDAO.get_holiday());

    }
}
