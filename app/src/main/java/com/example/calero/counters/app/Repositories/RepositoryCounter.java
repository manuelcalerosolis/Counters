package com.example.calero.counters.app.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.calero.counters.app.MainActivity;

public class RepositoryCounter {

    private final Context context;

    public RepositoryCounter(){
        this.context = MainActivity.getAppContext();
        CounterSQLLiteOpenHelper counterSQLLite = new CounterSQLLiteOpenHelper(context);
    }

    public long insertCounter(ContentValues contentValues){
        CounterSQLLiteOpenHelper counterSQLLite = new CounterSQLLiteOpenHelper(context);
        return (counterSQLLite.insert(contentValues));
    }

    public Cursor cursorCounters(){
        CounterSQLLiteOpenHelper counterSQLLite = new CounterSQLLiteOpenHelper(context);
        SQLiteDatabase sqLiteDatabase = counterSQLLite.getReadableDatabase();
        Cursor cursorCounters = sqLiteDatabase.rawQuery("SELECT * FROM Counters ORDER BY timeStampStart DESC", null);
        return cursorCounters;
    }

    public class CounterSQLLiteOpenHelper extends SQLiteOpenHelper{

        public SQLiteDatabase sqLiteDatabase;

        public CounterSQLLiteOpenHelper(Context context ){
            super(context,"Counters",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE Counters (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "stringName TEXT, " +
                    "longCounted LONG, " +
                    "intType INTEGER, " +
                    "timeStampStart DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "timeStampStop DATETIME DEFAULT CURRENT_TIMESTAMP)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Counters");
            onCreate(sqLiteDatabase);
        }

        @Override
        public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            onUpgrade(sqLiteDatabase, oldVersion, newVersion);
        }

        public void open(){
            SQLiteDatabase sqLiteDatabaseCounters = getWritableDatabase();
            if(sqLiteDatabaseCounters !=null)
                Log.d("textViewCounter", "La base de datos se abrio bien");
            else
                Log.d("textViewCounter", "La base de datos no se abrio");
        }

        public long insert(ContentValues values){
            long longInsert = 0;
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            if(sqLiteDatabase!=null){
                longInsert = sqLiteDatabase.insert("Counters", null, values);
                if ( longInsert != 0)
                    Log.d("textViewCounter", "El registro se inserto correctamente");
                else
                    Log.d("textViewCounter", "El registro no se inserto");
                sqLiteDatabase.close();
            }
            else
                Log.d("textViewCounter", "La base de datos no se abrio");
            return (longInsert);
        }

        @Override
        public void close(){
            if (sqLiteDatabase !=null)
                sqLiteDatabase.close();
            super.close();
        }
    }
}
