package com.example.cwpila14.finalproject.Battle;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cwpila14.finalproject.EliteFour.BattleActivity;
import com.example.cwpila14.finalproject.GameStuff.BagActivity;
import com.example.cwpila14.finalproject.R;



public class BattleButtonsFragment extends Fragment {


    //Buttons
    Button bag;
    Button fight;
    static TextView text;
    Button run;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.battle_menu_layout, container, false);

        //Button set-up
        bag = (Button) root.findViewById(R.id.button_bag);
        fight = (Button) root.findViewById(R.id.button_fight);
        text = (TextView) root.findViewById(R.id.battle_text);
        run = (Button) root.findViewById(R.id.button_run);

        //Button on-click
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open bag
                Intent intent = new Intent(getActivity(), BagActivity.class);
                getActivity().startActivity(intent);
            }
        });

        fight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //disable other buttons, go to moves
                //disable();
                getActivity().findViewById(R.id.battlebuttons).setVisibility(View.GONE);
                getActivity().findViewById(R.id.moves_fragment).setVisibility(View.VISIBLE);

            }
        });

        text.setText("What will you do?");

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity().getClass() == BattleActivity.class){
                    Toast t = Toast.makeText(getContext(), "You can't run away now!", Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    //go back
                    getActivity().finish();
                }
            }
        });



        return root;
    }

    public void disable(){
        bag.setEnabled(false);
        fight.setEnabled(false);
        text.setEnabled(false);
        run.setEnabled(false);
    }

    public void enable(){
        bag.setEnabled(true);
        fight.setEnabled(true);
        text.setEnabled(true);
        run.setEnabled(true);
    }

    public static void setText(String s){
        text.setText(s);
    }
}
