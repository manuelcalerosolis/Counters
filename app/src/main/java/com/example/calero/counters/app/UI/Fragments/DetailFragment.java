package com.example.calero.counters.app.UI.Fragments;

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calero.counters.app.Data.CountersContract;
import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Activities.MainActivity;
import com.example.calero.counters.app.Utils.UtilApplication;
import com.example.calero.counters.app.Utils.UtilDate;

import java.text.ParseException;
import java.util.Locale;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    public static final String DETAIL_URI = "URI";

    private static final String COUNTERS_SHARE_HASHTAG = " #CountersApp";

    private ShareActionProvider shareActionProvider;
    private String counters;

    private Uri uri;

    private static final int DETAIL_LOADER = 0;

    private TextView countedTextView;
    private TextView typeTextView;
    private TextView friendlyDateTextView;
    private TextView elapsedDateTextView;
    private TextView minutesTextView;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private ImageView iconImageView;
    private ImageView mapImageView;

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
            uri = arguments.getParcelable(DetailFragment.DETAIL_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        friendlyDateTextView = (TextView) rootView.findViewById(R.id.detail_day_textview);
        elapsedDateTextView = (TextView) rootView.findViewById(R.id.detail_dates_textview);
        minutesTextView = (TextView) rootView.findViewById(R.id.detail_minutes_textview);
        countedTextView = (TextView) rootView.findViewById(R.id.detail_counted_textview);
        typeTextView = (TextView) rootView.findViewById(R.id.detail_type_textview);
        iconImageView = (ImageView) rootView.findViewById(R.id.detail_icon_imageview);
        latitudeTextView = (TextView) rootView.findViewById(R.id.detail_latitude_textview);
        longitudeTextView = (TextView) rootView.findViewById(R.id.detail_longitude_textview);
        mapImageView = (ImageView) rootView.findViewById(R.id.detail_map_imageview);
        mapImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMapView();
            }
        });

        return rootView;
    }

    public double getDoubleLatitudeTextView() {
        double latitude = 0;
        latitude = Double.parseDouble(latitudeTextView.getText().toString());
        return latitude;
    }

    public double getDoubleLongitudeTextView() {
        double longitude = 0;
        longitude = Double.parseDouble(longitudeTextView.getText().toString());
        return longitude;
    }

    public String getStringLatitudeTextView() {
        return latitudeTextView.getText().toString();
    }

    public String getStringLongitudeTextView() {
        return longitudeTextView.getText().toString();
    }


    private void openMapView() {
        if ( getDoubleLatitudeTextView() != 0 || getDoubleLongitudeTextView() != 0 ) {
            Uri uri = Uri.parse("geo:" + getStringLatitudeTextView() + "," + getStringLongitudeTextView());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);

            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Log.d(LOG_TAG, "Couldn't call " + uri.toString() + ", no receiving apps installed!");
            }
        }
        else
        {
            Log.d(LOG_TAG, "URI " + uri);
            Log.d(LOG_TAG, "Latitude " + latitudeTextView.getText().toString());
            Log.d(LOG_TAG, "Longitude " + longitudeTextView.getText().toString());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        if (counters != null) {
            shareActionProvider.setShareIntent(createShareCountedIntent());
        }

    }

    private Intent createShareCountedIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, counters + COUNTERS_SHARE_HASHTAG);
        return shareIntent;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    void onLocationChanged( String newLocation ) {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if ( null != uri ) {
            return new CursorLoader(
                    getActivity(),
                    uri,
                    CountersContract.CountersEntry.COUNTERS_COLUMNS,
                    null,
                    null,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor != null && cursor.moveToFirst()) {

            int columnIndexStart;
            int columnIndexStop;

            columnIndexStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_COUNTED);
            if (columnIndexStart != -1)
                countedTextView.setText(cursor.getString(columnIndexStart));

            columnIndexStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_TYPE);
            if (columnIndexStart != -1) {
                iconImageView.setImageResource(UtilApplication.getArtResourceForCounterType(cursor.getInt(columnIndexStart)));
                typeTextView.setText(
                    UtilApplication.getTextResourceForCounterType(
                        cursor.getInt(columnIndexStart)));
            }

            columnIndexStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
            columnIndexStop = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STOP);

            if (columnIndexStart != -1 && columnIndexStop != -1){
                try {
                    friendlyDateTextView.setText(
                        UtilDate.getFormattedMonthDay(
                                cursor.getString(columnIndexStart)));
                    elapsedDateTextView.setText(
                        UtilDate.getDateInLine(
                            cursor.getString(columnIndexStart), cursor.getString(columnIndexStop)));
                    minutesTextView.setText(
                        UtilDate.getMinutesAndSecondsDifference(
                            cursor.getString(columnIndexStart), cursor.getString(columnIndexStop)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            columnIndexStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_LATITUDE);
            if (columnIndexStart != -1)
            latitudeTextView.setText(
                getActivity().getString(
                    R.string.format_latitude, cursor.getDouble(columnIndexStart)));

//            latitudeTextView.setText(String.valueOf(cursor.getDouble(columnIndexStart)));

            columnIndexStart = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_LONGITUDE);
            if (columnIndexStart != -1)
                longitudeTextView.setText(String.valueOf(cursor.getDouble(columnIndexStart)));

            // Shared information--------------------------------------------

            counters = String.format("%s - %s", typeTextView.getText(), countedTextView.getText());
            if (shareActionProvider != null) {
                shareActionProvider.setShareIntent(createShareCountedIntent());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {}

}