package com.example.calero.counters.app.UI.Presenters;

import com.example.calero.counters.app.UI.Fragments.Counters.FragmentOnlyCounter;

public class PresenterSetCounter extends PrensenterBaseCounter{

    FragmentOnlyCounter fragmentOnlyCounter;

    public void setFragment(FragmentOnlyCounter fragment) {
        if (fragment == null) {
            throw new IllegalArgumentException("You can't set a null fragment");
        }
        this.fragmentOnlyCounter = fragment;
    }

    public void showSaveAndCancel(){
        fragmentOnlyCounter.showSaveAndCancel();
    }


    public int getFragmentType(){
        return 1;
    }

    @Override
    public int getCounterType() {
        return 1;
    }
}
