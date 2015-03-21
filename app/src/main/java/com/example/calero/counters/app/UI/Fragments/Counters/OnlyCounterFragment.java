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

    static final String STATE_COUNTER = "onlyCounter";

    static OnlyCounterPresenter presenterOnlyCounter = new OnlyCounterPresenter();

    public static OnlyCounterFragment newInstance() {
        OnlyCounterFragment fragmentOnlyCounter = new OnlyCounterFragment();
        fragmentOnlyCounter.setArguments(presenterOnlyCounter.getBundle());
        return fragmentOnlyCounter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            presenterOnlyCounter.setBundle(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        imageButtonCancel = (ImageButton) view.findViewById(R.id.imageButtonStop);
        textViewSaveCounter = (TextView) view.findViewById(R.id.textViewSaveCounter);
        linearLayoutStop = (LinearLayout) view.findViewById(R.id.linearLayoutStop);
        
        if(presenterOnlyCounter.isBooleanInit())
            showSaveAndCancel();

        imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterOnlyCounter.onClickButtonPlus();
            }
        });

        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterOnlyCounter.onClickButtonMinus();
            }
        });

        imageButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterOnlyCounter.onClickButtonCancel();
            }
        });

        textViewSaveCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterOnlyCounter.onClickButtonSave();
            }
        });

        setRetainInstance(true);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        presenterOnlyCounter.getBundle();
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            presenterOnlyCounter.setBundle(savedInstanceState);
        }
        refreshTextViewCounter(presenterOnlyCounter.getModelCounter().getStringCounterFormat());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenterOnlyCounter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_counter;
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