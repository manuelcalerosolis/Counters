package com.example.calero.counters.app.Utils;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Activities.MainActivity;

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

    public static CharSequence getFormattedMonthDay(String timeStampText) throws ParseException {
        Date date = getDateFromString(timeStampText);
        return android.text.format.DateFormat.format("EEEE dd MMMM", date);
    }

    public static int getMinutesDifference(String timeStampTextStart, String timeStampTextEnd) throws ParseException {
        int minutesDifference;
        Date dateStart = getDateFromString(timeStampTextStart);
        Date dateStop = getDateFromString(timeStampTextEnd);
        minutesDifference = (int) (TimeUnit.MILLISECONDS.toMinutes(dateStart.getTime())) - (int) (TimeUnit.MILLISECONDS.toMinutes(dateStop.getTime()));
        return ( minutesDifference );
    }

    public static long getSecondsDifference(String timeStampTextStart, String timeStampTextEnd) throws ParseException {
        long secondsDifference;
        Date dateStart = getDateFromString(timeStampTextStart);
        Date dateStop = getDateFromString(timeStampTextEnd);
        secondsDifference = (TimeUnit.MILLISECONDS.toSeconds(dateStart.getTime())) - (TimeUnit.MILLISECONDS.toSeconds(dateStop.getTime()));
        secondsDifference = secondsDifference % 60;
        return ( secondsDifference );
    }

    public static CharSequence getMinutesAndSecondsDifference(String timeStampTextStart, String timeStampTextEnd) throws ParseException {
        long seconds = 0;
        CharSequence minutesAndSeconds;
        seconds = getSecondsDifference(timeStampTextStart, timeStampTextEnd );
        if (seconds == 0)
            minutesAndSeconds = getMinutesDifference(timeStampTextStart, timeStampTextEnd) + MainActivity.getAppContext().getString(R.string.minutes);
        else
            minutesAndSeconds = getMinutesDifference(timeStampTextStart, timeStampTextEnd) + MainActivity.getAppContext().getString(R.string.minutes) + " " +
                seconds + MainActivity.getAppContext().getString(R.string.seconds);
        return (minutesAndSeconds);
    }

    public static CharSequence getDateInLine(String timeStampTextStart, String timeStampTextEnd) throws ParseException {
        CharSequence dateString = "";
        Date dateStart = getDateFromString(timeStampTextStart);
        Date dateEnd = getDateFromString(timeStampTextEnd);
        dateString = android.text.format.DateFormat.format("dd/MM/yyyy hh:mm:ss", dateStart) + " to " +
            android.text.format.DateFormat.format("hh:mm:ss", dateEnd);
        return (dateString);
    }


    public static String getTimeStampFromString(String timeStampText) throws ParseException {
        Date date = new Date();
        date = getDateFromString(timeStampText);
        return date.toString();
    }



}
