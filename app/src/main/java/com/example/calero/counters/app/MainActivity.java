package com.example.calero.counters.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.widget.Toast;

import com.example.calero.counters.app.UI.Tabs.TabListener;
import com.example.calero.counters.app.UI.ViewPagers.ViewPagerAdapter;

public class MainActivity extends ActionBarActivity { // FragmentActivity{

    private static Context context;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
