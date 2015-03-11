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

    private static final SQLiteQueryBuilder queryBuilder;

    static {
        queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(CountersEntry.TABLE_NAME);
    }

    @Override
    public boolean onCreate() {
        countersDbHelper = new CountersDbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case COUNTERS:
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;

            case COUNTERS_ID:
                queryBuilder.appendWhere(CountersEntry.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        SQLiteDatabase sqLiteDatabase = countersDbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = uriMatcher.match(uri);

        switch (match) {
            case COUNTERS:
                return CountersEntry.CONTENT_TYPE;
            case COUNTERS_ID:
                return CountersEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        Uri returnUri;
        long id = 0;
        final int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case COUNTERS:
                final SQLiteDatabase sqLiteDatabase = countersDbHelper.getWritableDatabase();
                id = sqLiteDatabase.insert(CountersEntry.TABLE_NAME, null, values);
                if ( id > 0)
                    returnUri = CountersEntry.buildCuntersUri(id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted;
        final SQLiteDatabase sqLiteDatabase = countersDbHelper.getWritableDatabase();
        final int uriType = uriMatcher.match(uri);

        if( selection == null) selection = "1";

        switch (uriType) {
            case COUNTERS:
                rowsDeleted = sqLiteDatabase.delete(CountersEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdated;
        final SQLiteDatabase sqLiteDatabase = countersDbHelper.getWritableDatabase();
        final int uriType = uriMatcher.match(uri);

        if( selection == null) selection = "1";

        switch (uriType) {
            case COUNTERS:
                rowsUpdated = sqLiteDatabase.update(CountersEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CountersContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, CountersContract.PATH_COUNTERS, COUNTERS);

        return matcher;
    }
}


