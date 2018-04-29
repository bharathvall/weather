package com.example.bharath.weather;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by bharath on 3/24/2018.
 */

public class dataExtract extends AsyncTask<String, Void, String> {

    private Icommunication icommunication;

    public void setIcommunication(Icommunication icommunication) {
        this.icommunication = icommunication;
    }

    public interface AsyncResponce {
        void processFinish(ArrayList<Double> temp, ArrayList<Double> tempmax, ArrayList<Double> tempmin, ArrayList<Double> humidity, ArrayList<String> date, ArrayList<String> weather);
    }

    public dataExtract() {

    }

    public dataExtract(AsyncResponce delegate) {
        this.delegate = delegate;
    }

    ;

    String result = " ";

    URL url;

    HttpURLConnection connection = null;

    public AsyncResponce delegate;

    @Override

    protected String doInBackground(String... Urls) {
        try {
            url = new URL(Urls[0]);

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            int data = reader.read();
            while (data != -1) {

                char current = (char) data;

                result = result + current;

                data = reader.read();

            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        ArrayList<String> weather = new ArrayList<>();
        ArrayList<Double> temp = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Double> tempmin = new ArrayList<>();
        ArrayList<Double> tempmax = new ArrayList<>();
        ArrayList<Double> humidity = new ArrayList<>();
        ArrayList<Double> wind = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();

        try {
            JSONObject jsonObjectR = new JSONObject(this.result);
            JSONArray list = jsonObjectR.getJSONArray("list");

            for (int j = 0; j < list.length(); j++) {
                JSONObject loop = list.getJSONObject(j);
                date.add(loop.getString("dt_txt"));

                JSONObject main = loop.getJSONObject("main");
                temp.add(main.getDouble("temp"));
                tempmin.add(main.getDouble("temp_min"));
                tempmax.add(main.getDouble("temp_max"));
                humidity.add(main.getDouble("humidity"));


                JSONObject windo = loop.getJSONObject("wind");
                wind.add(windo.getDouble("speed"));


                JSONArray weatherarray = loop.getJSONArray("weather");
                JSONObject weatherobject = weatherarray.getJSONObject(0);

                weather.add(weatherobject.getString("main"));


                Log.d("tre", "onPostExecute: " + temp);
                id.add(weatherobject.getInt("id"));
//

            }
            Log.d("array", "onPostExecute: " + weather + temp + date + wind);
            Log.d("tempmax", "onPostExecute: " + temp + id);
            icommunication.temp(temp, tempmin, tempmax, wind, id);
            icommunication.dateAndweasum(date, weather);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }
//            Log.d("temp",String.valueOf(temperaturew));
        try {
            delegate.processFinish(temp, tempmax, tempmin, humidity, date, weather);
        } catch (NullPointerException e) {
            e.printStackTrace();

        }


    }
}






