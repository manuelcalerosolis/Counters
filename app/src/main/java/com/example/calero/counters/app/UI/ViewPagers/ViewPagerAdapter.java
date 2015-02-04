package com.example.calero.counters.app.UI.ViewPagers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.calero.counters.app.UI.Fragments.Counters.FragmentOnlyCounter;
import com.example.calero.counters.app.UI.Fragments.FragmentData;
import com.example.calero.counters.app.UI.Fragments.Counters.FragmentSetCounter;
import com.example.calero.counters.app.UI.Fragments.Counters.FragmentTimeCounter;

import static android.app.PendingIntent.getActivity;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;

    final FragmentOnlyCounter fragmentOnlyCounter = new FragmentOnlyCounter().newInstance();
    final FragmentTimeCounter fragmentTimeCounter = new FragmentTimeCounter().newInstance();
    final FragmentSetCounter fragmentSetCounter   = new FragmentSetCounter().newInstance();
    final FragmentData fragmentdata               = new FragmentData();

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("textViewCounter", Integer.toString( position) );
        switch (position) {
            case 0:
                return fragmentOnlyCounter;

            case 1:
                return fragmentTimeCounter;

            case 2:
                return fragmentSetCounter;

            case 3:
                return fragmentdata;
        }
        return null;
    }

}