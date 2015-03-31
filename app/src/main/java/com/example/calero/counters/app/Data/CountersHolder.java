package com.example.calero.counters.app.Data;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.Utils.UtilApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CountersHolder {

    private static final String LOG_TAG = CountersHolder.class.getSimpleName();

    TextView textViewCounter;
    TextView textViewName;
    ImageView imageViewType;
    TextView textViewDuration;

    CountersHolder(View row) {
        this.imageViewType = (ImageView) row.findViewById(R.id.iconTypeCounter);
        this.textViewCounter = (TextView) row.findViewById(R.id.list_item_counter_textview);
        this.textViewName = (TextView) row.findViewById(R.id.list_item_name_textview);
        this.textViewDuration = (TextView) row.findViewById(R.id.list_item_duration_textview);
    }

    public void setImageViewTypeFromCursor(Cursor cursor){
        int columnType = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_TYPE);
        if (columnType != -1)
            imageViewType.setImageResource(UtilApplication.getArtResourceForCounterType(cursor.getInt(columnType)));
    }

    public void setTextViewNameFromCursor(Cursor cursor){
        int columnName = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_TYPE);
        if (columnName != -1)
            textViewName.setText(cursor.getString(columnName));
    }

    public void setTextViewCounterFromCursor(Cursor cursor){
        int columnCounted = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_COUNTED);
        if (columnCounted != -1)
            textViewCounter.setText(cursor.getString(columnCounted));
    }

    public void setTextViewDurationFromCursor(Cursor cursor) throws ParseException {

        int minutes = 0;
        int columnStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
        int columnStop = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STOP);

        try {
            if (columnStart != -1 && columnStop != -1) {

                SimpleDateFormat simpleDateFormatStart = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date dateStart = simpleDateFormatStart.parse(cursor.getString(columnStart));

                SimpleDateFormat simpleDateFormatStop = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date dateStop = simpleDateFormatStop.parse(cursor.getString(columnStop));

                minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(dateStart.getTime())) - (int) (TimeUnit.MILLISECONDS.toMinutes(dateStop.getTime()));

            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        textViewDuration.setText( String.valueOf(minutes) + " min");
    }

    public void setTextViewDatesIntervalFromCursor(Cursor cursor) throws ParseException {

        String datesInterval = "Parse error";
        int columnStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
        int columnStop = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STOP);

        Log.d(LOG_TAG, "FroM SQLLite " + cursor.getString(columnStart));

        try {
            if (columnStart != -1 && columnStop != -1) {

                SimpleDateFormat simpleDateFormatStart = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss", Locale.US) ;// EEE, d MMM yyyy, HH:mm" ); // hh:mm:ss");
                Date dateStart = simpleDateFormatStart.parse(cursor.getString(columnStart));

                Log.d(LOG_TAG, "Date start " +  dateStart.toString());
                Log.d(LOG_TAG, "FroM SQLLite " + cursor.getString(columnStart));

//                SimpleDateFormat simpleDateFormatStop = new SimpleDateFormat("HH:mm:ss");
//                Date dateStop = simpleDateFormatStop.parse(cursor.getString(columnStop));

                datesInterval = dateStart.toString(); // + " " + dateStop.toString();

            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        textViewName.setText( datesInterval);
    }


}
