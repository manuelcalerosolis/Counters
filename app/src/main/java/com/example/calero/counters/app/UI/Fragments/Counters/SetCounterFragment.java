package com.example.calero.counters.app.UI.Fragments.Counters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Presenters.SetCounterPresenter;

public class SetCounterFragment extends BaseFragmentCounter {

    ImageButton imageButtonStop;

    EditText editTextTotal;

    static SetCounterPresenter prensenterSetCounter = new SetCounterPresenter();

    public static SetCounterFragment newInstance() {
        SetCounterFragment fragmentSetCounter = new SetCounterFragment();
        fragmentSetCounter.setArguments(prensenterSetCounter.getBundle());
        return fragmentSetCounter;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            prensenterSetCounter.setBundle(bundle);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_counter, container, false);

        editTextTotal           = (EditText) view.findViewById(R.id.editTextTotal);

        textViewCounter         = (TextView) view.findViewById(R.id.textViewCounter);

        imageButtonStop         = (ImageButton) view.findViewById(R.id.imageButtonStop);

        imageButtonPlus         = (ImageButton) view.findViewById(R.id.imageButtonPlus);
        imageButtonMinus        = (ImageButton) view.findViewById(R.id.imageButtonMinus);

        refreshTextViewCounter();

        imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (prensenterSetCounter.getLongCounter() < Long.parseLong( editTextTotal.getText().toString() ))
//                    prensenterSetCounter.plusCounter();
                refreshTextViewCounter();
            }
        });

        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prensenterSetCounter.minusCounter();
                refreshTextViewCounter();
            }
        });

        return view;
    }

    @Override
    protected int getFragmentLayout() {
        return 2;
    }

    private void refreshTextViewCounter(){
//        textViewCounter.setText(prensenterSetCounter.getLongCounterFormat());
    }

}