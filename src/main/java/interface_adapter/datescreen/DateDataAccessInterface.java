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
    String get_date();

    /**
     * Retrieves the holiday for the current date.
     *
     * @return the name of the holiday, or "No Holiday Today!" if none exists
     */
    String get_holiday();

    /**
     * Retrieves the current season based on the date.
     *
     * @return the current season as a string (e.g., "Winter", "Spring")
     */
    String get_season();
}
