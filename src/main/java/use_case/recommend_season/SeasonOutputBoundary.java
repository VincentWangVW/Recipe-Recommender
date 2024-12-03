package use_case.recommend_season;

/**
 * Interface defining the output boundary for presenting season-related information.
 */
public interface SeasonOutputBoundary {

    /**
     * Returns to the main menu or state.
     */
    void returnTomain();

    /**
     * Formats and retrieves the date to be presented.
     *
     * @param date The date to be formatted and presented.
     * @return The formatted date as a String.
     */
    String getDate(String date);

    /**
     * Formats and retrieves the season to be presented.
     *
     * @param season The season to be formatted and presented.
     * @return The formatted season as a String.
     */
    String getSeason(String season);
}
