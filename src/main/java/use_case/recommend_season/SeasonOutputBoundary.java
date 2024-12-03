package use_case.recommend_season;

/**
 * The SeasonOutputBoundary interface defines the contract for the output boundary
 * used in the season recommendation use case. It provides methods for presenting
 * information about the current date and season, as well as allowing navigation
 * back to the main screen.
 */
public interface SeasonOutputBoundary {

    /**
     * Instructs the presenter to return to the main screen.
     */
    void return_to_main();

    /**
     * Presents the current date to the user interface.
     *
     * @param date the current date to be displayed
     * @return a string representing the date to be shown in the user interface
     */
    String getDate(String date);

    /**
     * Presents the current season to the user interface.
     *
     * @param season the current season (e.g., "Winter", "Spring") to be displayed
     * @return a string representing the season to be shown in the user interface
     */
    String getSeason(String season);
}
