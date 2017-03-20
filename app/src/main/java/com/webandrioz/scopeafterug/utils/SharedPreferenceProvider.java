package com.webandrioz.scopeafterug.utils;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gipsy_danger on 25/11/16.
 */

public class SharedPreferenceProvider {

    public  void storeData(Context con, String key, String data) {

        SharedPreferences sharedPreferences = con.getSharedPreferences("Piyush1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.apply();


    }
    public void deleteKey(Context con,String key){
        SharedPreferences sharedPreferences = con.getSharedPreferences("Piyush1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
    public void deleteKey(Context con,String key,String str){
        SharedPreferences sharedPreferences = con.getSharedPreferences("Piyush1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }


    public  void deleteData(Context con) {

        SharedPreferences sharedPreferences = con.getSharedPreferences("Piyush", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
    }

    public String fatchDataLegislators(Context con ,String key){

        SharedPreferences sharedPreferences=con.getSharedPreferences("Piyush1",Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key,"0");
    }
}
