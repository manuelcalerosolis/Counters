package com.example.calero.counters.app.UI.Presenters;

import android.app.Application;
import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Activities.MainActivity;
import com.example.calero.counters.app.Utils.UtilToast;

import java.util.Date;

// TODO No se imprime bien el tiempo cdo se voltea el dispositivo

public class TimeCounterPresenter extends PrensenterBasePresenterCounter {

    private View view;

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void onClickButtonPlus(final Application application){
        if (isBooleanInit()){
            plusCounter(application);
            view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
        }
        else
            UtilToast.showLongCenterMessage(R.string.start_time_please);
    }

    public void onClickButtonMinus(final Application application){
        if (isBooleanInit())
            minusCounter(application);
            view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public int getFragmentType(){
        return 1;
    }

    public void onClickButtonPlay() {
        setBooleanInit(true);

        view.startCounter();

        getModelCounter().setTimeStampStart(new Date());
        getModelCounter().setLocationLatitude(MainActivity.getLocationLatitude());
        getModelCounter().setLocationLongitude(MainActivity.getLocationLongitude());

        toastTimeStampStart();
    }

    public void onClickButtonPause() {
        toggleBooleanInit();
        if (isBooleanInit())
            view.startCounter();
        else
            view.pauseCounter();
    }

    public void onClickButtonStop() {
        setBooleanInit(false);

        resetCounter();

        view.stopCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());

        toastTimeStampCancel();
    }

    public void onStart(){
        super.onStart();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void saveCounter() {
        if (isBooleanInit()) {
            getModelCounter().setTimeStampStop(new Date());
            getModelCounter().insertDatabase();
            setBooleanInit(false);
        }

        resetCounter();

        view.stopCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());

        toastTimeStampSave();
    }

    @Override
    public int getCounterType() {
        return 2;
    }

    public interface View {

        void startCounter();

        void pauseCounter();

        void stopCounter();

        void refreshTextViewCounter(String stringCounterFormat);
    }

}
