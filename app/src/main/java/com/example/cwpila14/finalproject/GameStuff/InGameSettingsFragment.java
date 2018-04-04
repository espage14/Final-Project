package com.example.cwpila14.finalproject.GameStuff;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.cwpila14.finalproject.StartScreenStuff.MainActivity;
import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 11/4/2016.
 */

public class InGameSettingsFragment extends Fragment {

    // instance variables
    ImageButton pokedex;
    ImageButton settings;
    ImageButton elite;
    ImageButton pokeCenter;
    ImageButton bag;
    AlertDialog mDialog;

    // sound stuff
    SoundPool s = null;
    private int buttonSound, dismissSound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        // get items in fragment
        this.settings = (ImageButton) rootView.findViewById(R.id.Settings);

        // on click
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // display dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Settings");
                builder.setMessage(R.string.quit_game_label);
                builder.setCancelable(false);
                builder.setNegativeButton(R.string.cancel_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // play sound
                                s.play(dismissSound, 1f, 1f, 1, 0, 1f);
                                // dismiss dialog box
                            }
                        });
                // show the dialog
                builder.setPositiveButton(R.string.quit_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // play sound
                        s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                        // send to home screen
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
                //show dialogue
                mDialog = builder.show();

            }
        });

        // set up sounds
        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = s.load(getActivity(), R.raw.button, 1);
        dismissSound = s.load(getActivity(), R.raw.cancel, 1);

        return rootView;
    }
}
