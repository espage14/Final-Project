package com.example.cwpila14.finalproject.StartScreenStuff;

import android.app.Activity;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.widget.ImageView;

import com.example.cwpila14.finalproject.R;

public class MainActivity extends Activity {

    // instance variables
    private MediaPlayer mediaPlayer;
    ImageView i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
        //this.i = (ImageView) findViewById(R.id.charizardGif);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // music stuff
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // music stuff
        mediaPlayer = MediaPlayer.create(this, R.raw.startscreentwo);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}

