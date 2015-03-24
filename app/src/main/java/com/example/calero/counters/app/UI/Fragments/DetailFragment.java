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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calero.counters.app.Data.CountersContract;
import com.example.calero.counters.app.R;
import com.example.calero.counters.app.Utils.UtilDate;

import java.util.Date;


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
        countedTextView = (TextView) rootView.findViewById(R.id.detail_counted_textview);
        typeTextView = (TextView) rootView.findViewById(R.id.detail_type_textview);

        return rootView;
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

            int columnIndex;

            columnIndex = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_COUNTED);
            if (columnIndex != -1)
                countedTextView.setText(cursor.getString(columnIndex));

            columnIndex = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_TYPE);
            if (columnIndex != -1)
                typeTextView.setText(cursor.getString(columnIndex));

            columnIndex = cursor.getColumnIndex(CountersContract.CountersEntry.COLUMN_STAR);
            if (columnIndex != -1){
                String dateText = cursor.getString(columnIndex);
                Date date = UtilDate.getTimeStampFromString(dateText);

                String friendlyDateText = UtilDate.getDayName(date.getTimeInMillis());
                friendlyDateTextView.setText(dateText);

                elapsedDateTextView.setText((CharSequence) date.toString());
            }
            
            

//            // Read weather condition ID from cursor
//            int weatherId = data.getInt(COL_WEATHER_CONDITION_ID);
//
//            // Use weather art image
//            mIconView.setImageResource(Utility.getArtResourceForWeatherCondition(weatherId));
//
//            // Read date from cursor and update views for day of week and date
//            long date = data.getLong(COL_WEATHER_DATE);
//            String friendlyDateText = Utility.getDayName(getActivity(), date);
//            String dateText = Utility.getFormattedMonthDay(getActivity(), date);
//            mFriendlyDateView.setText(friendlyDateText);
//            mDateView.setText(dateText);
//
//            // Read description from cursor and update view
//            String description = data.getString(COL_WEATHER_DESC);
//            mDescriptionView.setText(description);
//
//            // For accessibility, add a content description to the icon field
//            mIconView.setContentDescription(description);
//
//            // Read high temperature from cursor and update view
//            boolean isMetric = Utility.isMetric(getActivity());
//
//            double high = data.getDouble(COL_WEATHER_MAX_TEMP);
//            String highString = Utility.formatTemperature(getActivity(), high);
//            mHighTempView.setText(highString);
//
//            // Read low temperature from cursor and update view
//            double low = data.getDouble(COL_WEATHER_MIN_TEMP);
//            String lowString = Utility.formatTemperature(getActivity(), low);
//            mLowTempView.setText(lowString);
//
//            // Read humidity from cursor and update view
//            float humidity = data.getFloat(COL_WEATHER_HUMIDITY);
//            mHumidityView.setText(getActivity().getString(R.string.format_humidity, humidity));
//
//            // Read wind speed and direction from cursor and update view
//            float windSpeedStr = data.getFloat(COL_WEATHER_WIND_SPEED);
//            float windDirStr = data.getFloat(COL_WEATHER_DEGREES);
//            mWindView.setText(Utility.getFormattedWind(getActivity(), windSpeedStr, windDirStr));
//
//            // Read pressure from cursor and update view
//            float pressure = data.getFloat(COL_WEATHER_PRESSURE);
//            mPressureView.setText(getActivity().getString(R.string.format_pressure, pressure));
//
//            // We still need this for the share intent
//            mForecast = String.format("%s - %s - %s/%s", dateText, description, high, low);

            // If onCreateOptionsMenu has already happened, we need to update the share intent now.
            if (shareActionProvider != null) {
                shareActionProvider.setShareIntent(createShareCountedIntent());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) { }

}