package com.example.calero.counters.app;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

public class CountersApplication extends Application {

    private MediaPlayer mediaPlayerIncoming;
    private MediaPlayer mediaPlayerOutgoing;
    private AudioManager audioManager;
    private int currentVolume;

    private static final String LOG_TAG = CountersApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        Log.d(LOG_TAG, String.valueOf(currentVolume));

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

        mediaPlayerIncoming = MediaPlayer.create(this, R.raw.incoming);

        mediaPlayerIncoming.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        });

        mediaPlayerOutgoing = MediaPlayer.create(this, R.raw.outgoing);

        mediaPlayerOutgoing.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
//                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        });

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
    }

    public void playIncoming(){
        Log.d(LOG_TAG, "playIncoming");

        mediaPlayerIncoming.start(); // no need to call prepare(); create() does that for you
    }

    public void playOutgoing(){
        Log.d(LOG_TAG, "playOutgoing");

        mediaPlayerOutgoing.start(); // no need to call prepare(); create() does that for you
    }

}
