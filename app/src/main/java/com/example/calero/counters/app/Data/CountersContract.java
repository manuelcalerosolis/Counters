package com.example.calero.counters.app.Data;

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

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_COUNTERS;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_COUNTERS;

        public static final String TABLE_NAME = "counters";

        public static final String COLUMN_ID = "_ID";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COUNTED = "counted";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_STAR = "star";
        public static final String COLUMN_STOP = "stop";

        public static Uri buildCuntersUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
