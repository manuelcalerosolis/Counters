package com.example.calero.counters.app.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Fragments.DataFragment;
import com.example.calero.counters.app.UI.Tabs.TabListener;
import com.example.calero.counters.app.UI.ViewPagers.ViewPagerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends ActionBarActivity implements DataFragment.Callback {

    private static Context context;
    private static LocationManager locationManager;
    private static Location location;
    private LocationListener locationListener;

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

        getLocation();

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    public static void showAppToast(int resourceId ){
        Toast toast = Toast.makeText(getAppContext(), resourceId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void getLocation() {
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        showLocation();

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
//                showLocation();
            }
            public void onProviderDisabled(String provider){
            }
            public void onProviderEnabled(String provider){
            }
            public void onStatusChanged(String provider, int status, Bundle extras){
                Log.d("GEO", "Provider Status: " + status);
            }
        };

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 30000, 0, locationListener);
    }

    public static double getLocationLatitude(){
        double locationLatitude = 0;
        if (location != null )
            locationLatitude = location.getLatitude();
        Log.d("GEO", "Latitud: " + String.valueOf(locationLatitude));
        return (locationLatitude);
    }

    public static double getLocationLongitude(){
        double locationLongitude = 0;
        if (location != null )
            locationLongitude = location.getLongitude();
        Log.d("GEO", "Longitud: " + String.valueOf(locationLongitude));
        return (locationLongitude);
    }

    private void showLocation() {
        if(location != null)
        {
            Log.d("GEO", "Latitud: " + String.valueOf(location.getLatitude()));
            Log.d("GEO", "Longitud: " + String.valueOf(location.getLongitude()));
            Log.d("GEO", "Precision: " + String.valueOf(location.getAccuracy()));
            Log.d("GEO", String.valueOf(location.getLatitude() + " - " + String.valueOf(location.getLongitude())));
        }
        else
        {
            Log.d("GEO", "Latitud: (sin_datos)");
            Log.d("GEO", "Longitud: (sin_datos)");
            Log.d("GEO", "Precision: (sin_datos)");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Uri contentUri) {
        Intent intent = new Intent( MainActivity.this, DetailActivity.class);
            intent.setData(contentUri);
            startActivity(intent);
    }

}
