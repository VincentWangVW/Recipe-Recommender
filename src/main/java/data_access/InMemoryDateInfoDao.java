package data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

import interface_adapter.datescreen.DateDataAccessInterface;

/**
 * This class is an implementation of the DateDataAccessInterface that provides
 * the current date, holiday, and season.
 */
public class InMemoryDateInfoDao implements DateDataAccessInterface {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String HOLIDAY_API_URL_TEMPLATE = "https://holidays.abstractapi.com/v1/?api_key="
            + "%s&country=%s&year=%s&month=%s&day=%s";
    private static final String COUNTRY_CODE = "CA";
    private static final String NO_HOLIDAY_MESSAGE = "No Holiday Today!";
    private static final String API_KEY = "843b8d83c60d48f1b91cf5e5200bc72c";

    private static final int WINTER_START_MONTH = 12;
    private static final int WINTER_START_DAY = 21;
    private static final int WINTER_END_MONTH = 1;
    private static final int WINTER_END_DAY = 19;

    private static final int SPRING_START_MONTH = 3;
    private static final int SPRING_START_DAY = 21;
    private static final int SPRING_END_MONTH = 4;
    private static final int SPRING_END_DAY = 19;

    private static final int SUMMER_START_MONTH = 6;
    private static final int SUMMER_START_DAY = 21;
    private static final int SUMMER_END_MONTH = 7;
    private static final int SUMMER_END_DAY = 22;

    private static final int FALL_START_MONTH = 9;
    private static final int FALL_START_DAY = 23;
    private static final int FALL_END_MONTH = 10;
    private static final int FALL_END_DAY = 21;

    /**
     * Returns the current date in the format "yyyy-MM-dd".
     * @return the current date.
     */
    @Override
    public String getDate() {
        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * Returns the holiday for the current date.
     * @return the holiday for the current date.
     */
    @Override
    public String getHoliday() {
        final Date dates = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        final String date1 = sdf.format(dates);
        final String year = date1.substring(0, 4);
        final String month = date1.substring(5, 7);
        final String day = date1.substring(8, 10);

        final String urlString = String.format(HOLIDAY_API_URL_TEMPLATE, API_KEY, COUNTRY_CODE, year, month, day);

        String holiday = NO_HOLIDAY_MESSAGE;

        try {
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .build();
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body().equals("[]")) {
                holiday = NO_HOLIDAY_MESSAGE;
            }
            else {
                final JSONArray jsonArray = new JSONArray(response.body());
                if (jsonArray.getJSONObject(0).getString("name") != null) {
                    holiday = jsonArray.getJSONObject(0).getString("name");
                }
            }
        }
        catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
        return holiday;
    }

    /**
     * Returns the season for the current date.
     * @return the season for the current date.
     */
    @Override
    public String getSeason() {
        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        final String date1 = sdf.format(date);
        final String[] date2 = date1.split("-");
        final int month = Integer.parseInt(date2[0]);
        final int day = Integer.parseInt(date2[1]);

        final String season;

        if (month == WINTER_START_MONTH && day >= WINTER_START_DAY
                || month == WINTER_END_MONTH && day <= WINTER_END_DAY) {
            season = "Winter";
        }
        else if (month == SPRING_START_MONTH && day >= SPRING_START_DAY
                || month == SPRING_END_MONTH && day <= SPRING_END_DAY) {
            season = "Spring";
        }
        else if (month == SUMMER_START_MONTH && day >= SUMMER_START_DAY
                || month == SUMMER_END_MONTH && day <= SUMMER_END_DAY) {
            season = "Summer";
        }
        else if (month == FALL_START_MONTH && day >= FALL_START_DAY
                || month == FALL_END_MONTH && day <= FALL_END_DAY) {
            season = "Fall";
        }
        else {
            season = "Winter";
        }
        return season;
    }
}
