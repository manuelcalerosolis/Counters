package com.example.calero.counters.app.UI.Presenters;

import com.example.calero.counters.app.UI.Activities.MainActivity;

import java.util.Date;

public class OnlyCounterPresenter extends PrensenterBasePresenterCounter {

    private View view;

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void onClickButtonPlus(){
        if (!isBooleanInit())
            startCounter();
        plusCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickButtonMinus(){
        if (isBooleanInit())
            minusCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickButtonCancel(){
        if (isBooleanInit())
            onCancelCounter();
        resetCounter();
    }

    public void onClickButtonSave(){
        if (isBooleanInit())
            onSaveCounter();
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

    public void onSaveCounter(){
        setBooleanInit(false);
        getModelCounter().insertDatabase();
        view.hideSaveAndCancel();
        toastTimeStampSave();
    }

    public void onCancelCounter(){
        setBooleanInit(false);
        view.hideSaveAndCancel();
        toastTimeStampCancel();
    }

    public void onStart(){
        super.onStart();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
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
