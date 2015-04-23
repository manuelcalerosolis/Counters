package com.example.calero.counters.app.UI.Fragments.Counters;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Presenters.TimeCounterPresenter;

import java.util.concurrent.TimeUnit;

//TODO volver atras el timer cuando termine tras la grabacion.

public class TimeCounterFragment extends BaseFragmentCounter implements TimeCounterPresenter.View{

    TextView textViewTimer;

    ImageButton imageButtonPlusTime;
    ImageButton imageButtonMinusTime;
    ImageButton imageButtonPlayTime;
    ImageButton imageButtonStopTime;

    CounterTimer counterTimer;

    long longHours = 0;
    long longMinutes = 1;

    static TimeCounterPresenter timeCounterPresenter = new TimeCounterPresenter();

    private static final String LOG_TAG = TimeCounterFragment.class.getSimpleName();

    public static TimeCounterFragment newInstance() {
        TimeCounterFragment fragmentTimeCounter = new TimeCounterFragment();
        return fragmentTimeCounter;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        textViewTimer = (TextView) view.findViewById(R.id.textViewTimer);
        refreshTextViewTimer();
        imageButtonPlusTime = (ImageButton) view.findViewById(R.id.imageButtonPlusTime);
        imageButtonMinusTime = (ImageButton) view.findViewById(R.id.imageButtonMinusTime);
        imageButtonPlayTime = (ImageButton) view.findViewById(R.id.imageButtonPlayTime);
        imageButtonStopTime = (ImageButton) view.findViewById(R.id.imageButtonPauseTime);

        imageButtonPlayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counterTimer==null)
                    timeCounterPresenter.onClickButtonPlay();
                else
                    timeCounterPresenter.onClickButtonPause();
            }
        });

        imageButtonStopTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeCounterPresenter.onClickButtonStop();
            }
        });

        imageButtonPlusTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (longMinutes < 59) {
                    longMinutes++;
                } else {
                    longHours++;
                    longMinutes = 0;
                }
                refreshTextViewTimer();
            }
        });

        imageButtonMinusTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(longMinutes>1){
                    longMinutes--;
                }else{
                    if(longHours>0){
                        longHours--;
                        longMinutes = 59;
                    }
                }
                refreshTextViewTimer();
            }
        });

        imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeCounterPresenter.onClickButtonPlus(getActivity().getApplication());
            }
        });

        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeCounterPresenter.onClickButtonMinus(getActivity().getApplication());
            }
        });

        return view;
    }

    protected void finishCounterTimer(){
        timeCounterPresenter.saveCounter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timeCounterPresenter.setView(this);
    }

    @Override
    public void onStart(){
        Log.d(LOG_TAG, "onStart");
        Log.d(LOG_TAG, "state booleanInit onStart" + timeCounterPresenter.isBooleanInit());

        super.onStart();
        timeCounterPresenter.onStart();

        if (timeCounterPresenter.isBooleanInit())
            hidePlusMinusButtons();
        else
            showPlusMinusButtons();
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
    public void onStop() {
        Log.d(LOG_TAG, "onStop");
        Log.d(LOG_TAG, "state booleanInit onStop" + timeCounterPresenter.isBooleanInit());
        super.onStop();
        timeCounterPresenter.onStop();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_time_counter;
    }

    @Override
    public void refreshTextViewCounter( String stringCounterFormat ){
        textViewCounter.setText(stringCounterFormat);
    }

    public void startCounter(){
        if (counterTimer==null)
            buildCounterTimer();
        else
            leftCounterTimer();
        counterTimer.start();

        hidePlusMinusButtons();
    }

    private void hidePlusMinusButtons(){
        imageButtonPlayTime.setImageResource(R.drawable.pause_time_icon);
        imageButtonPlusTime.setVisibility(View.INVISIBLE);
        imageButtonMinusTime.setVisibility(View.INVISIBLE);
    }

    public void pauseCounter(){
        if (counterTimer!=null)
            counterTimer.cancel();
        imageButtonPlayTime.setImageResource(R.drawable.play_time_icon);
    }

    public void stopCounter(){
        if (counterTimer!=null)
            counterTimer.cancel();
        counterTimer = null;

        showPlusMinusButtons();

        refreshTextViewTimer();
    }

    private void showPlusMinusButtons(){
        imageButtonPlayTime.setImageResource(R.drawable.play_time_icon);
        imageButtonPlusTime.setVisibility(View.VISIBLE);
        imageButtonMinusTime.setVisibility(View.VISIBLE);
    }

    private void refreshTextViewTimer(){
        textViewTimer.setText(String.format("%02d:%02d:%02d", longHours, longMinutes, 0));
    }

    protected void buildCounterTimer(){
        long totalTime  = longHours * 60 * 60000;
        totalTime       += longMinutes * 60000;
        counterTimer    = new CounterTimer(totalTime, 1000);
    }

    protected void leftCounterTimer(){
        long millisLeft = counterTimer.longMillisLeft;
        counterTimer    = new CounterTimer(millisLeft, 1000);
    }

    /*
    Counter class--------------------------------------------------------------
    */

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterTimer extends CountDownTimer {

        protected long longMillisLeft;
        protected boolean booleanStart = false;

        public CounterTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long l) {
            longMillisLeft = l;

            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MICROSECONDS.toHours(longMillisLeft),
                    TimeUnit.MILLISECONDS.toMinutes(longMillisLeft) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(longMillisLeft)),
                    TimeUnit.MILLISECONDS.toSeconds(longMillisLeft) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(longMillisLeft)));

            System.out.println(hms);
            textViewTimer.setText(hms);
        }

        @Override
        public void onFinish() {
            finishCounterTimer();
        }
    }

}