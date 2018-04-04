package com.example.cwpila14.finalproject.GameStuff;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.cwpila14.finalproject.R;
import com.example.cwpila14.finalproject.TutorialStuff.TutorialActivity;

import static com.example.cwpila14.finalproject.BioStuff.BioFragment.s;

public class BagActivity extends Activity {

    // instance variables
    Button back;
    ImageButton potion;
    private AlertDialog mDialog = null;
    private SoundPool s = null;
    private int buttonSound, cancelSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bag_layout);
        getWindow().getAttributes().windowAnimations = R.style.Fade;

        this.potion = (ImageButton) findViewById(R.id.Potion);

        potion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //heal
                MainGameActivity.getPlayerPokemon().heal();

                // display dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(BagActivity.this);
                builder.setTitle(R.string.potionName);
                builder.setMessage(R.string.potion_heal);
                builder.setCancelable(false);
                builder.setNegativeButton(R.string.ok_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // dismiss dialog box.
                                //play sound
                                s.play(cancelSound, 1f, 1f, 1, 0, 1f);

                            }
                        });
                // show
                mDialog = builder.show();

            }
        });

        // soundpool stuff - load files
        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = s.load(this, R.raw.button, 1);
        cancelSound = s.load(this, R.raw.cancel, 1);

    }
}
