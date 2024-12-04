package use_case.recommend_season;

import data_access.InMemoryDateInfoDao;
import data_access.SpoonacularDao;
import entity.Recipe;
import entity.UserPreferences;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SeasonInteractorTest {

    private TestSeasonOutputBoundary seasonPresenter;
    private TestInMemoryDao inMemoryDAO;
    private SeasonInteractor seasonInteractor;
    private SpoonacularDao spoonacularDAO;

    @Before
    public void setUp() {
        seasonPresenter = new TestSeasonOutputBoundary();

        inMemoryDAO = new TestInMemoryDao();
        spoonacularDAO = new SpoonacularDao();
        seasonInteractor = new SeasonInteractor(seasonPresenter, inMemoryDAO, spoonacularDAO);
    }

    @Test
    public void testReturnToMain() {
        seasonInteractor.returnTomain();
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
    @Test
    public void testgetRecipesFromSeasontrue(){
        UserPreferences userPreferences = new UserPreferences(10, true, false,
                new String[0]);
        String expectedSeason = "Fall";
        inMemoryDAO.setSeason(expectedSeason);
        seasonPresenter.setSeason(expectedSeason);
        ArrayList<Recipe> actual = seasonInteractor.getRecipesFromSeason(userPreferences,true);
        ArrayList<Recipe> empty = new ArrayList<>();
        assertNotEquals(empty,actual);
    }
    @Test
    public void testgetRecipesFromSeasonfalse(){
        UserPreferences userPreferences = new UserPreferences(0, false, false,
                new String[0]);
        String expectedSeason = "Fall";
        inMemoryDAO.setSeason(expectedSeason);
        seasonPresenter.setSeason(expectedSeason);
        ArrayList<Recipe> actual=seasonInteractor.getRecipesFromSeason(userPreferences,false);
        ArrayList<Recipe> empty=new ArrayList<>();
        assertNotEquals(empty,actual);
    }

    private static class TestSeasonOutputBoundary implements SeasonOutputBoundary {
        boolean returnToMainCalled = false;
        private String date;
        private String season;

        @Override
        public void returnTomain() {
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

    private static class TestInMemoryDao extends InMemoryDateInfoDao {
        private String date;
        private String season;

        @Override
        public String getDate() {
            return date;
        }

        @Override
        public String getSeason() {
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