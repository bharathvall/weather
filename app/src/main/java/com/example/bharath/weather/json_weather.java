package com.example.bharath.weather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class json_weather extends AsyncTask<String, Void, String> {
    String result = " ";

    URL url;


    private Icommunication icommunication;

    public void setIcommunication(Icommunication icommunication) {
        this.icommunication = icommunication;
    }

    HttpURLConnection connection = null;

    @Override
    protected String doInBackground(String... urls) {

        try {
            this.url = new URL(urls[0]);

            connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            InputStream inputStream = connection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            while (data != -1) {
                char d = (char) data;

                result = result + d;

                data = inputStreamReader.read();


            }
            return result;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        double temp = 0;
        long presure = 0;
        long humidity = 0;
        double temp_min = 0;
        double temp_max = 0;
        long rise=0;
        long moonrise=0;

        try {
            JSONObject start = new JSONObject(this.result);
            String condition = " ";
            String mainw = "";
            int id = 0;
            JSONArray weather = start.getJSONArray("weather");
            JSONObject main = start.getJSONObject("main");
            JSONObject sun=start.getJSONObject("sys");
            JSONObject windJ=start.getJSONObject("wind");
            double speed=windJ.getDouble("speed");
            double direction=0;
            if(windJ.has("deg")){
                          direction=windJ.getDouble("deg");
            }

            Log.d("direcyions", "onPostExecute: "+start.has("wind")+windJ.getDouble("speed")+windJ.has("deg"));
            rise=sun.getInt("sunrise");
            moonrise=sun.getInt("sunset");
            Log.d("datasun", "onPostExecute: "+rise+" "+moonrise);
            temp = main.getDouble("temp");
            temp_min = main.getDouble("temp_min");
            temp_max = main.getDouble("temp_max");
            humidity = main.getInt("humidity");
            presure = main.getInt("pressure");

            for (int i = 0; i < weather.length(); i++) {
                JSONObject des = weather.getJSONObject(i);
                condition = des.getString("description");
                mainw = des.getString("main");
                id = des.getInt("id");
                Log.d("conditions", "onPostExecute: " + mainw + " " + condition + id);
            }

            Log.d("boolean ", "onPostExecute: " + main.has("temp"));

            double main1 = Double.parseDouble(main.getString("temp"));

            Log.d("tempmain", "onPostExecute: " + temp_max + " " + temp + " " + temp_min + " " + humidity + " " + presure + " " + condition);
            try {
                icommunication.weather(temp, temp_min, temp_max, presure, humidity, condition, mainw, id,rise,moonrise,speed,direction);
            } catch (Exception e) {

            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }
    public String convertDegreeToCardinalDirection(int directionInDegrees){
        String cardinalDirection = null;
        if( (directionInDegrees >= 348.75) && (directionInDegrees <= 360) ||
                (directionInDegrees >= 0) && (directionInDegrees <= 11.25)    ){
            cardinalDirection = "N";
        } else if( (directionInDegrees >= 11.25 ) && (directionInDegrees <= 33.75)){
            cardinalDirection = "NNE";
        } else if( (directionInDegrees >= 33.75 ) &&(directionInDegrees <= 56.25)){
            cardinalDirection = "NE";
        } else if( (directionInDegrees >= 56.25 ) && (directionInDegrees <= 78.75)){
            cardinalDirection = "ENE";
        } else if( (directionInDegrees >= 78.75 ) && (directionInDegrees <= 101.25) ){
            cardinalDirection = "E";
        } else if( (directionInDegrees >= 101.25) && (directionInDegrees <= 123.75) ){
            cardinalDirection = "ESE";
        } else if( (directionInDegrees >= 123.75) && (directionInDegrees <= 146.25) ){
            cardinalDirection = "SE";
        } else if( (directionInDegrees >= 146.25) && (directionInDegrees <= 168.75) ){
            cardinalDirection = "SSE";
        } else if( (directionInDegrees >= 168.75) && (directionInDegrees <= 191.25) ){
            cardinalDirection = "S";
        } else if( (directionInDegrees >= 191.25) && (directionInDegrees <= 213.75) ){
            cardinalDirection = "SSW";
        } else if( (directionInDegrees >= 213.75) && (directionInDegrees <= 236.25) ){
            cardinalDirection = "SW";
        } else if( (directionInDegrees >= 236.25) && (directionInDegrees <= 258.75) ){
            cardinalDirection = "WSW";
        } else if( (directionInDegrees >= 258.75) && (directionInDegrees <= 281.25) ){
            cardinalDirection = "W";
        } else if( (directionInDegrees >= 281.25) && (directionInDegrees <= 303.75) ){
            cardinalDirection = "WNW";
        } else if( (directionInDegrees >= 303.75) && (directionInDegrees <= 326.25) ){
            cardinalDirection = "NW";
        } else if( (directionInDegrees >= 326.25) && (directionInDegrees <= 348.75) ){
            cardinalDirection = "NNW";
        } else {
            cardinalDirection = "?";
        }

        return cardinalDirection;
    }
}
