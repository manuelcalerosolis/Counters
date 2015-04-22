package com.example.calero.counters.app.UI.Presenters;

import android.app.Application;

import com.example.calero.counters.app.UI.Activities.MainActivity;

import java.util.Date;

public class OnlyCounterPresenter extends PrensenterBasePresenterCounter {

    private View view;

    public void setView(View view){
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void onClickButtonPlus(final Application application){
        if (!isBooleanInit())
            startCounter();
        plusCounter(application);
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickButtonMinus(final Application application){
        if (isBooleanInit())
            minusCounter(application);
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickButtonCancel(){
        if (isBooleanInit())
            onCancelCounter();
        resetCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickButtonSave(){
        if (isBooleanInit())
            saveCounter();
        resetCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void startCounter(){
        setBooleanInit(true);
        getModelCounter().setTimeStampStart(new Date());
        getModelCounter().setLocationLatitude(MainActivity.getLocationLatitude());
        getModelCounter().setLocationLongitude(MainActivity.getLocationLongitude());
        toastTimeStampStart();
        view.showSaveAndCancel();
    }

    public void saveCounter(){
        setBooleanInit(false);
        getModelCounter().setTimeStampStop(new Date());
        getModelCounter().insertDatabase();
        toastTimeStampSave();
        view.hideSaveAndCancel();
    }

    public void onCancelCounter(){
        setBooleanInit(false);
        toastTimeStampCancel();
        view.hideSaveAndCancel();
    }

    public void onStart(){
        super.onStart();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onStop(){
        super.onStop();
    }

    @Override public int getCounterType() {
        return 1;
    }

    public interface View {

        void refreshTextViewCounter(String stringCounterFormat);

        void showSaveAndCancel();

        void hideSaveAndCancel();

    }

}
