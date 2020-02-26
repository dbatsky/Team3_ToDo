package com.example.team3_todo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    static String UNIQUE_ID = null;

    private SharedPreferences sharedPreferences;

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void SetNightModeState(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();
    }

    public Boolean loadNightModeState() {

        return sharedPreferences.getBoolean("NightMode", false);
    }

    public void setUniqueId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UNIQUE_ID", id);
        editor.apply();
    }

    public String loadUniqueId() {
        return sharedPreferences.getString("UNIQUE_ID", null);
    }
}
