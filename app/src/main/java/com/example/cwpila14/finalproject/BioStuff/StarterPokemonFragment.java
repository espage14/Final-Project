package com.example.cwpila14.finalproject.BioStuff;

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

import com.example.cwpila14.finalproject.GameStuff.MainGameActivity;
import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 11/4/2016.
 */

public class StarterPokemonFragment extends Fragment {

    // instance variables
    private String fire;
    private String water;
    private String grass;

    ImageButton bulb;
    ImageButton charm;
    ImageButton squir;

    private static String starter;
    private AlertDialog mDialog;

    // sound stuff
    private SoundPool sp = null;
    private int buttonSound, wrongSound;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.starter_pokemon_fragment, container, false);

        // get items in fragment
        fire = "Charmander";
        water = "Squirtle";
        grass = "Bulbasaur";

        this.bulb = (ImageButton) rootView.findViewById(R.id.Bulbasaur);
        this.squir = (ImageButton) rootView.findViewById(R.id.Squirtle);
        this.charm = (ImageButton) rootView.findViewById(R.id.Charmander);

        // on click
        bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // set type
                starter = grass;

                // display dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Your Pokemon!");
                builder.setMessage(R.string.grass_pokemon);
                builder.setCancelable(false);
                builder.setNegativeButton(R.string.cancel_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // dismiss dialog box
                                sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
                            }
                        });
                // show the dialog
                builder.setPositiveButton(R.string.choose_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                        // send to next activity
                        Intent intent = new Intent(getActivity(), MainGameActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
                // show
                mDialog = builder.show();

            }
        });

        // on click
        squir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // set type
                starter = water;
                // display dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Your Pokemon!");
                builder.setMessage(R.string.water_pokemon);
                builder.setCancelable(false);
                builder.setNegativeButton(R.string.cancel_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
                                // dismiss dialog box
                            }
                        });
                // show the dialog
                builder.setPositiveButton(R.string.choose_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                        // send to next activity
                        Intent intent = new Intent(getActivity(), MainGameActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
                // show
                mDialog = builder.show();
            }
        });

        // on click
        charm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // set type
                starter = fire;

                // display dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Your Pokemon!");
                builder.setMessage(R.string.fire_pokemon);
                builder.setCancelable(false);
                builder.setNegativeButton(R.string.cancel_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // dismiss dialog box
                                sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
                            }
                        });
                // show the dialog
                builder.setPositiveButton(R.string.choose_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                        // send to next activity
                        Intent intent = new Intent(getActivity(), MainGameActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
                // show
                mDialog = builder.show();
            }
        });

        // sound stuff
        sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = sp.load(getActivity(), R.raw.button, 1);
        wrongSound = sp.load(getActivity(), R.raw.cancel, 1);

        return rootView;
    }

    public static String getStarter(){
        return starter;
    }
}
