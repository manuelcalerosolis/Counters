package com.example.calero.counters.app.Data;

import com.example.calero.counters.app.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;

import java.text.ParseException;

public class CountersAdapter extends CursorAdapter {

    public CountersAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_data_row, parent, false);
        CountersHolder countersHolder = new CountersHolder(view);
        view.setTag(countersHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        CountersHolder countersHolder = (CountersHolder) view.getTag();

        countersHolder.setImageViewTypeFromCursor(cursor);
        countersHolder.setTextViewNameFromCursor(cursor);
        countersHolder.setTextViewCounterFromCursor(cursor);
        try {
            countersHolder.setTextViewDurationFromCursor(cursor);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
