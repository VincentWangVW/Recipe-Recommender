package data_access;

import interface_adapter.season.SeasonDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
public class InMemoryDAO implements SeasonDataAccessInterface  {
    @Override
    public String get_date() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 只获取年月日
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
        String apiKey = "843b8d83c60d48f1b91cf5e5200bc72c";
        String urlString = String.format("https://holidays.abstractapi.com/v1/?api_key=%s&country=%s&year=%s&month=%s&day=%s",
                apiKey,country, year,month,day);

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.body().equals("[]")) {
                return "";
            } else {
                JSONArray jsonArray = new JSONArray(response.body());
                return jsonArray.getJSONObject(0).getString("name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    }


