package com.example.cwpila14.finalproject.BioStuff;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.cwpila14.finalproject.R;

public class StarterPokemonActivity extends Activity {

    // instance variables
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_choice_activity);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // sound stuff
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
        // sound stuff
        mediaPlayer = MediaPlayer.create(this, R.raw.biomusic);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }
}
