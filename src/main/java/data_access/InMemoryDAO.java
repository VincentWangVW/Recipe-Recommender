package data_access;

import interface_adapter.datescreen.DateDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
public class InMemoryDAO implements DateDataAccessInterface  {
    @Override
    public String get_date() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    @Override
    public String get_holiday() {
        Date dates = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1=sdf.format(dates);
        String country = "CA";
        String year = date1.substring(0,4);
        String month = date1.substring(5,7);
        String day =date1.substring(8,10);
        String apiKey = "843b8d83c60d48f1b91cf5e5200bc72c"; // 118d3a2c9c1e4d8f813ec6f7b9f84637
        String urlString = String.format("https://holidays.abstractapi.com/v1/?api_key=%s&country=%s&year=%s&month=%s&day=%s",
                apiKey,country, year,month,day);

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body().equals("[]")) {
                return "No Holiday Today!";
            }
            else {
                JSONArray jsonArray = new JSONArray(response.body());
                if (jsonArray.getJSONObject(0).getString("name") != null) {
                    return jsonArray.getJSONObject(0).getString("name");
                }
                else {
                    return "No Holiday Today!";
                }
            }
        } catch (Exception e) {
            System.out.printf("Check API");
            e.printStackTrace();
        }
        return "No Holiday Today!";
    }

    @Override
    public String get_season() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String date1 = sdf.format(date);
        String[] date2 = date1.split("-");
        int month = Integer.parseInt(date2[0]);
        int day = Integer.parseInt(date2[1]);
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

    public static void main(String[] args) {
        InMemoryDAO inMemoryDAO = new InMemoryDAO();
        System.out.println(inMemoryDAO.get_date());
        System.out.println(inMemoryDAO.get_holiday());
        System.out.println(inMemoryDAO.get_season());
    }
}