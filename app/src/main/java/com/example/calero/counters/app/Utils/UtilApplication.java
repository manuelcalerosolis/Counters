package com.example.calero.counters.app.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.calero.counters.app.R;
import com.example.calero.counters.app.UI.Activities.MainActivity;

public class UtilApplication {

    private static final String LOG_TAG = UtilApplication.class.getSimpleName();
    //    private static MediaPlayer mediaPlayer;

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

    public static boolean isSound() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.getAppContext());
        return defaultSharedPreferences.getBoolean(MainActivity.getAppContext().getString(R.string.preference_sound_key), false);
    }

    public static void plusSound(){
    Log.d(LOG_TAG, "Call to plusSound()");

    final AudioManager audioManager = (AudioManager) MainActivity.getAppContext().getSystemService(Context.AUDIO_SERVICE);
    final int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.getAppContext(), R.raw.incoming);

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);            
        }
    });

    mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }

//
//    public static synchronized MediaPlayer getMediaPlayerInstance() {
//        if (mediaPlayer == null) {
//            mediaPlayer = new MediaPlayer(this, R.raw.incoming );
//        }
//        return mediaPlayer;
//    }

}


