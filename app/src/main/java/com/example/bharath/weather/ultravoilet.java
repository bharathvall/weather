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


public class ultravoilet extends AsyncTask<String, Void, String> {

    String result = " ";

    URL url;

Icommunication icommunication;

    public void setIcommunication(Icommunication icommunication) {
        this.icommunication = icommunication;
    }

    HttpURLConnection connection = null;

    @Override
    protected String doInBackground(String... urls) {

        try {
            this.url = new URL(urls[0]);

            connection = (HttpURLConnection) url.openConnection();
connection.addRequestProperty("x-access-token", "4657ab7c10c81de0d3d8a2de45056c6b");
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            while (data != -1) {
                char d = (char) data;

                result = result + d;
                Log.d("ultraresult", "doInBackground: "+result);
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
        try {
            JSONObject json =new JSONObject(this.result);
            JSONObject jsonObject=json.getJSONObject("result");

double value=jsonObject.getDouble("uv");
icommunication.voilet(value);
            Log.d("uvreading", "onPostExecute: "+jsonObject.getDouble("uv")+json.has("result")+jsonObject.has("uv"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
