package com.example.calero.counters.app.UI.Presenters;

import com.example.calero.counters.app.UI.Fragments.Counters.TimeCounterFragment;

public class TimeCounterPresenter extends PrensenterBasePresenterCounter {

    private View view;

    public void setView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void onClickButtonPlus(){
        if (!isBooleanInit())
//            startCounter();
        plusCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void onClickButtonMinus(){
        if (isBooleanInit())
            minusCounter();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public int getFragmentType(){
        return 1;
    }

    @Override
    public int getCounterType() {
        return 2;
    }

    public interface View {

        void refreshTextViewCounter(String stringCounterFormat);

    }

}
