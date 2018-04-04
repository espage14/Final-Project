package com.example.cwpila14.finalproject.GameStuff;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 11/13/2016.
 */

public class InGameBagFragment extends Fragment {

    // instance variables
    ImageButton bag;

    // sound stuff
    SoundPool s = null;
    private int buttonSound;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bag_fragment, container, false);

        // get items in fragment

        this.bag = (ImageButton) rootView.findViewById(R.id.YourItems);

        // on click
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // play sound
                s.play(buttonSound, 1f, 1f, 1, 0, 1f);
                // send to next activity
                Intent intent = new Intent(getActivity(), BagActivity.class);
                getActivity().startActivity(intent);
            }
        });

        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = s.load(getActivity(), R.raw.button, 1);

        return rootView;
    }

    static public void usePotion(Context c){
        MainGameActivity.getPlayerPokemon().heal();
    }
}
