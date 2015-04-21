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
import com.example.calero.counters.app.UI.Presenters.OnlyCounterPresenter;

public class OnlyCounterFragment extends BaseFragmentCounter implements OnlyCounterPresenter.View {

    LinearLayout linearLayoutStop;
    TextView textViewSaveCounter;
    ImageButton imageButtonCancel;

    private static final String LOG_TAG = OnlyCounterFragment.class.getSimpleName();

    static OnlyCounterPresenter onlyCounterPresenter = new OnlyCounterPresenter();

    public static OnlyCounterFragment newInstance() {
        OnlyCounterFragment fragmentOnlyCounter = new OnlyCounterFragment();
        return fragmentOnlyCounter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart(){
        Log.d(LOG_TAG, "onStart");
        super.onStart();
        onlyCounterPresenter.onStart();
    }

    @Override
    public void onResume(){
        Log.d(LOG_TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause(){
        Log.d(LOG_TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onStop(){
        Log.d(LOG_TAG, "onStop");
        super.onStop();
        onlyCounterPresenter.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        imageButtonCancel = (ImageButton) view.findViewById(R.id.imageButtonStop);
        textViewSaveCounter = (TextView) view.findViewById(R.id.textViewSaveCounter);
        linearLayoutStop = (LinearLayout) view.findViewById(R.id.linearLayoutStop);
        
        if(onlyCounterPresenter.isBooleanInit())
            showSaveAndCancel();

        imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onlyCounterPresenter.onClickButtonPlus(getActivity().getApplication());
            }
        });

        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onlyCounterPresenter.onClickButtonMinus(getActivity().getApplication());
            }
        });

        imageButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onlyCounterPresenter.onClickButtonCancel();
            }
        });

        textViewSaveCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            onlyCounterPresenter.onClickButtonSave();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState = onlyCounterPresenter.getSavedIntance();
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            onlyCounterPresenter.setSavedInstance(savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onlyCounterPresenter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_only_counter;
    }

    @Override
    public void refreshTextViewCounter( String stringCounterFormat ){
        textViewCounter.setText(stringCounterFormat);
    }

    @Override
    public void showSaveAndCancel(){
        linearLayoutStop.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSaveAndCancel(){
        linearLayoutStop.setVisibility(View.INVISIBLE);
    }

}