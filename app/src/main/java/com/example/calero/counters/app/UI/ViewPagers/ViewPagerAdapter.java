package com.example.calero.counters.app.UI.ViewPagers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.calero.counters.app.UI.Fragments.Counters.OnlyCounterFragment;
import com.example.calero.counters.app.UI.Fragments.DataFragment;
import com.example.calero.counters.app.UI.Fragments.Counters.SetCounterFragment;
import com.example.calero.counters.app.UI.Fragments.Counters.TimeCounterFragment;

import static android.app.PendingIntent.getActivity;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;

    final OnlyCounterFragment fragmentOnlyCounter = new OnlyCounterFragment().newInstance();
    final TimeCounterFragment fragmentTimeCounter = new TimeCounterFragment().newInstance();
    final SetCounterFragment fragmentSetCounter   = new SetCounterFragment().newInstance();
    final DataFragment fragmentdata               = new DataFragment();

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