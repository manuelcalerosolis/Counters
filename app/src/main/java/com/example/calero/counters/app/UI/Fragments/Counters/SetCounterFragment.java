package com.example.calero.counters.app.UI.Fragments.Counters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Presenters.SetCounterPresenter;
import com.example.calero.counters.app.Utils.NumberDialogFragment;

public class SetCounterFragment extends BaseFragmentCounter implements SetCounterPresenter.View {

    ImageButton imageButtonStop;

    LinearLayout linearLayoutSet;
    TextView textViewSet;

    private static final String LOG_TAG = SetCounterFragment.class.getSimpleName();

    static SetCounterPresenter prensenterSetCounter = new SetCounterPresenter();

    public static SetCounterFragment newInstance() {
        SetCounterFragment fragmentSetCounter = new SetCounterFragment();
        return fragmentSetCounter;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        linearLayoutSet = (LinearLayout) view.findViewById(R.id.linearLayoutSet);
        linearLayoutSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prensenterSetCounter.setTotalCounter();
            }
        });

        textViewSet = (TextView) view.findViewById(R.id.textViewSet);

        imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            prensenterSetCounter.onClickButtonPlus();
            }
        });

        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            prensenterSetCounter.onClickButtonMinus();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prensenterSetCounter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_set_counter;
    }

    @Override
    public void onStart(){
        Log.d(LOG_TAG, "onStart");
        super.onStart();
        prensenterSetCounter.onStart();
    }

    public void onStop(){
        Log.d(LOG_TAG, "onStop");
        super.onStop();
        prensenterSetCounter.onStop();
    }

    public void setTextTotal( int total ){
        textViewSet.setText(String.valueOf(total));
    }

    public int getNumberDialogFragment(){
        NumberDialogFragment numberDialogFragment = new NumberDialogFragment();
        numberDialogFragment.show(getFragmentManager(), "");
        return numberDialogFragment.getEditTextTotal();
    }

    @Override
    public void refreshTextViewCounter( String stringCounterFormat ){
        if (textViewCounter != null)
            textViewCounter.setText(stringCounterFormat);
    }

}