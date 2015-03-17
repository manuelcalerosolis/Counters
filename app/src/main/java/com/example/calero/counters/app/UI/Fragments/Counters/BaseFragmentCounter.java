package com.example.calero.counters.app.UI.Fragments.Counters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Fragments.BaseFragment;

public abstract class BaseFragmentCounter extends BaseFragment {

    TextView textViewCounter;
    ImageButton imageButtonMinus;
    ImageButton imageButtonPlus;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        textViewCounter = (TextView) view.findViewById(R.id.list_item_counter_textview);
        imageButtonPlus = (ImageButton) view.findViewById(R.id.imageButtonPlus);
        imageButtonMinus = (ImageButton) view.findViewById(R.id.imageButtonMinus);

        return view;
    }


}
