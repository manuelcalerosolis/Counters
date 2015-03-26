package com.example.calero.counters.app.Utils;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.example.calero.counters.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by calero on 24/03/2015.
 */
public class UtilDate {

    public static String getFormattedMonthDay(String timeStampText) {

        Date date;
        String monthDayString = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            date = simpleDateFormat.parse(timeStampText);
            monthDayString = (String) android.text.format.DateFormat.format("EEEE", date);
            monthDayString += " ";
            monthDayString += (String) android.text.format.DateFormat.format("dd", date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthDayString;
    }

    public static final String DATE_FORMAT = "ddMMyyyy";

    public static Date getTimeStampFromString(String timeStampText) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            date = simpleDateFormat.parse(timeStampText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
