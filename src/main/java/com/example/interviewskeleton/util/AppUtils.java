package com.example.interviewskeleton.util;

import com.example.interviewskeleton.dto.TimeZone;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AppUtils {

    private static final String TIMEZONE_SOURCE_DATA_URL = "https://parseapi.back4app.com/classes/Time_Zones_Dataset?limit=99999&order=CountryCode&keys=CountryCode,TimeZone";
    private static final String APPLICATION_ID = "AeFZ3ZNJ0k8vTBJckXzUvguaR30uhosrDGdvAXtN";
    private static final String MASTER_KEY = "ypQIcKerRaMCPVLYYeTvg0gdqyiDBZB3unKAVNQX";
    private static final String RESULTS_JSON_PROPERTY = "results";
    private static final String TIME_ZONE_JSON_PROPERTY = "TimeZone";
    private static final String COUNTRY_CODE_JSON_PROPERTY = "CountryCode";

    public static List<TimeZone> fetchTimeZoneData() {
        //We are fetching the TimeZone/Locale correlation from an external API.
        List<TimeZone> resultList = new ArrayList<>();
        try {
            URL url = new URL(TIMEZONE_SOURCE_DATA_URL);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestProperty("X-Parse-Application-Id", APPLICATION_ID);
            urlConnection.setRequestProperty("X-Parse-Master-Key", MASTER_KEY);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                JSONArray jsonDataArray = new JSONObject(stringBuilder.toString()).getJSONArray(RESULTS_JSON_PROPERTY);

                for (int i = 0; i < jsonDataArray.length(); i++) {
                    TimeZone timeZone = new TimeZone();
                    timeZone.setTimeZone(jsonDataArray.getJSONObject(i).getString(TIME_ZONE_JSON_PROPERTY));
                    timeZone.setCountryCode(jsonDataArray.getJSONObject(i).getString(COUNTRY_CODE_JSON_PROPERTY));
                    resultList.add(timeZone);
                }
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return resultList;
    }

    public static String replaceText(String str, String value) {
        return str.replaceAll("\\{[^}]+\\}", value);
    }
}
