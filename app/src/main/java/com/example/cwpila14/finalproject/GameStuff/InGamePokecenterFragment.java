package com.example.cwpila14.finalproject.GameStuff;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 11/13/2016.
 */

public class InGamePokecenterFragment extends Fragment{

    // instance variables
    ImageButton pokecenter;
    AlertDialog mDialog;

    // sound stuff
    SoundPool s = null;
    private int buttonSound;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pokecenter_fragment, container, false);

        // get items in fragment
        this.pokecenter = (ImageButton) rootView.findViewById(R.id.PokeCenter);

        // on click
        pokecenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // play sound
                s.play(buttonSound, 1f, 1f, 1, 0, 1f);

                //heal
                MainGameActivity.getPlayerPokemon().heal();

                // display a dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("PokeCenter");
                builder.setMessage(R.string.pokecenter_label);
                builder.setCancelable(false);
                builder.setNegativeButton(R.string.thankyou,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // play sound
                                s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                                // dismiss dialog box
                            }
                        });
                // show dialog
                mDialog = builder.show();

                // TODO restore starter pokemon to full health

            }
        });

        // set up sounds
        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = s.load(getActivity(), R.raw.button, 1);

        return rootView;
    }
}
