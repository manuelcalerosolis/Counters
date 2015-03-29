package com.example.calero.counters.app.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Activities.MainActivity;

/**
 * Created by calero on 27/03/2015.
 */
public class NumberDialogFragment extends DialogFragment {

    EditText editTextTotal;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.getAppContext());
        View numberDialog = layoutInflater.inflate(R.layout.number_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(numberDialog);

        final EditText editTextTotal = (EditText) numberDialog.findViewById(R.id.numberInput);

        builder.setMessage(R.string.dialog_text)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        editTextTotal.setText("0");
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setEditTextTotal(int total){
        editTextTotal.setText(String.valueOf(total));
    }

    public int getEditTextTotal() {
        return Integer.valueOf(editTextTotal.getText().toString());
    }

}
