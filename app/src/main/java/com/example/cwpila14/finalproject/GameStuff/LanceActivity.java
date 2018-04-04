package com.example.cwpila14.finalproject.GameStuff;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cwpila14.finalproject.R;
import com.example.cwpila14.finalproject.StartScreenStuff.MainActivity;

import static com.example.cwpila14.finalproject.BioStuff.BioFragment.s;

public class LanceActivity extends Activity {

    Button fight;

    // sound stuff
    SoundPool s = null;
    private int buttonSound, dismissSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lance);
        getWindow().getAttributes().windowAnimations = R.style.Fade;

        this.fight = (Button) findViewById(R.id.lance_fight);

        fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // play sound
                s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // send to home screen
                Intent intent = new Intent(LanceActivity.this, WinnerActivity.class);
                LanceActivity.this.startActivity(intent);
            }
        });

        // set up sounds
        s = new SoundPool(3,AudioManager.STREAM_MUSIC, 0);
        buttonSound = s.load(this, R.raw.button, 1);
        dismissSound = s.load(this, R.raw.cancel, 1);
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
