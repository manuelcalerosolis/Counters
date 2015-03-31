package com.example.calero.counters.app.UI.Presenters;

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

    public void onClickButtonPlus(){
        if (!isBooleanInit())
            startCounter();

        if (isPlusCounter())
            plusCounter();

        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    private boolean isPlusCounter() {
        return (getCounter() < view.getTextTotal());
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
        view.setButtonsVisible();
    }

    public void onStart(){
        super.onStart();
        view.refreshTextViewCounter(getModelCounter().getStringCounterFormat());
    }

    public void setTotalCounter(){
        view.getNumberDialogFragment();
    }

    public void onCancelCounter(){
        setBooleanInit(false);
        view.setButtonsInvisible();
        toastTimeStampCancel();
        resetCounter();
    }

    public void onSaveCounter(){
        setBooleanInit(false);
        getModelCounter().insertDatabase();
        view.setButtonsInvisible();
        toastTimeStampSave();
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
