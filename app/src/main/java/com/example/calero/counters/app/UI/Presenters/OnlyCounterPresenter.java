package com.example.calero.counters.app.UI.Presenters;

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
            cancelCounter();
    }

    public void onClickButtonSave(){
        if (isBooleanInit())
            saveCounter();
    }

    public void startCounter(){
        setBooleanInit(true);
        getModelCounter().setTimeStampStart(new Date());
        toastTimeStampStart();
        view.showSaveAndCancel();
    }

    public void saveCounter(){
        setBooleanInit(false);
        getModelCounter().insertDatabase();
        view.hideSaveAndCancel();
        toastTimeStampSave();
    }

    public void cancelCounter(){
        setBooleanInit(false);
        view.hideSaveAndCancel();
        toastTimeStampCancel();
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
