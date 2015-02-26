package com.example.calero.counters.app;

import android.database.Cursor;
import android.test.AndroidTestCase;

import com.example.calero.counters.app.Data.CountersContract.CountersEntry;

/**
 * Created by calero on 25/02/2015.
 */
public class TestProvider extends AndroidTestCase {

    public static final String LOG_TAG = TestProvider.class.getSimpleName();

    // brings our database to an empty state

    public void deleteAllRecords() {
        mContext.getContentResolver().delete(
                CountersEntry.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(
                CountersEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        assertEquals(20, cursor.getCount());
        cursor.close();

    }
}