package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, ParseException {
        String fromCurrency = "INR";
        String toCurrency = "USD";
        int amount = 100;
//        String apiKey = "veOtmUFgkaTyhSlCjbdK7nv9z1taN3X0";
//        String apiUrl = "https://api.apilayer.com/exchangerates_data/convert?to=" + toCurrency + "&from=" + fromCurrency + "&amount=" + amount;
        String apiUrl = "https://cat-fact.herokuapp.com/facts";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestProperty("apikey", apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        String responseMessage = connection.getResponseMessage();
        System.out.println(responseCode);

        if (responseCode != 200) {
            throw new RuntimeException("Response code: " + responseCode + "\n Response message: " + responseMessage);
        } else {
            StringBuilder information = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                information.append(scanner.nextLine());
            }
            scanner.close();

            JSONParser parse = new JSONParser();
            JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(information));
            System.out.println(((JSONObject)(dataObject.get(0))).get("text"));
        }
        System.out.println("Hello World!");
    }
}
