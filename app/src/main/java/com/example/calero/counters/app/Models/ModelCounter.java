package com.example.calero.counters.app.Models;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.example.calero.counters.app.Data.CountersContract;
import com.example.calero.counters.app.Repositories.RepositoryCounter;
import com.example.calero.counters.app.R;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.calero.counters.app.UI.Activities.MainActivity.getAppContext;

/**
 * Created by calero on 17/12/2014.
 */
public class ModelCounter {

    int intType = 1;
    long longCounter = 0;
    private Date timeStampStart = new Date();
    private Date timeStampStop = new Date();
    private DecimalFormat numberFormat = new DecimalFormat("0000");
    private double locationLongitude = 0;
    private double locationLatitude = 0;

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public ModelCounter(int intType) {
        this.intType = intType;
    }

    public void setTimeStampStart(Date timeStampStart) {
        this.timeStampStart = timeStampStart;
    }

    public Date getTimeStampStop() {
        return timeStampStop;
    }

    public void setTimeStampStop(Date timeStampStop) {
        this.timeStampStop = timeStampStop;
    }

    public DecimalFormat getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(DecimalFormat numberFormat) {
        this.numberFormat = numberFormat;
    }

    public int getIntType() {
        return intType;
    }

    public void setIntType(int intType) {
        this.intType = intType;
    }

    public long getLongCounter() {
        return longCounter;
    }

    public void setLongCounter(long longCounter) {
        this.longCounter = longCounter;
    }

    public String getStringCounterFormat(){
        return numberFormat.format(longCounter);
    }

    public void plusLongCounter(){
        ++longCounter;
    }

    public void minusLongCounter(){
        if (longCounter>0)
            --longCounter;
    }

    public void resetLongCounter(){
        longCounter = 0;
    }

    public Date getTimeStampStart() {
        return timeStampStart;
    }

    public void setTimeStampStartSerializable( Serializable timeSerializable ){
        timeStampStart = (Date) timeSerializable;
    }

    public String getTimeStampStartString() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(getTimeStampStart());
    }

    public String getTimeStampStartMessage() {
        return getAppContext().getString(R.string.counter_start) + " " +  getTimeStampStartString();
    }

    public String getTimeStampStopString() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(getTimeStampStop()) ;
    }

    public String getTimeStampStopMessage() {
        return getAppContext().getString(R.string.counter_stop) + " " +  getTimeStampStopString();
    }

    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("intType", getIntType());
        contentValues.put("longCounted", getLongCounter());
        contentValues.put("timeStampStart", getTimeStampStartString());
        contentValues.put("timeStampStop", getTimeStampStopString());
        return (contentValues);
    }

    public void saveDatabase(){
        RepositoryCounter repositoryCounter = new RepositoryCounter() ;
        ContentValues contentValues = getContentValues();
        repositoryCounter.insertCounter(contentValues);
    }

    public ContentValues getContentValuesProvider(){
        ContentValues testValues = new ContentValues();
        testValues.put(CountersContract.CountersEntry.COLUMN_NAME, "Demo data");
        testValues.put(CountersContract.CountersEntry.COLUMN_COUNTED, getLongCounter());
        testValues.put(CountersContract.CountersEntry.COLUMN_TYPE, getIntType());
        testValues.put(CountersContract.CountersEntry.COLUMN_STAR, getTimeStampStartString());
        testValues.put(CountersContract.CountersEntry.COLUMN_STOP, getTimeStampStopString());
        testValues.put(CountersContract.CountersEntry.COLUMN_LATITUDE, getLocationLatitude());
        testValues.put(CountersContract.CountersEntry.COLUMN_LONGITUDE, getLocationLongitude());
        return testValues;
    }

    public long insertDatabase(){
        ContentValues testValues = getContentValuesProvider();
        Uri locationUri = getAppContext().getContentResolver().insert(CountersContract.CountersEntry.CONTENT_URI, testValues);
        long locationRowId = ContentUris.parseId(locationUri);
        return locationRowId;
    }

}
