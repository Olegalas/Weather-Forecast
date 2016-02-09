package controller;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import model.City;
import model.Country;
import model.Weather;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dexter on 02.02.16.
 */
public class Controller {

    private Map<String, Country> countries = new HashMap<>();

    public Controller(){
        getCountriesFromJSON();
    }

    public Weather getWeather(City city) throws IOException {

        URL obj = new URL("http://api.openweathermap.org/data/2.5/weather?id=" + city.getId() + "&appid=44db6a862fba0b067b1930da0d769e98");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        String strWeather = readJSON(con);

        con.disconnect();
        return genWeather(strWeather);
    }

    private Weather genWeather(String strWeather) {

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(strWeather);
        JsonObject object = element.getAsJsonObject();
        JsonPrimitive temp = object.getAsJsonObject("main").getAsJsonPrimitive("temp");
        JsonPrimitive pressure = object.getAsJsonObject("main").getAsJsonPrimitive("pressure");
        JsonPrimitive humid = object.getAsJsonObject("main").getAsJsonPrimitive("humidity");
        JsonPrimitive maxTemp = object.getAsJsonObject("main").getAsJsonPrimitive("temp_max");
        JsonPrimitive minTemp = object.getAsJsonObject("main").getAsJsonPrimitive("temp_min");
        JsonPrimitive main = object.getAsJsonArray("weather").get(0).getAsJsonObject().getAsJsonPrimitive("main");
        JsonPrimitive description = object.getAsJsonArray("weather").get(0).getAsJsonObject().getAsJsonPrimitive("description");

        return new Weather.Builder().temperature(temp.getAsString()).
                pressure(pressure.getAsString()).humid(humid.getAsString()).
                maxTemperature(maxTemp.getAsString()).minTemperature(minTemp.getAsString()).
                main(main.getAsString()).description(description.getAsString()).build();
    }

    private String readJSON(HttpURLConnection con) throws IOException {
        Scanner sc = new Scanner(con.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n");
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

    @Deprecated
    private String load(){

        StringBuilder bufferStr = new StringBuilder();

        try (BufferedReader bf = new BufferedReader(new FileReader("./resources/city.list.json"))) {

            char[] bArray = new char[1024];

            while(bf.read(bArray, 0, bArray.length) != -1){
                bufferStr.append(bArray);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bufferStr.toString();
    }

    private void getCountriesFromJSON(){

        JsonParser jsonParser = new JsonParser();
        try(JsonReader jsonReader = new JsonReader(new FileReader("./resources/city.list.json"))) {

            jsonReader.setLenient(true);
            for(JsonElement element = jsonParser.parse(jsonReader); element != null; element = jsonParser.parse(jsonReader)) {

                JsonObject object = element.getAsJsonObject();
                JsonPrimitive city = object.getAsJsonPrimitive("name");
                JsonPrimitive country = object.getAsJsonPrimitive("country");
                JsonPrimitive id = object.getAsJsonPrimitive("_id");

                createCountry(city.getAsString(), country.getAsString(), id.getAsString());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createCountry(String city, String country, String id) {
        Country tmpCountry = countries.get(country);
        if(tmpCountry == null){
            tmpCountry = new Country(country, city, id);
            countries.put(country, tmpCountry);
        } else{
            tmpCountry.addCity(city, id);
        }
    }

    public Map<String, Country> getCountries(){
        return countries;
    }
}
