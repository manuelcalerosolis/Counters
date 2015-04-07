package com.example.calero.counters.app.Data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by calero on 24/02/2015.
 */
public class CountersContract {

    public static final String CONTENT_AUTHORITY = "com.example.calero.counters.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_COUNTERS = "counters";
    public static final String DATE_FORMAT = "ddMMyyyy";

    public static String getDbDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    public static Date getDateFromDb(String dateText) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dbDateFormat.parse(dateText);
        } catch ( ParseException e ) {
            e.printStackTrace();
            return null;
        }
    }

    public static final class CountersEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COUNTERS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTERS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COUNTERS;

        public static final String TABLE_NAME = "counters";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COUNTED = "counted";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_STAR = "star";
        public static final String COLUMN_STOP = "stop";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";

        public static final String[] COUNTERS_COLUMNS = {
                TABLE_NAME + "." + COLUMN_ID,
                COLUMN_NAME,
                COLUMN_COUNTED,
                COLUMN_TYPE,
                COLUMN_STAR,
                COLUMN_STOP,
                COLUMN_LATITUDE,
                COLUMN_LONGITUDE
        };

        public static Uri buildCuntersUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildCountersEntry() {
            return CONTENT_URI;
        }

        public static Uri buildCountersEntryWithId(int id) {
//            return CONTENT_URI.buildUpon().appendQueryParameter(COLUMN_ID, Integer.toString(id)).build();
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
