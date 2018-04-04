package com.example.cwpila14.finalproject.BioStuff;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 11/4/2016.
 */

public class BioFragment extends Fragment {

    // Instance variables
    ImageView checkMarkOne;
    ImageView checkMarkTwo;
    Button continueButton;
    Boolean selected;

    // gender selected method
    public boolean genderSelected() {
        return selected;
    }

    ImageButton boy;
    ImageButton girl;

    EditText playerName;
    public static String s;
    public static String c;


    // sound stuff
    private SoundPool sp = null;
    private int buttonSound, wrongSound;

    public String player_name;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bio_fragment, container, false);

        // get all items in fragment
        this.checkMarkOne = (ImageView) rootView.findViewById(R.id.checkmark1);
        this.checkMarkTwo = (ImageView) rootView.findViewById(R.id.checkmark2);

        this.boy = (ImageButton) rootView.findViewById(R.id.boy);
        this.girl = (ImageButton) rootView.findViewById(R.id.girl);

        this.playerName = (EditText) rootView.findViewById(R.id.playerName);
        this.s = playerName.getText().toString();

        this.continueButton = (Button) rootView.findViewById(R.id.continueButton);

        // set visibility of checks at start
        checkMarkOne.setVisibility(View.INVISIBLE);
        checkMarkTwo.setVisibility(View.INVISIBLE);

        selected = false;


        // on click
        boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //play sound
                sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // change selected
                selected = true;
                // modify checks
                checkMarkOne.setVisibility(View.VISIBLE);
                checkMarkTwo.setVisibility(View.INVISIBLE);

                c = "boy";

            }
        });

        // on click
        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // play sound
                sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // change selected
                selected = true;
                // modify checks
                checkMarkOne.setVisibility(View.INVISIBLE);
                checkMarkTwo.setVisibility(View.VISIBLE);

                c = "girl";
            }
        });

        // on click
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = playerName.getText().toString();
                if (s.trim().length() <= 0) { // check if text field is empty
                    // play sound
                    sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
                    // display message
                    Toast.makeText(getContext(), "You need to enter a player name", Toast.LENGTH_SHORT).show();
                } else if (s.trim().length() > 15) { // check if text field is greater than 15 characters
                    // play sound
                    sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
                    // display message
                    Toast.makeText(getContext(), "Your player name is too long", Toast.LENGTH_SHORT).show();
                }
                else if (checkMarkOne.getVisibility() == View.INVISIBLE && checkMarkTwo.getVisibility() == View.INVISIBLE){ // check if gender is selected
                    // play sound
                    sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
                    // display message
                    Toast.makeText(getContext(), "You need to select a gender", Toast.LENGTH_SHORT).show();
                } else {
                    // play sound
                    sp.play(buttonSound, 1f, 1f, 1, 0, 1f);
                    // set player name
                    player_name = playerName.getText().toString();
                    // load next activity
                    Intent intent = new Intent(getActivity(), StarterPokemonActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        });

        // sound stuff
        sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = sp.load(getActivity(), R.raw.button, 1);
        wrongSound = sp.load(getActivity(), R.raw.cancel, 1);

        return rootView;
    }

    public static String getName(){
        return s;
    }

    public static String getCharacter(){
        return c;
    }

}
