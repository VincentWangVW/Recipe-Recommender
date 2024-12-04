package use_case.recommend_season;

import data_access.InMemoryDateInfoDao;
import data_access.SpoonacularDao;
import entity.Recipe;
import entity.UserPreferences;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test class for SeasonInteractor.
 * This class tests the methods in the SeasonInteractor class.
 * The methods tested are returnToMain, getDate, getSeason, getRecipesFromSeason.
 */
public class SeasonInteractorTest {
    private TestSeasonOutputBoundary seasonPresenter;
    private TestInMemoryDao inMemoryDAO;
    private SeasonInteractor seasonInteractor;

    /**
     * Set up the test environment.
     */
    @Before
    public void setUp() {
        seasonPresenter = new TestSeasonOutputBoundary();

        inMemoryDAO = new TestInMemoryDao();
        SpoonacularDao spoonacularDAO = new SpoonacularDao();
        seasonInteractor = new SeasonInteractor(seasonPresenter, inMemoryDAO, spoonacularDAO);
    }

    /**
     * Test the returnToMain method.
     */
    @Test
    public void testReturnToMain() {
        seasonInteractor.returnTomain();
        assertTrue(seasonPresenter.returnToMainCalled);
    }

    /**
     * Test the getDate method.
     */
    @Test
    public void testGetDate() {
        String expectedDate = "2023-10-10";
        inMemoryDAO.setDate(expectedDate);
        seasonPresenter.setDate(expectedDate);

        String actualDate = seasonInteractor.getDate();
        assertEquals(expectedDate, actualDate);
    }

    /**
     * Test the getSeason method.
     */
    @Test
    public void testGetSeason() {
        String expectedSeason = "Fall";
        inMemoryDAO.setSeason(expectedSeason);
        seasonPresenter.setSeason(expectedSeason);

        String actualSeason = seasonInteractor.getSeason();
        assertEquals(expectedSeason, actualSeason);
    }

    /**
     * Test the getRecipesFromSeason method.
     */
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

    /**
     * Test the getRecipesFromSeason method.
     */
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

    /**
     * TestSeasonOutputBoundary is a test class that implements the SeasonOutputBoundary interface.
     * This class is used to test the SeasonInteractor class.
     */
    private static class TestSeasonOutputBoundary implements SeasonOutputBoundary {
        boolean returnToMainCalled = false;
        private String date;
        private String season;

        /**
         * Return to the main menu.
         */
        @Override
        public void returnTomain() {
            returnToMainCalled = true;
        }

        /**
         * Get the date.
         * @param date the date
         * @return the date
         */
        @Override
        public String getDate(String date) {
            return this.date;
        }

        /**
         * Get the season.
         * @param season the season
         * @return the season
         */
        @Override
        public String getSeason(String season) {
            return this.season;
        }

        /**
         * Set the date.
         * @param date the date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * Set the season.
         * @param season the season
         */
        public void setSeason(String season) {
            this.season = season;
        }
    }

    /**
     * TestInMemoryDao is a test class that extends the InMemoryDateInfoDao class.
     * This class is used to test the SeasonInteractor class.
     */
    private static class TestInMemoryDao extends InMemoryDateInfoDao {
        private String date;
        private String season;

        /**
         * Get the date.
         * @return the date
         */
        @Override
        public String getDate() {
            return date;
        }

        /**
         * Get the season.
         * @return the season
         */
        @Override
        public String getSeason() {
            return season;
        }

        /**
         * Set the date.
         * @param date the date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * Set the season.
         * @param season the season
         */
        public void setSeason(String season) {
            this.season = season;
        }
    }
}
