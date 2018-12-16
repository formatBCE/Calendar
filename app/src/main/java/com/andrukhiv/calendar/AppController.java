package com.andrukhiv.calendar;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.andrukhiv.calendar.database.DbAdapter;


public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbAdapter mDbHelper = DbAdapter.getInstance(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
    }

    // за замовчуванням автоматичний вибір теми додатку в залежності від часу доби
    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);
    }
}