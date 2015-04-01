package com.example.calero.counters.app.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.widget.Toast;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Fragments.DataFragment;
import com.example.calero.counters.app.UI.Tabs.TabListener;
import com.example.calero.counters.app.UI.ViewPagers.ViewPagerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends ActionBarActivity implements DataFragment.Callback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener { // FragmentActivity{

    private static Context context;
    protected GoogleApiClient googleApiClient;
    protected Location lastLocation;

//    private final ActionBar actionBar = getSupportActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this));
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        actionBar.setSelectedNavigationItem(position);
                    }
                });

        TabListener tabListener = new TabListener(viewPager, actionBar);

        actionBar.addTab(
                actionBar.newTab()
                        .setContentDescription(R.string.fragment_counter)
                        .setIcon(R.drawable.fragment_counter_only)
                        .setTabListener(tabListener));

        actionBar.addTab(
                actionBar.newTab()
                        .setContentDescription(R.string.fragment_counter_time)
                        .setIcon(R.drawable.fragment_counter_time)
                        .setTabListener(tabListener));

        actionBar.addTab(
                actionBar.newTab()
                        .setContentDescription(R.string.fragment_counter_set)
                        .setIcon(R.drawable.fragment_counter_set)
                        .setTabListener(tabListener));

        actionBar.addTab(
                actionBar.newTab()
                        .setContentDescription(R.string.fragment_counter_data)
                        .setIcon(R.drawable.fragment_counter_data)
                        .setTabListener(tabListener));

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public static void showAppToast(int resourceId ){
        Toast toast = Toast.makeText(getAppContext(), resourceId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show( );
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
//            lmLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onItemSelected(Uri contentUri) {
        Intent intent = new Intent(this, DetailActivity.class);
            intent.setData(contentUri);
            startActivity(intent);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
