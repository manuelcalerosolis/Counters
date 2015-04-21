package com.example.calero.counters.app;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class TuApplication extends Application {

    private MediaPlayer mediaPlayerIncoming;
    private MediaPlayer mediaPlayerOutgoing;

    @Override
    public void onCreate() {
        super.onCreate();

        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

        mediaPlayerIncoming = MediaPlayer.create(this, R.raw.incoming);

        mediaPlayerIncoming.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        });

        mediaPlayerOutgoing = MediaPlayer.create(this, R.raw.outgoing);

        mediaPlayerOutgoing.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
            }
        });

    }

    public void playIncoming(){
        mediaPlayerIncoming.start(); // no need to call prepare(); create() does that for you
    }

    public void playOutgoing(){
        mediaPlayerOutgoing.start(); // no need to call prepare(); create() does that for you
    }
}
