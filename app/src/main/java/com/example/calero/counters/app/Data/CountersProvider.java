package com.example.calero.counters.app.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.calero.counters.app.Data.CountersContract.CountersEntry;

public class CountersProvider extends ContentProvider {

    private static final int COUNTERS = 100;
    private static final int COUNTERS_ID = 101;

    private CountersDbHelper countersDbHelper;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        countersDbHelper = new CountersDbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Set the table
        queryBuilder.setTables(CountersEntry.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            // If the incoming URI was for all of table3
            case COUNTERS:

                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;

            // If the incoming URI was for a single row
            case COUNTERS_ID:

                queryBuilder.appendWhere(CountersEntry.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;

        default:
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        SQLiteDatabase db = countersDbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CountersContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, CountersContract.PATH_COUNTERS, COUNTERS);

        return matcher;
    }
}


