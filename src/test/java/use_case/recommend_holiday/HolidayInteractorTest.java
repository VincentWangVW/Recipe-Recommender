package use_case.recommend_holiday;

import data_access.InMemoryDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HolidayInteractorTest {

    private TestHolidayOutputBoundary holidayPresenter;
    private TestInMemoryDAO inMemoryDAO;
    private HolidayInteractor holidayInteractor;

    @Before
    public void setUp() {
        holidayPresenter = new TestHolidayOutputBoundary();
        inMemoryDAO = new TestInMemoryDAO();
        holidayInteractor = new HolidayInteractor(holidayPresenter, inMemoryDAO);
    }

    @Test
    public void testReturnToMain() {
        holidayInteractor.return_to_main();
        assertTrue(holidayPresenter.returnToMainCalled);
    }

    @Test
    public void testGetHoliday() {
        String expectedHoliday = "Christmas";
        inMemoryDAO.setHoliday(expectedHoliday);
        holidayPresenter.setHoliday(expectedHoliday);

        String actualHoliday = holidayInteractor.getHoliday();
        assertEquals(expectedHoliday, actualHoliday);
    }

    private static class TestHolidayOutputBoundary implements HolidayOutputBoundary {
        boolean returnToMainCalled = false;
        private String holiday;

        @Override
        public void return_to_main() {
            returnToMainCalled = true;
        }

        @Override
        public String getHoliday(String holiday) {
            return this.holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }
    }

    private static class TestInMemoryDAO extends InMemoryDAO {
        private String holiday;

        @Override
        public String get_holiday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }
    }
}