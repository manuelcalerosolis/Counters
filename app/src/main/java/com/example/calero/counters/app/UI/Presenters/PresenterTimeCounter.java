package com.example.calero.counters.app.UI.Presenters;

import com.example.calero.counters.app.UI.Fragments.Counters.FragmentTimeCounter;

public class PresenterTimeCounter extends PrensenterBaseCounter{

    FragmentTimeCounter fragmentTimeCounter;

    public void setFragment(FragmentTimeCounter fragment) {
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
