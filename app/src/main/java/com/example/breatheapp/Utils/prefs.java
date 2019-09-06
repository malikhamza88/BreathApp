package com.example.breatheapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

public class prefs {

    private SharedPreferences preferences;

    public prefs(Activity activity) {
        this.preferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public String getDate() {

        String amPm = "";

        long millisTime = preferences.getLong("seconds", 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisTime);
        int a = calendar.get(Calendar.AM_PM);
        if (a == Calendar.AM)
            amPm = "AM";
        else
            amPm = "PM";

        String time = "Last " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " " + amPm;

        return time;

    }

    public void setDate(long date) {
        preferences.edit().putLong("seconds", date).apply();
    }

    public int getSession() {
        return preferences.getInt("sessions", 0);
    }

    public void setSession(int session) {
        preferences.edit().putInt("sessions", session).apply();
    }

    public int getBreath() {
        return preferences.getInt("breath", 0);
    }

    public void setBreath(int breath) {
        preferences.edit().putInt("breath", breath).apply();
    }

}
