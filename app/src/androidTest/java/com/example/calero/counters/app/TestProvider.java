package com.example.calero.counters.app;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.example.calero.counters.app.Data.CountersContract.CountersEntry;

import java.util.Date;

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
        assertEquals(0, cursor.getCount());
        cursor.close();

    }

    // Since we want each test to start with a clean slate, run deleteAllRecords
    // in setUp (called by the test runner before each test).
    public void setUp() {
        deleteAllRecords();
    }

    public void testInsertReadProvider() {
        ContentValues testValues = createTestValues();

        Uri locationUri = mContext.getContentResolver().insert(CountersEntry.CONTENT_URI, testValues);
        long locationRowId = ContentUris.parseId(locationUri);

        assertTrue(locationRowId != -1);
    }

    static ContentValues createTestValues() {

        ContentValues testValues = new ContentValues();
        testValues.put(CountersEntry.COLUMN_NAME, "Demo data");
        testValues.put(CountersEntry.COLUMN_COUNTED, 100);
        testValues.put(CountersEntry.COLUMN_TYPE, 1);
        testValues.put(CountersEntry.COLUMN_STAR, String.valueOf(new Date()));
        testValues.put(CountersEntry.COLUMN_STOP, String.valueOf(new Date()));
        return testValues;
    }


}