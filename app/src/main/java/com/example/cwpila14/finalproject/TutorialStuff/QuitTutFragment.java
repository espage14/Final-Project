package com.example.cwpila14.finalproject.TutorialStuff;

import android.app.Fragment;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cwpila14.finalproject.R;
import com.example.cwpila14.finalproject.StartScreenStuff.MainActivity;

/**
 * Created by cwpila14 on 11/5/2016.
 */

public class QuitTutFragment extends Fragment {

    // instance variables
    Button quit;
    private SoundPool s = null;
    private int exit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quit_tut_fragment, container, false);

        // get items in fragment
        this.quit = (Button) rootView.findViewById(R.id.quit_tut);

        // on click
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // play sound
                s.play(exit, 1f, 1f, 1, 0, 1f);
                // send back to homescreen
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });

        // sound stuff
        s = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        exit = s.load(getActivity(), R.raw.button, 1);


        return rootView;
    }
}
