package com.example.calero.counters.app.Utils;

import com.example.calero.counters.app.R;

/**
 * Created by calero on 30/03/2015.
 */
public class UtilApplication {

    public static int getArtResourceForCounterType(int typeId) {

        int artResource;

        switch (typeId){
            case 1:
                artResource = R.drawable.fragment_counter_only;
                break;
            case 2:
                artResource = R.drawable.fragment_counter_time;
                break;
            case 3:
                artResource = R.drawable.fragment_counter_set;
                break;
            default:
                artResource = R.drawable.fragment_counter_only;
                break;
        }

        return artResource;
    }

    public static int getTextResourceForCounterType(int typeId) {

        int counterType;

        switch (typeId){
            case 1:
                counterType = R.string.fragment_counter;
                break;
            case 2:
                counterType = R.string.fragment_counter_time;
                break;
            case 3:
                counterType = R.string.fragment_counter_set;
                break;
            default:
                counterType = R.string.fragment_counter;
                break;
        }

        return counterType;
    }

}
