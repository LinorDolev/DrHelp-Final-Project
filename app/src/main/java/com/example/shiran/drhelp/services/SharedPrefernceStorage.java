package com.example.shiran.drhelp.services;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefernceStorage {
    private final String FILE = "Dr.Help";
    private Context context;

    public SharedPrefernceStorage(Context context) {
        this.context = context;
    }

    public void saveData(String val, String key) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, val).apply();
    }

    public String readData(String key) {
        return readData("", key);
    }

    private String readData(String val, String key) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, val);
    }

}
