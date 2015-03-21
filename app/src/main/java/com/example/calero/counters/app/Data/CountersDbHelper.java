package com.example.calero.counters.app.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.calero.counters.app.Data.CountersContract.CountersEntry;

public class CountersDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "counters.db";

    public CountersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_COUNTERS_TABLE = "CREATE TABLE " + CountersEntry.TABLE_NAME +
                " (" +
                CountersEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                CountersEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                CountersEntry.COLUMN_COUNTED + " LONG, " +
                CountersEntry.COLUMN_TYPE + " INTEGER, " +
                CountersEntry.COLUMN_STAR + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                CountersEntry.COLUMN_STOP + " DATETIME DEFAULT CURRENT_TIMESTAMP " +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_COUNTERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CountersEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
