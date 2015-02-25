package com.example.calero.counters.app.UI.Fragments.Counters;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calero.counters.app.MainActivity;
import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Presenters.TimeCounterPresenter;

import java.util.concurrent.TimeUnit;

public class TimeCounterFragment extends BaseFragmentCounter {

    TextView textViewTimer;

    ImageButton imageButtonPlusTime;
    ImageButton imageButtonMinusTime;
    ImageButton imageButtonPlayTime;
    ImageButton imageButtonStopTime;

    LinearLayout linearLayoutStop;

    CounterTimer counterTimer;

    long longHours      = 0;
    long longMinutes    = 1;

    static TimeCounterPresenter prensenterTimeCounter = new TimeCounterPresenter();

    public static TimeCounterFragment newInstance() {
        TimeCounterFragment fragmentTimeCounter = new TimeCounterFragment();
        fragmentTimeCounter.setArguments(prensenterTimeCounter.getBundle());
        return fragmentTimeCounter;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null)
            prensenterTimeCounter.setBundle(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time_counter, container, false);

        textViewTimer = (TextView) view.findViewById(R.id.textViewTimer);
        refreshTextViewTimer();

        textViewCounter = (TextView) view.findViewById(R.id.textViewCounter);

        linearLayoutStop = (LinearLayout) view.findViewById(R.id.linearLayoutStop);

        imageButtonPlusTime = (ImageButton) view.findViewById(R.id.imageButtonPlusTime);
        imageButtonMinusTime = (ImageButton) view.findViewById(R.id.imageButtonMinusTime);
        imageButtonPlayTime = (ImageButton) view.findViewById(R.id.imageButtonPlayTime);
        imageButtonStopTime = (ImageButton) view.findViewById(R.id.imageButtonPauseTime);

        imageButtonPlus = (ImageButton) view.findViewById(R.id.imageButtonPlus);
        imageButtonMinus = (ImageButton) view.findViewById(R.id.imageButtonMinus);

        imageButtonPlayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prensenterTimeCounter.toggleBooleanInit();
                if (prensenterTimeCounter.isBooleanInit())
                    startCounter();
                else
                    pauseCounter();
            }
        });

        imageButtonStopTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCounter();
                finalizeCounterTimer();
                refreshTextViewTimer();
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
                if(prensenterTimeCounter.isBooleanInit()) {
                    prensenterTimeCounter.plusCounter();
                    //refreshTextViewCounter();
                }
                else
                    MainActivity.showAppToast(R.string.start_time_please);
            }
        });

        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prensenterTimeCounter.isBooleanInit()){
                    prensenterTimeCounter.minusCounter();
                }
                else
                    MainActivity.showAppToast(R.string.start_time_please);
            }
        });

        return view;
    }

    @Override
    protected int getFragmentLayout() {
        return 0;
    }

    public void refreshTextViewCounter( String stringCounterFormat ){
        textViewCounter.setText(stringCounterFormat);
    }

    public void showSaveAndCancel(){
        linearLayoutStop.setVisibility(View.VISIBLE);
    }

    public void hideSaveAndCancel(){
        linearLayoutStop.setVisibility(View.INVISIBLE);
    }

    private void startCounter(){
        if (counterTimer==null)
            buildCounterTimer();
        else
            leftCounterTimer();
        counterTimer.start();

        imageButtonPlayTime.setImageResource(R.drawable.pause_time_icon);
        imageButtonPlusTime.setVisibility(View.INVISIBLE);
        imageButtonMinusTime.setVisibility(View.INVISIBLE);
    }

    private void pauseCounter(){
        if (counterTimer!=null)
            counterTimer.cancel();
        imageButtonPlayTime.setImageResource(R.drawable.play_time_icon);
    }

    private void stopCounter(){
        if (counterTimer!=null)
            counterTimer.cancel();
        MainActivity.showAppToast(R.string.stop_time);
        imageButtonPlayTime.setImageResource(R.drawable.play_time_icon);
        imageButtonPlusTime.setVisibility(View.VISIBLE);
        imageButtonMinusTime.setVisibility(View.VISIBLE);
    }

    private void refreshTextViewTimer(){
        textViewTimer.setText(String.format("%02d:%02d:%02d",longHours,longMinutes,0));
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

    protected void finalizeCounterTimer(){
        prensenterTimeCounter.setBooleanInit(false);
        if(counterTimer != null){
            counterTimer.cancel();
            counterTimer = null;
        }
    }

    protected void finishCounterTimer(){
        finalizeCounterTimer();
//        prensenterTimeCounter.saveCounter();
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

        public void toggle() {
            if (prensenterTimeCounter.isBooleanInit())
                this.cancel();
            else
                this.start();
            prensenterTimeCounter.toggleBooleanInit();
        }

    }

}