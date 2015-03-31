package com.example.calero.counters.app.Utils;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.example.calero.counters.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by calero on 24/03/2015.
 */
public class UtilDate {

    public static final String DATE_FORMAT = "ddMMyyyy";

    protected static Date getDateFromString(String timeStamp) throws ParseException {
        SimpleDateFormat simpleDateFormatStart = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return (simpleDateFormatStart.parse(timeStamp));
    }

    public static String getFormattedMonthDay(String timeStampText) throws ParseException {
        Date date = getDateFromString(timeStampText);
        return  android.text.format.DateFormat.format("EEEE", date) + " " +
                android.text.format.DateFormat.format("dd", date) + " " +
                android.text.format.DateFormat.format("MMMM", date);
    }

    public static int getMinutesAndSecondsDifference(String timeStampTextStart, String timeStampTextEnd) throws ParseException {
        Date dateStart = getDateFromString(timeStampTextStart);
        Date dateStop = getDateFromString(timeStampTextEnd);
        return ( (int) (TimeUnit.MILLISECONDS.toMinutes(dateStart.getTime())) - (int) (TimeUnit.MILLISECONDS.toMinutes(dateStop.getTime())) );
    }

    public static String getDateInLine(String timeStampTextStart, String timeStampTextEnd) throws ParseException {
        String dateString = "";
        Date dateStart = getDateFromString(timeStampTextStart);
        Date dateEnd = getDateFromString(timeStampTextEnd);
        dateString =    android.text.format.DateFormat.format("dd", dateStart) + "/" +
                        android.text.format.DateFormat.format("mm", dateStart) + "/" +
                        android.text.format.DateFormat.format("yyyy", dateStart) + " " +
                        android.text.format.DateFormat.format("hh:mm:ss", dateStart) + " to " +
                        android.text.format.DateFormat.format("hh:mm:ss", dateEnd);
        return ( dateString );
    }


    public static String getTimeStampFromString(String timeStampText) throws ParseException {
        Date date = new Date();
        date = getDateFromString(timeStampText);
        return date.toString();
    }



}
