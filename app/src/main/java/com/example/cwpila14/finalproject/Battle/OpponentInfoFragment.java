package com.example.cwpila14.finalproject.Battle;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cwpila14.finalproject.EliteFour.BattleActivity;
import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/14/2016.
 */

public class OpponentInfoFragment extends Fragment {

    TextView name;
    TextView lvl;
    static TextView hp;
    static ProgressBar bar;
    static Resources r;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.opponent_bio_fragment, container, false);

        r = getResources();

        name = (TextView) root.findViewById(R.id.OpponentPokemonName);
        lvl = (TextView) root.findViewById(R.id.OpponentPokemonLevel);
        hp = (TextView) root.findViewById(R.id.OpponentHealth);
        bar = (ProgressBar) root.findViewById(R.id.OpponentHealthBar);

        if(getActivity().getClass() == BattleActivity.class){
            name.setText(BattleActivity.getOpponentPokemon().getName());
            lvl.setText(""+BattleActivity.getOpponentPokemon().getlvl());
            bar.setMax(4*BattleActivity.getOpponentPokemon().getlvl());
            updateHP(4*BattleActivity.getOpponentPokemon().getlvl());
        } else {
            name.setText(WildBattleActivity.getWildPokemon().getName());
            lvl.setText("" + WildBattleActivity.getWildPokemon().getlvl());
            bar.setMax(4 * WildBattleActivity.getWildPokemon().getlvl());
            updateHPWild(4 * WildBattleActivity.getWildPokemon().getlvl());
        }


        return root;
    }

    public static void updateHPWild(int n){
        bar.setProgress(n);
        if(bar.getProgress() >= (.65 * bar.getMax())){
            bar.setProgressDrawable(new ColorDrawable(r.getColor(R.color.GREEN,null)));
        } else if(bar.getProgress() >= (.25 * bar.getMax())){
            bar.setProgressDrawable(new ColorDrawable(r.getColor(R.color.YELLOW, null)));
        } else {
            bar.setProgressDrawable(new ColorDrawable(r.getColor(R.color.RED, null)));
        }
        hp.setText("HP: " + WildBattleActivity.getWildPokemon().getHP() + "/" + (4*WildBattleActivity.getWildPokemon().getlvl()));
    }

    public static void updateHP(int n){
        bar.setProgress(n);
        if(bar.getProgress() >= (.65 * bar.getMax())){
            bar.setProgressDrawable(new ColorDrawable(r.getColor(R.color.GREEN,null)));
        } else if(bar.getProgress() >= (.25 * bar.getMax())){
            bar.setProgressDrawable(new ColorDrawable(r.getColor(R.color.YELLOW, null)));
        } else {
            bar.setProgressDrawable(new ColorDrawable(r.getColor(R.color.RED, null)));
        }
        hp.setText("HP: " + BattleActivity.getOpponentPokemon().getHP() + "/" + (4*BattleActivity.getOpponentPokemon().getlvl()));
    }
}
