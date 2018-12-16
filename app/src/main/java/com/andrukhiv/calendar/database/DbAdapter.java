package com.andrukhiv.calendar.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.andrukhiv.calendar.tables.CalendarTable;
import com.rilixtech.agendacalendarview.models.BaseCalendarEvent;
import com.rilixtech.agendacalendarview.models.CalendarEvent;

import java.io.IOException;
import java.util.ArrayList;


public class DbAdapter {

    private static final String TAG = "DbAdapter";

    private SQLiteDatabase mDb;
    private DbHelper mDbHelper;

    @SuppressLint("StaticFieldLeak")
    private static DbAdapter sInstance;


    public static synchronized DbAdapter getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbAdapter(context.getApplicationContext());
        }
        return sInstance;
    }


    private DbAdapter(Context context) {
        mDbHelper = new DbHelper(context);
    }


    public void createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
    }


    public void open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
    }


    public void close() {
        mDbHelper.close();
    }


    public ArrayList<CalendarEvent> getCalendarEvents() {

        ArrayList<CalendarEvent> result = new ArrayList<>();
        String sql = "SELECT * FROM " + CalendarTable.CALENDAR_NAME_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                result.add(new BaseCalendarEvent(
                        cursor.getLong(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_LOCATION)),
                        cursor.getInt(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_DATE_START)),
                        cursor.getInt(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_DATE_END)),
                        cursor.getInt(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_END_ALL_DAY)),
                        cursor.getString(cursor.getColumnIndex(CalendarTable.CALENDAR_COLUMN_DURATION))
            ));
            }
            cursor.close();
        }
        return result;
    }
}





























