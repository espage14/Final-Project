package com.example.cwpila14.finalproject.BioStuff;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.cwpila14.finalproject.R;

public class BioActivity extends Activity {

    // instance variables
    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bio_activity);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // media stuff
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // media stuff
        mediaPlayer = MediaPlayer.create(this, R.raw.biomusic);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}
