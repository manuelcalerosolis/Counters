package com.example.calero.counters.app.UI.Presenters;

import android.os.Bundle;

import com.example.calero.counters.app.UI.Activities.MainActivity;
import com.example.calero.counters.app.Models.ModelCounter;
import com.example.calero.counters.app.R;
import com.example.calero.counters.app.Utils.UtilToast;

public abstract class PrensenterBasePresenterCounter extends BasePresenter {

    private boolean booleanInit = false;

    private final ModelCounter modelCounter = new ModelCounter(getCounterType());

    public ModelCounter getModelCounter() {
        return modelCounter;
    }

    public boolean isBooleanInit() {
        return booleanInit;
    }

    public void setBooleanInit(boolean booleanInit) {
        this.booleanInit = booleanInit;
    }

    public void toggleBooleanInit() {
        setBooleanInit(!isBooleanInit());
    }

    public void toastTimeStampStart(){
        UtilToast.showLongCenterMessage(modelCounter.getTimeStampStartMessage());
    }

    public void toastTimeStampSave(){
        UtilToast.showLongCenterMessage(modelCounter.getTimeStampStopMessage());
    }

    public void toastTimeStampCancel(){
        UtilToast.showLongCenterMessage(MainActivity.getAppContext().getString(R.string.counter_cancel));
    }

    public void plusCounter(){
        modelCounter.plusLongCounter();
    }

    public long getCounter(){
        return modelCounter.getLongCounter();
    }

    public void minusCounter(){
        modelCounter.minusLongCounter();
    }

    public void resetCounter(){
        modelCounter.resetLongCounter();
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanInit", isBooleanInit());
        bundle.putLong("longCounter", modelCounter.getLongCounter());
        bundle.putSerializable("timeStampStart", modelCounter.getTimeStampStart());
        return (bundle);
    }

    public void setBundle(Bundle bundle ){
        setBooleanInit(bundle.getBoolean("booleanInit", false));
        modelCounter.setLongCounter(bundle.getLong("longCounter", 0));
        modelCounter.setTimeStampStartSerializable(bundle.getSerializable("timeStampStart"));
    }

    public abstract int getCounterType();

}
