package com.example.bharath.weather;

import java.util.ArrayList;

public interface Icommunication {

    void temp(ArrayList<Double> temp, ArrayList<Double> temp_min, ArrayList<Double> temp_max, ArrayList<Double> wind, ArrayList<Integer> id);

void voilet(double value);
    void dateAndweasum(ArrayList<String> date, ArrayList<String> weasum);

    void weather(double temp, double temp_min, double temp_max, long pressure, long humidity, String condition, String mainw, int id,long rtime,long dtime,double windspeed,double winddirection);
}
