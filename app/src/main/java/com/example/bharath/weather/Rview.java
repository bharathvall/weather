package com.example.bharath.weather;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;


public class Rview extends RecyclerView.Adapter<Rview.holder>  {

//    double temp1=0;
//    double temp_min;
//    double temp_max;
//    long pressure=0;
//    long humidity=0;
//    String condition=" ";
//    String mainw=" ";
//    int id=0;
//    public ArrayList<String> arrayLists= new ArrayList<>();

ArrayList<String> arrayLists=new ArrayList<>();
ArrayList<String> dess=new ArrayList<>();
ArrayList<Integer> images=new ArrayList<>();
    public Rview(ArrayList<String> strings,ArrayList<String> des,ArrayList<Integer> images) {
        this.dess=des;
        this.arrayLists = strings;
        this.images=images;
    }



    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rview,parent,false);

        return new holder(view);
    }



    @Override
    public void onBindViewHolder(holder holder, int position)  {
        String str=" ";
        String des=" ";
        str=arrayLists.get(position);
        des=dess.get(position);
//        holder.num.setText(String.valueOf(str));
//        holder.str.setText(des);
        Drawable drawable=null;
        try {
            drawable= ContextCompat.getDrawable(holder.view.getContext(),images.get(position));

            holder.num.setText(String.valueOf(str));
            holder.str.setText(des);
        }catch (IndexOutOfBoundsException e){

        }

holder.imageView.setImageDrawable(drawable);
        Log.d("onbind", "onBindViewHolder: "+str);
//        holder.num.setText(arrayLists.get(position));

    }


    @Override
    public int getItemCount() {
        Log.d("iteamcount", "getItemCount: "+arrayLists.size());
        return arrayLists.size();
    }





    public class holder extends RecyclerView.ViewHolder {
ImageView imageView;
TextView num;
TextView str;
View view;
        public holder(View itemView) {
            super(itemView);
            view=itemView;
imageView=view.findViewById(R.id.imageviewR);
            num=view.findViewById(R.id.valuetextR);
            str=view.findViewById(R.id.textviewR);
        }
    }
}
