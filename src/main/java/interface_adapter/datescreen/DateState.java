package interface_adapter.datescreen;

import java.util.Date;

/**
 * Represents the state of a date-related object, including season, date, and holiday information.
 */
public class DateState {
    private String season;
    private Date date;
    private String holiday;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
}
