package use_case.recommend_season;

import data_access.InMemoryDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeasonInteractorTest {

    private TestSeasonOutputBoundary seasonPresenter;
    private TestInMemoryDAO inMemoryDAO;
    private SeasonInteractor seasonInteractor;

    @Before
    public void setUp() {
        seasonPresenter = new TestSeasonOutputBoundary();
        inMemoryDAO = new TestInMemoryDAO();
        seasonInteractor = new SeasonInteractor(seasonPresenter, inMemoryDAO);
    }

    @Test
    public void testReturnToMain() {
        seasonInteractor.return_to_main();
        assertTrue(seasonPresenter.returnToMainCalled);
    }

    @Test
    public void testGetDate() {
        String expectedDate = "2023-10-10";
        inMemoryDAO.setDate(expectedDate);
        seasonPresenter.setDate(expectedDate);

        String actualDate = seasonInteractor.getDate();
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testGetSeason() {
        String expectedSeason = "Fall";
        inMemoryDAO.setSeason(expectedSeason);
        seasonPresenter.setSeason(expectedSeason);

        String actualSeason = seasonInteractor.getSeason();
        assertEquals(expectedSeason, actualSeason);
    }

    private static class TestSeasonOutputBoundary implements SeasonOutputBoundary {
        boolean returnToMainCalled = false;
        private String date;
        private String season;

        @Override
        public void return_to_main() {
            returnToMainCalled = true;
        }

        @Override
        public String getDate(String date) {
            return this.date;
        }

        @Override
        public String getSeason(String season) {
            return this.season;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setSeason(String season) {
            this.season = season;
        }
    }

    private static class TestInMemoryDAO extends InMemoryDAO {
        private String date;
        private String season;

        @Override
        public String get_date() {
            return date;
        }

        @Override
        public String get_season() {
            return season;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setSeason(String season) {
            this.season = season;
        }
    }
}