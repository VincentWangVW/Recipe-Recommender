package interface_adapter.datescreen;

/**
 * Interface for accessing date-related data, including current date, holiday, and season.
 */
public interface DateDataAccessInterface {

    /**
     * Retrieves the current date.
     *
     * @return A string representing the current date.
     */
    String getDate();

    /**
     * Retrieves the current holiday.
     *
     * @return A string representing the current holiday.
     */
    String getHoliday();

    /**
     * Retrieves the current season.
     *
     * @return A string representing the current season.
     */
    String getSeason();
}
