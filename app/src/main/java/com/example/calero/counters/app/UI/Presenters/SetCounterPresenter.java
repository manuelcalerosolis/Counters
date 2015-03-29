package com.example.calero.counters.app.UI.Presenters;

import com.example.calero.counters.app.UI.Fragments.Counters.OnlyCounterFragment;
import com.example.calero.counters.app.Utils.NumberDialogFragment;

import java.util.Date;

public class SetCounterPresenter extends PrensenterBasePresenterCounter {

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

    public void startCounter(){
        setBooleanInit(true);
        getModelCounter().setTimeStampStart(new Date());
        toastTimeStampStart();
    }

    public void onStart(){
        super.onStart();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void setTotalCounter(){
        int intCounterSet = view.getNumberDialogFragment();
        if (intCounterSet != 0);
            view.setTextTotal(intCounterSet);
    }

    @Override
    public int getCounterType() {
        return 1;
    }

    public interface View {
        void refreshTextViewCounter(String stringCounterFormat);

        int getNumberDialogFragment();

        void setTextTotal(int intCounterSet);
    }

}
