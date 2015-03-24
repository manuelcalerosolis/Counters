package com.example.calero.counters.app.Utils;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.example.calero.counters.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by calero on 24/03/2015.
 */
public class UtilDate {

    public static final String DATE_FORMAT = "ddMMyyyy";

    public static String getDayName(Context context, long dateInMillis) {
        // If the date is today, return the localized version of "Today" instead of the actual
        // day name.

        Time t = new Time();
        t.setToNow();
        int julianDay = Time.getJulianDay(dateInMillis, t.gmtoff);
        int currentJulianDay = Time.getJulianDay(System.currentTimeMillis(), t.gmtoff);
        if (julianDay == currentJulianDay) {
            return context.getString(R.string.today);
        } else if ( julianDay == currentJulianDay +1 ) {
            return context.getString(R.string.tomorrow);
        } else {
            Time time = new Time();
            time.setToNow();
            // Otherwise, the format is just the day of the week (e.g "Wednesday".
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            return dayFormat.format(dateInMillis);
        }
    }

    public static String getFormattedMonthDay(Date date ) {
        Time time = new Time();
        time.setToNow();
        SimpleDateFormat monthDayFormat = new SimpleDateFormat("MMMM dd hh:mm:ss");
        String monthDayString = monthDayFormat.format(dateInMillis);
        return monthDayString;
    }

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
