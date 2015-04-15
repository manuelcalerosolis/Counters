package com.example.calero.counters.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.database.Cursor;
import android.util.Log;

public class ModelData {

    private long longCounter;
    private int intType;
    private final Integer[] drawableType = {
        R.drawable.fragment_counter_only,
        R.drawable.fragment_counter_time,
        R.drawable.fragment_counter_set};
    private long duration;
    private Date timeStampInit;
    private Date TimeStampEnd;
    private String rowDetails;

    public ModelData(long counter) {
        setIntType(1);
        setCounter(10);
        setTimeStampInit(new Date());
        setTimeStampEnd(new Date());
        setRowDetails("details");
        setDuration(5);
    }

    public ModelData(Cursor cursorCounter) {
        if (cursorCounter.getColumnIndex("intType") != -1)
           setIntType(cursorCounter.getInt(cursorCounter.getColumnIndex("intType")));

        if(cursorCounter.getColumnIndex("longCounted") != -1)
            setCounter(cursorCounter.getLong(cursorCounter.getColumnIndex("longCounted")));

        if(cursorCounter.getColumnIndex("timeStampStart") != -1)
            setTimeStampInit(cursorCounter.getString(cursorCounter.getColumnIndex("timeStampStart")));

        setRowDetails(cursorCounter.getColumnName(2));
        setDuration(4);
        setTimeStampEnd(new Date());
    }

    public void setCounter(long longCounter) {
        this.longCounter = longCounter;
    }

    public long getCounter() {
        return this.longCounter;
    }

    public String getCounterString(){
        return Long.toString(longCounter);
    }

    public int getIntType() {
        return intType;
    }

    public void setIntType(int intType) {
        this.intType = intType;
    }

    public int getIntTypeIcon(){
        return drawableType[getIntType()-1];
    }

    public String getRowDetails() {
        return rowDetails;
    }

    public Date getTimeStampInit() {
        return timeStampInit;
    }

    public String getTimeStampInitString() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(getTimeStampInit());
    }

    public Date getTimeStampEnd() {
        return TimeStampEnd;
    }

    public String getTimeStampEndString() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(getTimeStampEnd());
    }

    public String getTimeStampEndMinutesString() {
        return new SimpleDateFormat("HH:mm").format(getTimeStampEnd());
    }

    public String getTimeStampString() {
        return getTimeStampInitString() + " " + getTimeStampEndMinutesString();
    }

    public long getDuration() {
        return duration;
    }

    public String getDurationString() {
        return Long.toString(duration);
    }

    public void setRowDetails(String rowDetails) {
        this.rowDetails = rowDetails;
    }

    public void setTimeStampInit(Date timeStampInit) {
        this.timeStampInit = timeStampInit;
    }

    public void setTimeStampInit(String timeStampInit) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            this.timeStampInit = simpleDateFormat.parse(timeStampInit);
        }catch (ParseException e) {
            Log.e("ERROR", "Parsing ISO8601 datetime failed", e);
        }
    }

    public void setTimeStampEnd(Date timeStampEnd) {
        TimeStampEnd = timeStampEnd;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

}
