package com.example.calero.counters.app.Data;

import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.Utils.UtilApplication;
import com.example.calero.counters.app.Utils.UtilDate;

import java.text.ParseException;

public class CountersHolder {

    private static final String LOG_TAG = CountersHolder.class.getSimpleName();

    TextView textViewCounter;
    TextView textViewName;
    ImageView imageViewType;
    TextView textViewMinutes;
    TextView textViewDates;

    CountersHolder(View row) {
        this.imageViewType = (ImageView) row.findViewById(R.id.iconTypeCounter);
        this.textViewCounter = (TextView) row.findViewById(R.id.list_item_counter_textview);
        this.textViewName = (TextView) row.findViewById(R.id.list_item_name_textview);
        this.textViewDates = (TextView) row.findViewById(R.id.list_item_dates_textview);
        this.textViewMinutes = (TextView) row.findViewById(R.id.list_item_duration_textview);
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
        int columnStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
        int columnStop = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STOP);

        if (columnStart != -1 && columnStop != -1) {
            textViewMinutes.setText(
                    UtilDate.getMinutesAndSecondsDifference(
                            cursor.getString(columnStart), cursor.getString(columnStop)) + " min");
        }
    }

    public void setTextViewDatesIntervalFromCursor(Cursor cursor) throws ParseException {

        int columnStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
        int columnStop = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STOP);

        if (columnStart != -1 && columnStop != -1) {
            textViewName.setText(
                UtilDate.getFormattedMonthDay(
                        cursor.getString(columnStart)));
//                SimpleDateFormat simpleDateFormatStop = new SimpleDateFormat("HH:mm:ss");
//                Date dateStop = simpleDateFormatStop.parse(cursor.getString(columnStop));

        }

    }

    public void setTextViewDatesFromCursor(Cursor cursor) throws ParseException {
        int columnStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
        int columnStop = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STOP);

        if (columnStart != -1 && columnStop != -1) {
            textViewDates.setText(
                    UtilDate.getDateInLine(
                            cursor.getString(columnStart), cursor.getString(columnStop)));
        }
    }


}
