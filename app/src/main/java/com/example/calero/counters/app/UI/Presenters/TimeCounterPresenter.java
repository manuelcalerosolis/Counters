package com.example.calero.counters.app.UI.Presenters;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Activities.MainActivity;
import com.example.calero.counters.app.UI.Fragments.Counters.TimeCounterFragment;

// TODO No se imprime bien el tiempo cdo se voltea el dispositivo

public class TimeCounterPresenter extends PrensenterBasePresenterCounter {

    private View view;

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void onClickButtonPlus(){
        if (isBooleanInit()){
            plusCounter();
            view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
        }
        else
            MainActivity.showAppToast(R.string.start_time_please);

    }

    public void onClickButtonMinus(){
        if (isBooleanInit())
            minusCounter();
            view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public int getFragmentType(){
        return 1;
    }

    public void onStart(){
        super.onStart();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void saveCounter() {
        if (isBooleanInit()) {
            getModelCounter().insertDatabase();
        }
        resetCounter();
        toastTimeStampSave();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    @Override
    public int getCounterType() {
        return 2;
    }

    public interface View {

        void refreshTextViewCounter(String stringCounterFormat);

    }

}
