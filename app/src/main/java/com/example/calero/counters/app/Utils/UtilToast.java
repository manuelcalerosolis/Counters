package com.example.calero.counters.app.Utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.example.calero.counters.app.UI.Activities.MainActivity;

public class UtilToast {

    public static void showError(final String message, final Context context) {
        getToast(message, context).show();
    }

    public static void showLongCenterMessage(String message) {
        getToastLongCenter(message).show();
    }

    public static void showShortMessage(String message, Context context) {
        getToast(message, Toast.LENGTH_SHORT).show();
    }

    private static Toast getToast(String message, Context context) {
        return getToast(message, Toast.LENGTH_LONG);
    }

    private static Toast getToast(String message, int length) {
        return Toast.makeText(MainActivity.getAppContext(), message, length);
    }

    private static Toast getToastLong(String message) {
        return getToast(message, Toast.LENGTH_LONG);
    }

    private static Toast getToastLongCenter(String message) {
        Toast toast = getToastLong(message);
        toast.setGravity(Gravity.CENTER, 0, 0);
        return toast;
    }

}
