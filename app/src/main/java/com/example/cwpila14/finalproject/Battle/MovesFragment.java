package com.example.cwpila14.finalproject.Battle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cwpila14.finalproject.EliteFour.BattleActivity;
import com.example.cwpila14.finalproject.GameStuff.BagActivity;
import com.example.cwpila14.finalproject.GameStuff.MainGameActivity;
import com.example.cwpila14.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovesFragment extends Fragment {

    //instance
    Button move_one;
    Button move_two;
    Button move_three;
    Button move_four;

    BattleActivity a = null;
    WildBattleActivity w = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_moves, container, false);

        //Button set-up
        move_one = (Button) root.findViewById(R.id.move_1);
        move_two = (Button) root.findViewById(R.id.move_2);
        move_three = (Button) root.findViewById(R.id.move_3);
        move_four = (Button) root.findViewById(R.id.move_4);

        move_one.setText(MainGameActivity.getPlayerPokemon().getMove(0));
        move_two.setText(MainGameActivity.getPlayerPokemon().getMove(1));
        move_three.setText(MainGameActivity.getPlayerPokemon().getMove(2));
        move_four.setText(MainGameActivity.getPlayerPokemon().getMove(3));

        if(getActivity().getClass() == BattleActivity.class) {
            a =  (BattleActivity) getActivity();
        } else {
            w = (WildBattleActivity) getActivity();
        }

        //Button on-click
        move_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a != null){
                    a.turn(0);
                } else {
                    w.turn(0);
                }
            }
        });

        move_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a != null){
                    a.turn(1);
                } else {
                    w.turn(1);
                }
            }
        });

        move_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a != null){
                    a.turn(2);
                } else {
                    w.turn(2);
                }
            }
        });

        move_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a != null){
                    a.turn(3);
                } else {
                    w.turn(3);
                }
            }
        });
        return root;
    }

    public void disable(){
        move_one.setEnabled(false);
        move_two.setEnabled(false);
        move_three.setEnabled(false);
        move_four.setEnabled(false);
    }

    public void enable(){
        move_one.setEnabled(true);
        move_two.setEnabled(true);
        move_three.setEnabled(true);
        move_four.setEnabled(true);
    }

}
