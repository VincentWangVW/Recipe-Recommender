package interface_adapter.datescreen;

/**
 * The data access interface for retrieving date-related information.
 * Provides methods for fetching the current date, holiday, and season.
 */
public interface DateDataAccessInterface {

    /**
     * Retrieves the current date.
     *
     * @return the current date in "yyyy-MM-dd" format
     */
    String getDate();

    /**
     * Retrieves the holiday for the current date.
     *
     * @return the name of the holiday, or "No Holiday Today!" if none exists
     */
    String getHoliday();

    /**
     * Retrieves the current season based on the date.
     *
     * @return the current season as a string (e.g., "Winter", "Spring")
     */
    String getSeason();
}
