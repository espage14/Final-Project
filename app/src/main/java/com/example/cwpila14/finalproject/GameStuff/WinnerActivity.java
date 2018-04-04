package com.example.cwpila14.finalproject.GameStuff;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cwpila14.finalproject.R;
import com.example.cwpila14.finalproject.StartScreenStuff.MainActivity;

import static com.example.cwpila14.finalproject.BioStuff.BioFragment.s;

public class WinnerActivity extends Activity {

    Button exit;
    // sound stuff
    SoundPool s = null;
    private int buttonSound, dismissSound, victorySound;
    MediaPlayer mediaPlayer = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
        // set up sounds
        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = s.load(this, R.raw.button, 1);
        dismissSound = s.load(this, R.raw.cancel, 1);
        victorySound = s.load(this, R.raw.victorysound, 1);
        s.play(victorySound, 1f, 1f, 1, 0, 1f);

        this.exit = (Button) findViewById(R.id.exitGameButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // play sound
                s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // send to home screen
                Intent intent = new Intent(WinnerActivity.this, MainActivity.class);
                WinnerActivity.this.startActivity(intent);
            }
        });
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
        mediaPlayer = MediaPlayer.create(this, R.raw.victory);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    // disable back button at this point
    @Override
    public void onBackPressed() {
        // sound stuff
        s.play(dismissSound, 1f, 1f, 1, 0, 1f);
        Toast.makeText(this, "Cannot go back at this point", Toast.LENGTH_SHORT).show();

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }
}
