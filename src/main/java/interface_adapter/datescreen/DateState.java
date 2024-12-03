package interface_adapter.datescreen;

import java.util.Date;

/**
 * Represents the state of a date, including the season, date, and holiday.
 */
public class DateState {
    private String season;
    private Date date;
    private String holiday;

    /**
     * Retrieves the current season.
     *
     * @return the current season as a string (e.g., "Winter", "Spring")
     */
    public String getSeason() {
        return season;
    }

    /**
     * Sets the current season.
     *
     * @param season the season to set (e.g., "Winter", "Spring")
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Retrieves the current date.
     *
     * @return the current date as a {@link Date} object
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the current date.
     *
     * @param date the date to set as a {@link Date} object
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retrieves the current holiday.
     *
     * @return the name of the current holiday, or {@code null} if none exists
     */
    public String getHoliday() {
        return holiday;
    }

    /**
     * Sets the current holiday.
     *
     * @param holiday the name of the holiday to set
     */
    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
}
