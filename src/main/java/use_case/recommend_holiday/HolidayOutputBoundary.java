package use_case.recommend_holiday;

/**
 * Interface defining the output boundary for presenting holiday-related information.
 */
public interface HolidayOutputBoundary {

    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Presents the specified holiday to the user.
     *
     * @param holiday The name of the holiday to be presented.
     * @return The formatted holiday name as a String.
     */
    String getHoliday(String holiday);
}
