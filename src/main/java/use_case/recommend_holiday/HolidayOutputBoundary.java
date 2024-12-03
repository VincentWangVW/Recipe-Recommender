package use_case.recommend_holiday;

/**
 * The HolidayOutputBoundary interface defines the methods that a presenter or output boundary
 * should implement to handle holiday-specific data and actions. It allows for returning to the main
 * screen and retrieving holiday information.
 */
public interface HolidayOutputBoundary {

    /**
     * Returns to the main screen or menu. This method is called when the user navigates away from
     * the current holiday-related view.
     */
    void return_to_main();

    /**
     * Retrieves the holiday information.
     *
     * @param holiday a String representing the current or selected holiday
     * @return the holiday as a String
     */
    String getHoliday(String holiday);
}
