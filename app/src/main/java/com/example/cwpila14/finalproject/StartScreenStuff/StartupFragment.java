package com.example.cwpila14.finalproject.StartScreenStuff;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cwpila14.finalproject.BioStuff.BioActivity;
import com.example.cwpila14.finalproject.R;
import com.example.cwpila14.finalproject.TutorialStuff.TutorialActivity;

/**
 * Created by cwpila14 on 11/1/2016.
 */

public class StartupFragment extends Fragment {

    // instance variables
    private Button newGame = null;
    private Button about = null;
    private AlertDialog mDialog = null;
    private SoundPool s = null;
    private int buttonSound, cancelSound;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // get items in fragment
        View rootView = inflater.inflate(R.layout.button_fragment, container, false);

        this.newGame = (Button) rootView.findViewById(R.id.StartButton);
        this.about = (Button) rootView.findViewById(R.id.AboutButton);

        // on click
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // play sound
                s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // send to next activity
                Intent intent = new Intent(getActivity(), BioActivity.class);
                getActivity().startActivity(intent);

            }
        });

        // on click
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // play sound
                s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // display dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("About the Game");
                builder.setMessage(R.string.About);
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
                // show the dialog
                builder.setPositiveButton(R.string.tutorial_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // play sound
                        s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                        // send to next acitivty
                        Intent intent = new Intent(getActivity(), TutorialActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
                // show
                mDialog = builder.show();

            }
        });

        // soundpool stuff - load files
        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = s.load(getActivity(), R.raw.button, 1);
        cancelSound = s.load(getActivity(), R.raw.cancel, 1);

        return rootView;
    }
}
