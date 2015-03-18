package com.example.calero.counters.app.Data;

import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calero.counters.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CountersHolder {

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
            imageViewType.setImageResource(cursor.getInt(columnType));
    }

    public void setTextViewNameFromCursor(Cursor cursor){
        int columnName = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_NAME);
        if (columnName != -1)
            textViewName.setText(cursor.getString(columnName));
    }

    public void setTextViewCounterFromCursor(Cursor cursor){
        int columnCounted = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_COUNTED);
        if (columnCounted != -1)
            textViewCounter.setText(cursor.getString(columnCounted));
    }

    public void setTextViewDurationFromCursor(Cursor cursor) throws ParseException {

        int minutes;
        int columnStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
        int columnStop = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STOP);

        try {
            if (columnStart != -1 && columnStop != -1) {

                SimpleDateFormat simpleDateFormatStart = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date dateStart = simpleDateFormatStart.parse(cursor.getString(columnStart));

                SimpleDateFormat simpleDateFormatStop = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date dateStop = simpleDateFormatStop.parse(cursor.getString(columnStart));

                minutes = (int) (TimeUnit.MILLISECONDS.toMinutes(dateStart.getTime()));
                minutes =-(int) (TimeUnit.MILLISECONDS.toMinutes(dateStop.getTime()));

                textViewDuration.setText(minutes);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

    }

}
