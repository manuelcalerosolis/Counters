package com.example.calero.counters.app.UI.Tabs;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

public class TabListener implements ActionBar.TabListener {

    ViewPager viewPager;
    ActionBar actionBar;

    public TabListener( ViewPager viewPager, ActionBar actionBar) {
        this.viewPager  = viewPager;
        this.actionBar  = actionBar;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        actionBar.setTitle(tab.getContentDescription());
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        // fragmentTransaction.remove(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }
}
