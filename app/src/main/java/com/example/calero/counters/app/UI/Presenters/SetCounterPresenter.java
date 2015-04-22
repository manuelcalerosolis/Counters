package com.example.calero.counters.app.UI.Presenters;

import android.app.Application;

import com.example.calero.counters.app.UI.Activities.MainActivity;

import java.util.Date;

// TODO vuelta a origen tras finalizar el conteo

public class SetCounterPresenter extends PrensenterBasePresenterCounter {

    private View view;

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void onClickButtonPlus(final Application application){
        if (!isBooleanInit())
            startCounter();

        if (isPlusCounter())
            plusCounter(application);

        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickButtonMinus(final Application application){
        if (isBooleanInit())
            minusCounter(application);
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    private boolean isPlusCounter() {
        return (getCounter() < view.getTextTotal());
    }

    public void startCounter(){
        setBooleanInit(true);
        getModelCounter().setTimeStampStart(new Date());
        getModelCounter().setLocationLatitude(MainActivity.getLocationLatitude());
        getModelCounter().setLocationLongitude(MainActivity.getLocationLongitude());
        toastTimeStampStart();
        view.setButtonsVisible();
    }

    public void onStart(){
        super.onStart();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickTextTotal(){
        view.getNumberDialogFragment();
    }

    public void onClickButtonCounter(){
        setBooleanInit(false);
        view.setButtonsInvisible();
        toastTimeStampCancel();
        resetCounter();
    }

    public void onClickButtonSave(){
        if (isBooleanInit())
            saveCounter();
        else
            onClickTextTotal();
    }

    public void saveCounter(){
        setBooleanInit(false);
        getModelCounter().setTimeStampStop(new Date());
        getModelCounter().insertDatabase();
        toastTimeStampSave();
        view.setButtonsInvisible();
        resetCounter();
    }

    @Override
    public int getCounterType() {
        return 3;
    }

    public interface View {
        void refreshTextViewCounter(String stringCounterFormat);

        void getNumberDialogFragment();

        int getTextTotal();

        void setButtonsVisible();

        void setButtonsInvisible();
    }

}
