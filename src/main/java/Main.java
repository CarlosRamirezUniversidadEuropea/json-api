import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import entity.City;
import entity.MyCity;
import com.fasterxml.jackson.databind.ObjectMapper;
import error.ApiError;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {


        System.out.println("Introduce un nombre de una ciudad");
        Scanner scanner = new Scanner(System.in);
        String ciudad = scanner.nextLine();

        String url = Services.apiUrl + ciudad + "&APPID=" + Services.APPID + "&units=metric" + "&lang=es";
        System.out.println(url);
        final HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        JSONObject json = response.getBody().getObject();

        try {
            System.out.println("Grados en " + ciudad + ": " + json.getJSONObject("main").getDouble("temp") + " ºC");
            System.out.println("Descripción: " + json.getJSONArray("weather").getJSONObject(0).getString("description"));
            System.out.println("Latitud: " + json.getJSONObject("coord").getDouble("lat"));
            System.out.println("Longitud: " + json.getJSONObject("coord").getDouble("lon"));
            System.out.println("Humedad: " + json.getJSONObject("main").getDouble("humidity") + " %");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String responseString = response.getBody().toString();
        System.out.println(responseString);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        City city = gson.fromJson(responseString, new TypeToken<City>() {}.getType());
        ApiError error = gson.fromJson(responseString, new TypeToken<ApiError>() {}.getType());

        if(city!=null && city.getCod()==200){
            System.out.println("Grados en " + ciudad + ": " + city.getTermicInformation().getTemp() + " ºC");
            System.out.println("Descripción: " + city.getWeather().get(0).getDescription());
            System.out.println("Latitud: " + city.getCoordinates().getLat());
            System.out.println("Longitud: " + city.getCoordinates().getLon());
            System.out.println("Humedad: " + city.getTermicInformation().getHumidity() + " %");
        } else if (error!=null){
            System.out.println("Error al intentar recoger los datos del tiempo: "+ error.getCode());
            if(error.hasErrorMsg()){
                System.out.println("Mensaje de eror: "+ error.getMessage());
            }
        }

        String url2 = Services.severalCitiesUrl + "lat=40.4&lon=-3.7&cnt=10" + "&APPID=" + Services.APPID + "&units=metric" + "&lang=es";
        final HttpResponse<JsonNode> response2 = Unirest.get(url2).asJson();
        JSONObject json2 = response2.getBody().getObject();
        System.out.println(json2.toString());
        List<City> citiesList = gson.fromJson(json2.getJSONArray("list").toString(), new TypeToken<List<City>>() {}.getType());;
        List<MyCity> myCities = new ArrayList<MyCity>();
        for (City city1: citiesList){
            myCities.add(new MyCity(
                    city1.getTermicInformation().getTemp(),
                    city1.getName(),
                    city1.getCoordinates().getLat(),
                    city1.getCoordinates().getLon()));
        }
        OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File("results.json"), myCities);

    }
}
