package com.example.bharath.weather;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.text.DecimalFormat;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements LocationListener, Icommunication {
    TextView text1;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    double lati = 0;
    double longi = 0;
    private TextView text23;
    String adress;
    String conition = " ";
    int id = 0;
    String cityh = "";
    ArrayList<Double> temp = new ArrayList<>();
    ArrayList<Double> tempmax = new ArrayList<>();
    ArrayList<Double> tempmin = new ArrayList<>();
    ArrayList<String> day1 = new ArrayList<>();
    ArrayList<String> days = new ArrayList<>();
    ArrayList<Double> wind = new ArrayList<>();
    ArrayList<Long> diffa = new ArrayList<>();
    ArrayList<String> dateerw = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    ArrayList<Integer> ida = new ArrayList<>();
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    String userName = "";
    boolean namebool = false;
    private static final int MY_LOCATION = 101;
    boolean permissionb = false;
    int timer = 2000;
    Set<String> dess=null;
    Set<String> AllS=null;
    Set<Integer> imagesS=null;
    TextView day11;
    TextView day2;
    TextView day3;
    TextView temp1;
    TextView temp2;
    TextView temp3;
double uv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




 day11=findViewById(R.id.dayid_1);
 day2=findViewById(R.id.dayid_2);
 day3=findViewById(R.id.dayid_3);
 temp1=findViewById(R.id.tempid_1);
temp2=findViewById(R.id.tempid_2);
temp3=findViewById(R.id.tempid_3);
        popupi(getApplicationContext());
        Log.d("dir", "onCreate: " + getApplicationContext().getFilesDir().toString());
        Log.d("dir2", "onCreate: " + Environment.getDataDirectory().toString());
//writerf(getApplicationContext(),"bharath");
//read(getApplicationContext());


        text1 = findViewById(R.id.text);
//if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
//    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
//    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
//}

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);

        }
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
//        if (result==PackageManager.PERMISSION_GRANTED){
//            getLocation();
//            jsonmethod();
//
//
//        }

        ArrayList<Double> temp1 = new ArrayList();
        final TextView text3 = findViewById(R.id.dayid_1);
// dataExtract dataExtract=new dataExtract(new dataExtract.AsyncResponce() {
//
//
//     @Override
//     public void processFinish(ArrayList<Double> temp, ArrayList<Double> tempmax, ArrayList<Double> tempmin, ArrayList<Double> humidity, ArrayList<String> date, ArrayList<String> weather) {
//         ArrayList<Double> temp1=new ArrayList();
//        temp1.addAll(temp);
//         Log.d("temp1", "processFinish: "+temp1);
//     }
// }){
//
//};
// dataExtract.execute("http://openweathermap.org/data/2.5/forecast?lat=17.3850&lon=78.4867&appid=b6907d289e10d714a6e88b30761fae22");


//        String input_date="01/08/2012";
//        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
//        Date dt1= null;
//        try {
//            dt1 = format1.parse(input_date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        DateFormat format2=new SimpleDateFormat("EEEE");
//        String finalDay=format2.format(dt1);

//        text3.setText(finalDay);
//        String[] str=new String[10];
        ArrayList<String> str = new ArrayList<>();

        str.add(String.valueOf("opressure"));
        str.add(String.valueOf("humidity"));
//        str[0]="pressure";
//        str[1]="humidity";
if (boolg()==false) {
    final Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            getLocation();
            ultra();
            jsonmethod();
            Log.d("yoRunning", "run: ");
            timer = timer - 1000;
            if (timer > 0) {

                handler.postDelayed(this, 500);

            }
        }
    };
    handler.postDelayed(runnable, 500);
    Log.d("yournnungd", "onCreate: ");
}
        Log.d("boolg", "onCreate: "+boolg());

        final SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jsonmethod();
                ultra();
                swipeRefreshLayout.setRefreshing(false);


            }
        });

       ;


    }

public void ultra(){
    ultravoilet ultravoilet=new ultravoilet();
    ultravoilet.setIcommunication(this);
    ultravoilet.execute("https://api.openuv.io/api/v1/uv?lat="+this.lati+"&lng="+this.longi+"&dt=2018-01-24T10%3A50%3A52.283Z");
    dataExtract dataExtract1 = new dataExtract();
    dataExtract1.setIcommunication(this);
    dataExtract1.execute("http://api.openweathermap.org/data/2.5/forecast?lat="+this.lati+"&lon="+this.longi+"&APPID=652810bb14530b9c77d39b5d00d41b6e&units=imperial");
}
    public void popupi(final Context context3) {
        final TextView nameview = findViewById(R.id.usernameid);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View v = getLayoutInflater().inflate(R.layout.popup, null);
        Button button = v.findViewById(R.id.buttonp);
        final EditText editText = v.findViewById(R.id.popetext);
        namebool = false;
//ImageView imageView=v.findViewById(R.id.pid);
//imageView.setImageDrawable(getDrawable(R.drawable.anotherbaro));
        File file = new File(context3.getFilesDir(), "Usernamee.txt");
        if (file.exists()) {
            Log.d("fileexixts", "popupi: ");
        } else {
            builder.setView(v);
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
            namebool = true;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    userName = String.valueOf(editText.getText());
                    writerf(context3, userName);
                    nameview.setText("hey! " + read(context3) + " todays weather is... ");
//            nameview.setText(read(context3));
                    alertDialog.dismiss();

                }
            });
        }
        nameview.setText("hey! " + read(context3) + " todays weather is... ");
    }

    public void writerf(Context activity, String un) {
        Log.d("1Writer", "writerf: ");
        File path = activity.getFilesDir();
        File file = new File(path, "Usernamee.txt");
        FileOutputStream fileOutputStream = null;
        try {
            Log.d("2Writer", "writerf: ");
            fileOutputStream = new FileOutputStream(file);
            Log.d("4Writer", "writerf: ");
            fileOutputStream.write(un.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("3Writer", "writerf: ");
    }

    public String read(Context context1) {
        File file = new File(context1.getFilesDir(), "Usernamee.txt");
        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (in != null) {
                in.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String contents = new String(bytes);
        Log.d("heredata", "read: " + contents);
        return contents;
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_LOCATION);
            permissionb = true;
        }
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//            String provider=locationManager.getBestProvider(criteria.getAccuracy(),false )
            if (locationManager != null) {

                Location location1 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                try {
                    this.lati = location1.getLatitude();
                    this.longi = location1.getLongitude();
                    Log.d("latirudeandlongi", "getLocation: " + this.lati + " " + this.longi);
                } catch (NullPointerException n) {
                    n.printStackTrace();
                }
//                this.lati = location1.getLatitude();
//                this.longi = location1.getLongitude();
                ultra();
                jsonmethod();


            }


            if (locationManager != null) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
ultra();
                jsonmethod();


            }


        } catch (SecurityException e) {

            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(final Location location) {


        this.lati = location.getLatitude();
        this.longi = location.getLongitude();
        Log.d("latilongi", "onLocationChanged: "+lati+longi);

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//           textView.setText(addresses.get(0).getAddressLine(0));
//            textView.setText(addresses.get(0).getLocality());
            TextView ext = findViewById(R.id.cityid);
            ext.setText(addresses.get(0).getLocality());
            Log.d("getlocale", "onLocationChanged: "+addresses.get(0).getLocale());

            Log.d("featurename", "onLocationChanged: " + addresses.get(0).getFeatureName());
//            textView.setText(textView.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
//                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        } catch (Exception e) {
            e.printStackTrace();
        }


        final dataExtract data = new dataExtract();//(new dataExtract.AsyncResponce() {

        data.execute("http://api.openweathermap.org/data/2.5/forecast?lat=" + this.lati + "&lon=" + this.longi + "&APPID=652810bb14530b9c77d39b5d00d41b6e");
        data.setIcommunication(this);

    }

public boolean iter(String s,String s1){



        boolean b=false;
        if(s.contains(s1)){
            Log.d("truesw", "iter: ");
            b=true;
        }else{
            b=false;
        }
        return b;
}

    @Override
    protected void onStart() {
        ImageView imageView=findViewById(R.id.imageview);
        Log.d("value1", "onStart: "+imgidGet("value1"));
        imageviewse(imageView,imgidGet("value1"));
        TextView day11=findViewById(R.id.dayid_1);
        TextView descc=findViewById(R.id.dec);
        TextView day2=findViewById(R.id.dayid_2);
        TextView day3=findViewById(R.id.dayid_3);
        TextView temp1=findViewById(R.id.tempid_1);
        TextView temp2=findViewById(R.id.tempid_2);
        TextView temp3=findViewById(R.id.tempid_3);
        TextView ext = findViewById(R.id.cityid);
        String st=getsave("location","yoyo");
        ext.setText(st);
descc.setText(getsave("descc",""));
        //
//        if(Objects.equals( day11.getText().toString(),"")){
//            Log.d("the", "onStart: ");
//        }else{
            day11.setText(textreturn("s1",""));
            day2.setText(textreturn("s2",""));
            day3.setText(textreturn("s3",""));
            temp1.setText(textreturn("i1",""));
            temp2.setText(textreturn("i2",""));
            temp3.setText(textreturn("13",""));

//        }
        Log.d("textret", "onStart: "+textreturn("s1",""));
        super.onStart();
        getLocation();
        TextView temptext = findViewById(R.id.temp);
        if(Objects.equals(gett(),"")) {
            Log.d("prefempty", "onStart: ");
        }else{
            temptext.setText(gett());
        }
        Set ss=new HashSet<>();
        ss=getta();
//        Log.d("etmap", "onStart: "+  ss.iterator().next());
String uvs="";

        ArrayList<String> list=new ArrayList<>(getta());
        Log.d("listre", "onStart: "+list);

            uvs=getsave("UV","yobro");
        Log.d("uvred", "onStart: "+uvs);

            ArrayList<String> sss=new ArrayList<>();
sss.add(uvs);
sss.add("ds");
        sss.add("asd");
        sss.add("ds");
        sss.add("asd");
        sss.add("asd");

        for (String s:list) {
            Log.d("booleanres", "onStart: "+iter(s,"hpa"));



            if (iter(s,"AM")){
                sss.remove(1);
                Log.d("booleans", "onStart: "+s);
                sss.add(1,s);


            }
            if (iter(s,"PM")){
                sss.remove(2);
                Log.d("booleans", "onStart: "+s);
                sss.add(2,s);

            }
            if (iter(s,"kph")){
                sss.remove(3);
                Log.d("booleans", "onStart: "+s);
               sss.add(3,s);

            }
            if (iter(s,"%")){
                sss.remove(4);
                Log.d("booleans", "onStart: "+s);
                sss.add(4,s);

            }
            if (iter(s,"hpa")){
                sss.remove(5);

                sss.add(5,s);

            }



        }
        Log.d("listrew", "onStart: "+sss);
        ArrayList<String> list1=new ArrayList<>(getta1());

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.uv_image);
        images.add(R.drawable.sun2);
        images.add(R.drawable.moon6);
        images.add(R.drawable.windmill);
        images.add(R.drawable.humio);
        images.add(R.drawable.pressure2u);

if (list.size()==0){

}else{
    Log.d("listder", "onStart: "+list);
    list1.size();

}
        ArrayList<String> des = new ArrayList<>();
        des.add("UV Index");
        des.add("sun rise");
        des.add("moon rise");
        des.add("wind");
        des.add("humidity");
        des.add("pressure");

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
//        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new Rview(sss, des, images));

//        jsonmethod();

    }
    @Override
    protected void onDestroy() {
        boola(true);

        textcachsave(day11.getText().toString(),day2.getText().toString(),day3.getText().toString(),temp1.getText().toString(),temp2.getText().toString(),temp3.getText().toString());
TextView temptext=findViewById(R.id.temp);
        TextView decription = findViewById(R.id.dec);
        save("descc",decription.getText().toString());
        Log.d("uvd", "onDestroy: "+uv);
        save("UV",String.valueOf(uv));
        TextView textView=findViewById(R.id.cityid);
        save("location",textView.getText().toString());

        shared(temptext.getText().toString());
               if (AllS.isEmpty()) {

               }else{
                   shareda(AllS,dess);
               }

        super.onDestroy();
    }
    public  void boola(boolean b){
        SharedPreferences sharedPreferences=getSharedPreferences("bool",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("boolk",b);
        editor.apply();
    }
    public  boolean boolg(){
        SharedPreferences sharedPreferences=getSharedPreferences("bool",Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("boolk",false);
    }
public void imgid(String key,int value){
        SharedPreferences sharedPreferences=getSharedPreferences("images",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3=sharedPreferences.edit();
        sharedPreferences.getInt(key,value);
        editor3.apply();

}
public int imgidGet(String key){
    SharedPreferences sharedPreferences=getSharedPreferences("images",Context.MODE_PRIVATE);
    return sharedPreferences.getInt(key,0);
}

    public void save(String key,String value){
        SharedPreferences sharedPreferences=getSharedPreferences("group",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getsave(String key,String Default){
        SharedPreferences sharedPreferences=getSharedPreferences("group",Context.MODE_PRIVATE);
       return sharedPreferences.getString(key,Default);
    }

    public void shared(String s) {
        SharedPreferences sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data", s);
        editor.apply();

    }

    public void shareda(Set<String> All,Set<String> dess) {
        SharedPreferences sharedPreferences = getSharedPreferences("myarray", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("all", All);
//       editor.putStringSet("images", Collections.singleton(String.valueOf(imagesS)));
        editor.putStringSet("dess",dess);

        editor.apply();

    }
    public Set<String> getta(){
        SharedPreferences sharedPreferences1=getSharedPreferences("myarray",Context.MODE_PRIVATE);
Set<String> set=new HashSet<>();
        return sharedPreferences1.getStringSet("all",set);
    }
    public Set<String> getta1(){
        SharedPreferences sharedPreferences1=getSharedPreferences("myarray",Context.MODE_PRIVATE);
        Set<String> set=new HashSet<>();
        return sharedPreferences1.getStringSet("dess",set);
    }
    public Set<String> getta2(){
        SharedPreferences sharedPreferences1=getSharedPreferences("myarray",Context.MODE_PRIVATE);
        Set<String> set=new HashSet<>();
        return sharedPreferences1.getStringSet("dess",set);
    }

    public String gett(){
        SharedPreferences sharedPreferences1=getSharedPreferences("mydata",Context.MODE_PRIVATE);
        return sharedPreferences1.getString("data","");
    }

    public void textcachsave(String s1,String s2,String s3,String i1,String i2,String i3 ){
        SharedPreferences sharedPreferences=getSharedPreferences("forecastString",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("s1",s1);
        editor.putString("s2",s2);
        editor.putString("s3",s3);
editor.putString("i1",(i1));
        editor.putString("i2",(i2));
        editor.putString("i3",(i3));
        editor.apply();
    }
public String textreturn(String key,String defalut){
    SharedPreferences sharedPreferences=getSharedPreferences("forecastString",Context.MODE_PRIVATE);
    return sharedPreferences.getString(key,defalut);

}
    @Override
    public void voilet(double value) {
uv=value;
    }
    @Override
    public void weather(double temp, double temp_min, double temp_max, long pressure, long humidity, String condition, String mainw, int id, long rtime, long dtime, double windspeed, double winddirection) {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.d("permiion result", "weather: " + result);
//        if (result==PackageManager.PERMISSION_GRANTED) {


        DecimalFormat precision = new DecimalFormat("0.0");
        Date date = new Date();
        Date date1 = new Date();
        date1.setTime(dtime * 1000);
        date.setTime(rtime * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:a");
        String Srise = simpleDateFormat.format(date);
        String Mrise = simpleDateFormat.format(date1);
        Log.d("windspeed", "weather: " + windspeed);
        double wind = Double.parseDouble((precision.format(windspeed * 1.6)));
        ArrayList<String> all = new ArrayList<>();
        all.add(String.valueOf(uv));
        all.add(String.valueOf(Srise));
        all.add(String.valueOf(Mrise));
        all.add(String.valueOf(wind) + "kph");
        all.add(String.valueOf(humidity) + "%");
        all.add(String.valueOf(pressure) + " hpa");

 AllS=new HashSet<>(all);

        ArrayList<String> des = new ArrayList<>();
        des.add("UV Index");
        des.add("sun rise");
        des.add("moon rise");
        des.add("wind");
        des.add("humidity");
        des.add("pressure");

       dess = new HashSet<>(des);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.uv_image);
        images.add(R.drawable.sun2);
        images.add(R.drawable.moon6);
        images.add(R.drawable.windmill);
        images.add(R.drawable.humio);
        images.add(R.drawable.pressure2u);

      imagesS=new HashSet<>(images);
        Log.d("String to int ", "weather: "+String.valueOf(images.get(0)));

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
//        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new Rview(all, des, images));

        TextView temptext = findViewById(R.id.temp);

        double tempout = temp;
        tempout = ((tempout - 32) * 5) / 9;
        Log.d("fahrenheat", "weather: " + tempout + " " + temp);

        String deg = "\u2103";
//       shared(String.valueOf(tempout));
        temptext.setText(String.valueOf(precision.format(tempout)) + deg);
//       temptext.setText(gett());
        TextView decription = findViewById(R.id.dec);
        TextView city = findViewById(R.id.cityid);
        final ImageView imageView = findViewById(R.id.imageview);



        decription.setText(condition);

        this.conition = mainw;
        Log.d("mainw", "weather: " + this.conition + id);
        this.id = id;
        imgid("value1",id);
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        Log.d("checkds", "weather: " + timeOfDay);
        boolean day = false;





        imageviewse(imageView, this.id);

//        if (timeOfDay >= 0 && timeOfDay <= 18) {
//
//
//            if (this.id > 200 && this.id <= 232) {
//                imageView.setImageResource(R.drawable.weatherstormday);
//            }
//            if (this.id >= 300 && this.id <= 321) {
//                imageView.setImageResource(R.drawable.weatherdrizzleday);
//            }
//            if (this.id >= 500 && this.id <= 531) {
//                imageView.setImageResource(R.drawable.weatherrainday);
//            }
//            if (this.id >= 600 && this.id <= 622) {
//                imageView.setImageResource(R.drawable.weathersnowscatteredday);
//            }
//            if (this.id >= 701 && this.id <= 781) {
//                imageView.setImageResource(R.drawable.weathermist);
//            }
//            if (this.id == 800) {
//                imageView.setImageResource(R.drawable.weatherclear);
//            }
//            if (this.id >= 801 && this.id <= 804) {
//
//                imageView.setImageResource(R.drawable.weatherclouds);
//            }
//
//            if (this.id >= 900 && this.id <= 906) {
//
//                imageView.setImageResource(R.drawable.weatherwind);
//            }
//            if (this.id >= 951 && this.id <= 962) {
//                imageView.setImageResource(R.drawable.weatherstorm);
//            }
//
//        }
//        if (timeOfDay >= 19 && timeOfDay < 24) {
//
//
//            if (this.id >= 300 && this.id <= 321) {
//                imageView.setImageResource(R.drawable.weatherdrizzle_night);
//            }
//            if (this.id >= 500 && this.id <= 531) {
//                imageView.setImageResource(R.drawable.weatherrainnight);
//            }
//            if (this.id >= 600 && this.id <= 622) {
//                imageView.setImageResource(R.drawable.weathersnowscatterednight);
//            }
//            if (this.id >= 701 && this.id <= 781) {
//                imageView.setImageResource(R.drawable.weathermist);
//            }
//            if (this.id == 800) {
//                imageView.setImageResource(R.drawable.weatherclear);
//            }
//            if (this.id >= 801 && this.id <= 804) {
//
//                imageView.setImageResource(R.drawable.weatherclouds);
//                Log.d("night", "weather: " + "night");
//            }
//            if (this.id >= 900 && this.id <= 906) {
//
//                imageView.setImageResource(R.drawable.weathercloudsnight);
//            }
//            if (this.id >= 951 && this.id <= 962) {
//                imageView.setImageResource(R.drawable.weatherstormnight);
//            }
//        }
        Log.d("mainw2", "weather: " + this.conition + id);


        tempset();
//        }

//
//    switch (this.conition) {
//        case " Thunderstorm":
//            imageView.setImageResource(R.drawable.weatherstormnight);
//        case " Drizzle":
//            imageView.setImageResource(R.drawable.weatherdrizzle_night);
//        case " Rain":
//            imageView.setImageResource(R.drawable.weatherrainnight);
//        case " Snow":
//            imageView.setImageResource(R.drawable.weathersnowscatterednight);
//        case " Atmosphere":
//            imageView.setImageResource(R.drawable.weathermist);
//        case " Clear":
//            imageView.setImageResource(R.drawable.weatherclear);
//        case " Clouds":
//            imageView.setImageResource(R.drawable.weathercloudsnight);
//        case " Extreme":
//            imageView.setImageResource(R.drawable.weatherstormnight);
//    }


//        Log.d("city", "weather: "+cityh);


    }



    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public void temp(ArrayList<Double> temp, ArrayList<Double> temp_min, ArrayList<Double> temp_max, ArrayList<Double> wind, ArrayList<Integer> id) {
        this.temp.addAll(temp);
        this.tempmax.addAll(temp_max);
        this.tempmin.addAll(temp_min);
        this.wind.addAll(wind);
        this.ida.addAll(id);

        Log.d("this.temp", "temp: " + this.wind.size() + " " + this.tempmin.size() + " " + this.tempmax.size() + " " + this.temp.size() + " ");


    }


//public void popupi(final Context context3){
//final TextView nameview=findViewById(R.id.usernameid);
//AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//View v=getLayoutInflater().inflate(R.layout.popup,null);
//    Button button=v.findViewById(R.id.buttonp);
//    final EditText editText=v.findViewById(R.id.popetext);
//namebool=false;
////ImageView imageView=v.findViewById(R.id.pid);
////imageView.setImageDrawable(getDrawable(R.drawable.anotherbaro));
//File file=new File(context3.getFilesDir(),"Usernamee.txt");
//    if(file.exists()) {
//        Log.d("fileexixts", "popupi: ");
//    }else {
//        builder.setView(v);
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        namebool = true;
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userName = String.valueOf(editText.getText());
//                writerf(context3, userName);
//                nameview.setText("hey! " + read(context3) + " todays weather is... ");
////            nameview.setText(read(context3));
//alertDialog.dismiss();
//
//            }
//        });
//    }
//    nameview.setText("hey! " + read(context3) + " todays weather is... ");
//}

//public void writerf(Context activity,String un){
//    Log.d("1Writer", "writerf: ");
//File path=activity.getFilesDir();
//       File file=new File(path,"Usernamee.txt");
//    FileOutputStream fileOutputStream=null;
//    try {
//        Log.d("2Writer", "writerf: ");
//       fileOutputStream=new FileOutputStream(file);
//        Log.d("4Writer", "writerf: ");
//       fileOutputStream.write(un.getBytes());
//    } catch (IOException e) {
//        e.printStackTrace();
//    }finally {
//        if(fileOutputStream!=null){
//            try {
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    Log.d("3Writer", "writerf: ");
//}
//public  String read(Context context1){
//File file=new File(context1.getFilesDir(),"Usernamee.txt");
//    int length = (int) file.length();
//
//    byte[] bytes = new byte[length];
//
//    FileInputStream in = null;
//    try {
//        in = new FileInputStream(file);
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    }
//    try {
//        if (in != null) {
//            in.read(bytes);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    } finally {
//        try {
//            if (in != null) {
//                in.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    String contents = new String(bytes);
//    Log.d("heredata", "read: "+contents);
//    return contents;
//}

    public void Imageviewset() {
//    Log.d("Indexs", "Imageviewset: ");
//Calendar calendar=Calendar.getInstance();
//calendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
//SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//    String daytime=simpleDateFormat.format(calendar.getTime());
//
//    Date date2=null;
//    Date date1=null;
//    String time=" ";
//    SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("kk:mm:ss");
//    Date date=new Date();
//    date.getTime();
//
//
//         time=simpleDateFormat1.format(date);
//         String apple=simpleDateFormat1.format(new Date());
//
//time=time.replace(":","");
//
//String dtaesr=Date(1,'i');
//String data2=Date(2,'i');
//int timei=Integer.valueOf(time);
//int time2=0;
//String time22="";
//    int  time23=0;
//    int ind=0;
//    Log.d("timessss", "Imageviewset: "+time);
//    Log.d("daytime", "Imageviewset: "+daytime);
//    Log.d("daytime", "Imageviewset: "+day1);
//    Log.d("timeary", "Imageviewset: "+this.time);
//
//Log.d("Indexs", "Imageviewset: "+ida.get(6));
//String day2d=" ";
//int day2i=0;
//int ind2=0;
//
//
//    for (int i = 0; i <38 ; i++) {
//
//
//            if (Objects.equals(dtaesr,dateerw.get(i))){
//             ind=   dateerw.indexOf(dateerw.get(i));
//                Log.d("Indexs", "Imageviewset: "+ind+ida.get(i));
//
//                time22=this.time.get(ind);
//                time23= Integer.parseInt(time22.replace(":",""));
//                Log.d("time2", "Imageviewset: "+time23);
//                Log.d("time23", "Imageviewset: "+time22);
////                for (int j = 0; j <7 ; j++) {
////                    time2=this.time.get(ind)
////                    time2= Integer.parseInt(time.replace(":",""));
//            }else {
//
//            }
//        for (int j = 0; j <38 ; j++) {
//            if(Objects.equals(data2,dateerw.get(i))){
//                ind2=dateerw.indexOf(dateerw.get(i));
//                day2d=this.time.get(ind2);
//                Log.d("yo", "Imageviewset: "+this.time.get(ind2)+" "+ind2);
//
//        }else {
//                Log.d("patchol", "Imageviewset: "+" noth9ng found");
//            }
//
////                Log.d("nothing found", "Imageviewset: "+"nothing matched");
//            }
//
//        for (int j = 0; j <38 ; j++) {
//
//        }
//            }
//    Log.d("Indexs", "Imageviewset: "+ind+" "+time22+" "+ time23+" "+timei);
//    String time1s="";
//    int time1i=0;
//    String time2s="";
//    int time2i=0;
//    int j=0;
//    int indx=ind;
//    int ider=0;
//   while ( j <7 ) {
//        time1s=(this.time.get(indx));
//        time1s=time1s.replace(":","");
//        time1i=Integer.parseInt(time1s);
//        time2s=this.time.get(indx+1);
//       time2s=time2s.replace(":","");
//        time2i=Integer.parseInt(time2s);
//        if (timei>=time1i&&timei<=time2i){
//            Log.d("matched", "Imageviewset: "+"matched"+" "+this.ida.get(indx)+" "+this.temp.get(indx));
//this.ida.get(indx);
//ider=indx;
//        }else {
//            Log.d("elsere", "Imageviewset: "+this.ida.get(ind+7)+" "+this.temp.get(ind+7));
//        }
//        indx++;
//        j++;
//
//    }
//    Log.d("condition", "Imageviewset: "+ida.get(ider));
////                    time2=this.time.get(ind)
////                    time2= Integer.parseInt(time.replace(":",""));
//////
//////                }
        ImageView imageView = findViewById(R.id.appCompatImageView);
        imageviewse(imageView, (whileloops(loops(1))));
        ImageView imageView1 = findViewById(R.id.appCompatImageView2);
        ImageView imageView2 = findViewById(R.id.appCompatImageView3);
        imageviewse(imageView1, whileloops(loops(2)));
        imageviewse(imageView2, whileloops(loops(3)));

        Log.d("loopseloop", "Imageviewset: " + whileloops(loops(3)));
        Log.d("whileloop result", "Imageviewset: ");

    }


    public int loops(int rex) {
        String data2 = Date(rex, 'i');
        int indx = 0;
        for (int i = 0; i < dateerw.size(); i++) {
            if (Objects.equals(data2, dateerw.get(i))) {
                indx = dateerw.indexOf(dateerw.get(i));
            }

        }
        return indx;
    }


    public int whileloops(int index) {
        int j = 0;
        String time1s = "";
        int time1i = 0;
        String time2s = "";
        int time2i = 0;

        int indx = index;
        int ider = 0;

        int indexr = 0;
        String time = " ";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("kk:mm:ss");
        Date date = new Date();
        date.getTime();


        time = simpleDateFormat1.format(date);
        time = time.replace(":", "");
        int timei = Integer.parseInt(time);

        int ureturn = 0;
        while (j < 7) {
            time1s = (this.time.get(indx));
            time1s = time1s.replace(":", "");
            time1i = Integer.parseInt(time1s);

            time2s = this.time.get(indx + 1);
            time2s = time2s.replace(":", "");
            time2i = Integer.parseInt(time2s);

            if (timei >= time1i && timei <= time2i) {
                this.ida.get(indx);
                Log.d("whileloope", "whileloops: " + "match" + " " + this.ida.get(indx));
                indexr = indx;
                ureturn = (ida.get(indx));
            }
            indx++;
            j++;

        }

        return ureturn;
    }


    public void imageviewse(ImageView imageView, int id) {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay <= 18) {
            if (id > 200 && id <= 232) {

                imageView.setImageResource(R.drawable.thunder_day);
            }
            if (id >= 300 && id <= 321) {

                imageView.setImageResource(R.drawable.rainy_weather);
            }
            if (id >= 500 && id <= 531) {
                imageView.setImageResource(R.drawable.rainy_day);
            }
            if (id >= 600 && id <= 622) {
                imageView.setImageResource(R.drawable.snow_day);
            }
            if (id >= 701 && id <= 781) {
                imageView.setImageResource(R.drawable.haze_day);
            }
            if (id == 800) {
                imageView.setImageResource(R.drawable.clear_day);
            }
            if (id >= 801 && id <= 804) {

                imageView.setImageResource(R.drawable.mostly_cloudy);
            }

            if (id >= 900 && id <= 906) {

                imageView.setImageResource(R.drawable.windy_day);
            }
            if (id >= 951 && id <= 962) {
                imageView.setImageResource(R.drawable.storm_weather_day);
            }

        }
        if (timeOfDay >= 19 && timeOfDay < 24) {
            if (id > 200 && id <= 232) {
                imageView.setImageResource(R.drawable.thunder_night);
            }
            if (id >= 300 && id <= 321) {
                imageView.setImageResource(R.drawable.rainy_weather);
            }
            if (id >= 500 && id <= 531) {
                imageView.setImageResource(R.drawable.rainy_night);
            }
            if (id >= 600 && id <= 622) {
                imageView.setImageResource(R.drawable.snow_night);
            }
            if (id >= 701 && id <= 781) {
                imageView.setImageResource(R.drawable.haze_night);
            }
            if (id == 800) {
                imageView.setImageResource(R.drawable.clear_night);
            }
            if (id >= 801 && id <= 804) {

                imageView.setImageResource(R.drawable.mostly_cloudy_night);
            }

            if (id >= 900 && id <= 906) {

                imageView.setImageResource(R.drawable.windy_night);
            }
            if (id >= 951 && id <= 962) {
                imageView.setImageResource(R.drawable.storm_weather_night);
            }
        }
    }


    @Override
    public void dateAndweasum(ArrayList<String> date, ArrayList<String> weasum) {

        this.day1.addAll(date);
        Log.d("day1", "dateAndweasum: " + day1);
        ArrayList<String> weathersum = new ArrayList<>(weasum);
        ArrayList<String> time = new ArrayList<>();

        int j = 0;
        String[] st = new String[200];
        Log.d("54", "dateAndweasum: " + date.get(5));

        while (j <= day1.size()) {
            Log.d("yoyoy", "dateAndweasum: " + "yoyoy");
            try {
                String trim = date.get(j);
                String str = (trim.split(" "))[1];
                time.add(str);

                String date2 = date.get(j);
                String str2 = (date2.split(" "))[0];
                dateerw.add(str2);


            } catch (IndexOutOfBoundsException e) {
e.printStackTrace();
            }
            j++;
        }

        Log.d("javasd", "dateAndweasum: " + time + " ");
        Log.d("datese", "dateAndweasum: " + date);
        Log.d("datese", "dateAndweasum: " + dateerw);
        this.time.addAll(time);

        int i = 0;
        try {
            while (i <= day1.size()) {

                String input_date = day1.get(i);

                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
                Date dt1 = null;
                try {
                    dt1 = format1.parse(input_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat format2 = new SimpleDateFormat("EEEE");


                String dayew = String.valueOf(format2.format(dt1));
                this.days.add(i, dayew);

//        Log.d("days", "dateAndweasum: " + day1.size());
//        TextView day12 = findViewById(R.id.dayid_1);
////        day12.setText(lDay);
                i++;
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        Log.d("arraymod", "dateAndweasum: " + days);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String formattedDate = df.format(c.getTime());
        Log.d("today date", "dateAndweasum: " + formattedDate);
        String stre = date.get(6);
        if (stre.compareTo(formattedDate) >= 0) {


        } else {
            Log.d("datescomp", "dateAndweasum: " + stre + " " + formattedDate + "less");
        }


        Imageviewset();
        Log.d("you ", "dateAndweasum: " + indexfinderexp(1, dateerw));
        TextViewSet(1);
//TextViewSet(1);
//Textset();

    }

    public int indexfinderexp(int rex, ArrayList<String> date) {
        int index = 0;
        Log.d("empty1", "comparer: " + "non matched");

        for (int i = 0; i < date.size(); i++) {
            if (Objects.equals(date.get(i), Date(rex, 'i'))) {
                index = date.indexOf(date.get(i));
                Log.d("indexes", "comparer: " + date.indexOf(date.get(i)) + " " + temp.get(index));
            } else {
                Log.d("empty", "comparer: " + "non matched");
            }

        }

        Log.d("emprty", "indexfinder: " + "tee" + index);

        return index;
    }


    public void assignment() {
        ArrayList<Long> differ = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat time1 = new SimpleDateFormat("kk:mm:ss");
        String now = df.format(c.getTime());
        long date = System.currentTimeMillis();

        String timeString1 = time1.format(date);
//        for (int i = 0; i < time.size(); i++) {
//        String time = this.time.get(0);
//        Log.d("timeat0", "assignment: " + this.time.get(0) + " " + timeString1);
//        for (int i = 0; i <this.time.size() ; i++) {

//array for minimum difference
//        try {
////            Date mDate = df.parse(timeString1);
////            Date Date = df.parse(this.time.get(i));
////            long millew = Date.getTime();
////            long mills = mDate.getTime();
////            long minus = mills - millew;
////            differ.add(minus);
////            Log.d("millsdiffers", "assignment: " + mills + " " + millew + " " + minus);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        to find the minimum value in minumem diff array

//    }
//        long minIndex = differ.indexOf(Collections.min(differ));
//        Log.d("leat", "assignment: "+minIndex);
//
//        Log.d("differarry", "assignment: "+differ.size());

//        TextView textView=findViewById( R.id.dayid_1);
//        int valuesetter= (int) (minIndex);
//        textView.setText(String.valueOf(temp.get(valuesetter)));
        for (int i = 0; i < time.size(); i++) {
            try {
                Date d1 = df.parse(timeString1);
                Date d2 = df.parse(this.time.get(i));
                long mills = d1.getTime() - d2.getTime();
                Log.d("mills", "assignment: " + d1.getTime() + " " + d2.getTime());
                int hours = (int) (mills / (1000 * 60 * 60));
                int mins = (int) (mills / (1000 * 60)) % 60;
                String diff = hours + " " + mins;
                String differt = (hours + ":" + mins + ":" + 00);
                Date d3 = df.parse(differt);
                long dd = d3.getTime();
                diffa.add((dd));


                Log.d("diff", "assignment: " + diffa.get(i));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        long minIndex = differ.indexOf(Collections.min(diffa));

        long minindex2 = sorter(diffa);
        Log.d("mindiffa", "assignment: " + minindex2);

//        final int index =Ints.indexOf(diffa, minindex2);
    }

    public long sorter(ArrayList<Long> sorta) {
        long min = sorta.get(0);
        for (long minf : sorta) {

            if (minf < min) {
                min = minf;
            }
        }

        return min;
    }

    ;

    public void jsonmethod() {
        json_weather json_weather = new json_weather();
        json_weather.execute("http://api.openweathermap.org/data/2.5/weather?lat=" + this.lati + "&lon=" + this.longi + "&APPID=652810bb14530b9c77d39b5d00d41b6e&units=imperial");
        Log.d("latitude", "jsonmethod: " + this.longi + " " + this.lati);
        json_weather.setIcommunication(this);
    }

    public void Textset() {
        TextView temp1 = findViewById(R.id.tempid_2);
        temp1.setText((int) Averagertemp(1));
    }

    public void TextViewSet(int rex) {

        String deg = "\u2103";
        DecimalFormat precision = new DecimalFormat("0.0");
        TextView temp1 = findViewById(R.id.tempid_1);

        temp1.setText(String.valueOf(precision.format(Averagertemp(rex))) + deg);
        TextView day1 = findViewById(R.id.dayid_1);
        day1.setText(Date(1, 's'));


//Date(1,'s');
//    Log.d("ees", "comparer: "+Date(0,'i'));
//    Log.d("average temp rex", "comparer: "+Averagertemp(2));

        TextView day2 = findViewById(R.id.dayid_2);
        day2.setText(Date(2, 's'));
        TextView temp2 = findViewById(R.id.tempid_2);
        temp2.setText(String.valueOf(precision.format(Averagertemp(rex + 1))) + deg);
        TextView day3 = findViewById(R.id.dayid_3);
        day3.setText(Date(3, 's'));
        TextView temp3 = findViewById(R.id.tempid_3);
        temp3.setText(String.valueOf(precision.format(Averagertemp(rex + 2))) + deg);
        Log.d("latilonge", "TextViewSet: " + lati + longi);

    }

//    public void TextView() {
//        TextView textView = findViewById(R.id.dayid_1);
//        textView.setText(String.valueOf(Date(1, 's')));
//        TextView day2 = findViewById(R.id.dayid_1);
//        day2.setText(Date(2, 's'));
//        TextView day3 = findViewById(R.id.dayid_3);
//        day3.setText(Date(3, 's'));
//
//
//    }

    public void tempset() {
        Log.d("yo called", "tempset: " + indexfinder(2));
//        TextView textView=findViewById(R.id.tempid_1);
//        textView.setText((int) Averagertemp(0));


    }

    public double Averagertemp(int Drex) {

        double div = 0;
        double dd = 0;
        int indexi = indexfinder(Drex);

        for (int i = 0; i <= 7; i++) {
            dd += temp.get(indexi);
            div = (dd / 8);


            div = ((div - 32) * 5) / 9;

            Log.d("tempinc", "comparer: " + temp.get(indexi) + " " + dd + " " + div + " ");
            indexi = indexi + 1;

        }
        Log.d("indexfinder", "Averagertemp: " + indexfinder(3));


        return div;
    }

    public void logde() {

        Log.d("logdata", "logde: " + dateerw);
    }

    public int indexfinder(int rex) {
        int index = 0;
//        Log.d("empty1", "comparer: " + "non matched");
//
//        for (int i = 0; i < dateerw.size(); i++) {
//            if (Objects.equals(dateerw.get(i), Date(rex, 'i'))) {
//                index = dateerw.indexOf(dateerw.get(i));
//                Log.d("indexes", "comparer: " + dateerw.indexOf(dateerw.get(i)) + " " + temp.get(index));
//            } else {
//                Log.d("empty", "comparer: " + "non matched");
//            }
//
//        }
//
//        Log.d("emprty", "indexfinder: " + "tee" + index);

        return indexfinderexp(rex, dateerw);
    }


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

}


//        Geocoder gcd = new Geocoder(context, Locale.getDefault());
//        List<Address> addresses = null;
//        try {
//            addresses = gcd.getFromLocation(latitude, longitude, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (addresses.size() > 0) {
//            System.out.println(addresses.get(0).getLocality());
//            Log.d("taggsss", addresses.get(0).getLocality());
////            text.setText(String.valueOf(addresses.get(0).getLocality()));
//        }
//        else {
//            // do your stuff
//        }






