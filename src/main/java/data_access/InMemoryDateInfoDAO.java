package data_access;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

import interface_adapter.datescreen.DateDataAccessInterface;

/**
 * The {@code InMemoryDateInfoDAO} class implements the {@link DateDataAccessInterface}
 * to provide date-related functionalities such as retrieving the current date,
 * holiday information, and the current season.
 */
public class InMemoryDateInfoDAO implements DateDataAccessInterface {
    /**
     * Retrieves the current date in "yyyy-MM-dd" format.
     *
     * @return the current date as a {@code String}.
     */
    @Override
    public String get_date() {
        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * Retrieves the holiday for the current date.
     *
     * <p>Uses the Abstract API to determine if there is a holiday on the current date.
     * If no holiday is found or an error occurs, "No Holiday Today!" is returned.</p>
     *
     * @return the name of the holiday or "No Holiday Today!" if none exists.
     */
    @Override
    public String get_holiday() {
        final Date dates = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String date1 = sdf.format(dates);
        final String country = "CA";
        final String year = date1.substring(0, 4);
        final String month = date1.substring(5, 7);
        final String day = date1.substring(8, 10);
        // 118d3a2c9c1e4d8f813ec6f7b9f84637
        final String apiKey = "843b8d83c60d48f1b91cf5e5200bc72c";
        final String urlString = String.format(
                "https://holidays.abstractapi.com/v1/?api_key=%s&country=%s&year=%s&month=%s&day=%s",
                apiKey, country, year, month, day);

        try {

            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body().equals("[]")) {
                return "No Holiday Today!";
            }
            else {
                final JSONArray jsonArray = new JSONArray(response.body());
                if (jsonArray.getJSONObject(0).getString("name") != null) {
                    return jsonArray.getJSONObject(0).getString("name");
                }
                else {
                    return "No Holiday Today!";
                }
            }
        }
        catch (Exception e) {
            System.out.printf("Check API");
            e.printStackTrace();
        }
        return "No Holiday Today!";
    }

    /**
     * Determines the current season.
     *
     * @return the current season.
     */
    @Override
    public String get_season() {
        final Date date = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        final String date1 = sdf.format(date);
        final String[] date2 = date1.split("-");
        final int month = Integer.parseInt(date2[0]);
        final int day = Integer.parseInt(date2[1]);
        if (month == 12 && day >= 21 || month == 1 && day <= 19) {
            return "Winter";
        }
        else if (month == 3 && day >= 21 || month == 4 && day <= 19) {
            return "Spring";
        }
        else if (month == 6 && day >= 21 || month == 7 && day <= 22) {
            return "Summer";
        }
        else if (month == 9 && day >= 23 || month == 10 && day <= 21) {
            return "Fall";
        }
        else {
            return "Winter";
        }
    }
}
