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
public class InMemoryDAO  {

    public String get_date() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 只获取年月日
        return sdf.format(date);
    }


    public static String gets_holiday() {
        Date dates = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date1=sdf.format(dates);
        String country = "CA";
        String year = "2023";//date1.substring(0,4);
        String month = "01";//date1.substring(5,7);
        String day ="01"; //date1.substring(8,10);
        String apiKey = "c26c9672-4db9-49b3-a4e4-03b8d88da0ad"; // 替换为你的API密钥
        String urlString = String.format("https://holidayapi.com/v1/holidays?country=%s&year=%s&key=%s&month=%s&day=%s",
                country, year, apiKey,month,day);

        try {
            // 使用 HttpClient 发送请求
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .build();

            // 获取响应
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 输出响应体（调试用）
            System.out.println("Response: " + response.body());

            // 解析响应的JSON数据
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray holidays = jsonResponse.getJSONArray("holidays");

            // 输出假期信息
            if (holidays.length() > 0) {
                for (int i = 0; i < holidays.length(); i++) {
                    JSONObject holiday = holidays.getJSONObject(i);
                    String date = holiday.getString("date");
                    String name = holiday.getString("name");
                    System.out.println("Holiday: " + name + " on " + date);
                }
            } else {
                System.out.println("No holidays found for this date.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Holiday check complete.";
    }

    }


