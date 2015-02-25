package com.example.calero.counters.app.UI.Presenters;

import com.example.calero.counters.app.UI.Fragments.Counters.TimeCounterFragment;

public class TimeCounterPresenter extends PrensenterBasePresenterCounter {

    TimeCounterFragment fragmentTimeCounter;

    public void setFragment(TimeCounterFragment fragment) {
        if (fragment == null) {
            throw new IllegalArgumentException("You can't set a null fragment");
        }
        this.fragmentTimeCounter = fragment;
    }

    public int getFragmentType(){
        return 1;
    }

    @Override
    public int getCounterType() {
        return 2;
    }
}
