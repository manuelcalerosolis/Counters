package com.example.calero.counters.app.UI.Fragments.Counters;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Activities.MainActivity;
import com.example.calero.counters.app.UI.Presenters.SetCounterPresenter;

//TODO Comprobar el counter set si toma los valores del dialogo

public class SetCounterFragment extends BaseFragmentCounter implements SetCounterPresenter.View {

    ImageButton imageButtonCancel;
    ImageButton imageButtonSave;

    TextView textViewSet;
    TextView textViewTotal;

    private static final String LOG_TAG = SetCounterFragment.class.getSimpleName();

    static SetCounterPresenter prensenterSetCounter = new SetCounterPresenter();

    public static SetCounterFragment newInstance() {
        SetCounterFragment fragmentSetCounter = new SetCounterFragment();
        return fragmentSetCounter;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        imageButtonCancel = (ImageButton) view.findViewById(R.id.imageButtonCancel);
        imageButtonSave = (ImageButton) view.findViewById(R.id.imageButtonSave);
        textViewTotal = (TextView) view.findViewById(R.id.textViewTotal);
        textViewSet = (TextView) view.findViewById(R.id.textViewSet);

        imageButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prensenterSetCounter.onClickButtonCounter();
            }
        });

        imageButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prensenterSetCounter.onClickButtonSave();
            }
        });

        textViewTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prensenterSetCounter.onClickTextTotal();
            }
        });

        textViewSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prensenterSetCounter.onClickTextTotal();
            }
        });

        imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prensenterSetCounter.onClickButtonPlus();
            }
        });

        imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prensenterSetCounter.onClickButtonMinus();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prensenterSetCounter.setView(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_set_counter;
    }

    @Override
    public void onStart(){

        super.onStart();
        prensenterSetCounter.onStart();

        if (prensenterSetCounter.isBooleanInit())
            setButtonsVisible();
        else
            setButtonsInvisible();

    }

    public void onStop(){
        super.onStop();
        prensenterSetCounter.onStop();
    }

    public int getTextTotal(){
        return Integer.valueOf(textViewSet.getText().toString());
    }

    public void setButtonsVisible(){
        imageButtonCancel.setVisibility(android.view.View.VISIBLE);
        imageButtonSave.setVisibility(android.view.View.VISIBLE);
    }

    public void setButtonsInvisible(){
        imageButtonCancel.setVisibility(android.view.View.INVISIBLE);
        imageButtonSave.setVisibility(android.view.View.VISIBLE);
    }

    public void getNumberDialogFragment(){
        NumberDialog numberDialog = new NumberDialog();
        numberDialog.setEditTextSet(textViewSet);
        numberDialog.show(getFragmentManager(), "");
    }

    @Override
    public void refreshTextViewCounter( String stringCounterFormat ){
        if (textViewCounter != null)
            textViewCounter.setText(stringCounterFormat);
    }

    public static class NumberDialog extends DialogFragment {

        TextView textViewSet;
        EditText editTextTotal;

        public void setEditTextSet(TextView textViewSet){
            this.textViewSet = textViewSet;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.getAppContext());
            View numberDialog = layoutInflater.inflate(R.layout.dialog_number, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_DARK);

            builder.setView(numberDialog);

            editTextTotal = (EditText) numberDialog.findViewById(R.id.numberInput);

            builder.setMessage(R.string.dialog_text)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        textViewSet.setText( editTextTotal.getText().toString() );
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
            return builder.create();
        }

    }

}