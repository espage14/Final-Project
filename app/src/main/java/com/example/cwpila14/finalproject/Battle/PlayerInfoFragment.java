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

public class PlayerInfoFragment extends Fragment {

    TextView name;
    TextView lvl;
    static TextView hp;
    static ProgressBar bar;
    static Resources r;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.player_bio_battle, container, false);

        r = getResources();

        name = (TextView) root.findViewById(R.id.PokemonName);
        lvl = (TextView) root.findViewById(R.id.PokemonLevel);
        hp = (TextView) root.findViewById(R.id.Health);
        bar = (ProgressBar) root.findViewById(R.id.PokemonHealthBar);

        if(getActivity().getClass() == BattleActivity.class){
            name.setText(BattleActivity.getPlayerPokemon().getName());
            lvl.setText(""+BattleActivity.getPlayerPokemon().getlvl());
            bar.setMax(4*BattleActivity.getPlayerPokemon().getlvl());
            updateHP(4*BattleActivity.getPlayerPokemon().getlvl());
        } else {
            name.setText(WildBattleActivity.getPlayerPokemon().getName());
            lvl.setText("" + WildBattleActivity.getPlayerPokemon().getlvl());
            bar.setMax(4 * WildBattleActivity.getPlayerPokemon().getlvl());
            updateHPWild(4 * WildBattleActivity.getPlayerPokemon().getlvl());
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
        hp.setText("HP: " + WildBattleActivity.getPlayerPokemon().getHP() + "/" + (4*WildBattleActivity.getPlayerPokemon().getlvl()));
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
        hp.setText("HP: " + BattleActivity.getPlayerPokemon().getHP() + "/" + (4*BattleActivity.getPlayerPokemon().getlvl()));
    }
}
