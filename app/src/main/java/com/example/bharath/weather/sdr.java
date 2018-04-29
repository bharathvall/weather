package com.example.bharath.weather;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class sdr {

    public String Date(int rex, char code) {
//returs date or day according to the value passed and ittertate through
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, rex);
        Date date = calendar.getTime();
        String datesi = " ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("EEEE");

        if (Objects.equals(code, 'i')) {
            datesi = simpleDateFormat.format(date);
        }
        if (Objects.equals(code, 's')) {
            datesi = simpleDateFormat1.format(date);
        }

        return datesi;
    }

    public int indexfinder(int rex, ArrayList<String> dateerw) {
        int index = 0;
        Log.d("empty1", "comparer: " + "non matched");

        for (int i = 0; i < dateerw.size(); i++) {
            if (Objects.equals(dateerw.get(i), Date(rex, 'i'))) {
                index = dateerw.indexOf(dateerw.get(i));
//                Log.d("indexes", "comparer: " + dateerw.indexOf(dateerw.get(i))+" "+temp.get(index));
            } else {
                Log.d("empty", "comparer: " + "non matched");
            }
        }
        return index;
    }
}
