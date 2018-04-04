package com.example.cwpila14.finalproject.Battle;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cwpila14.finalproject.BioStuff.StarterPokemonFragment;
import com.example.cwpila14.finalproject.EliteFour.BattleActivity;
import com.example.cwpila14.finalproject.Pokemon.Pokemon;
import com.example.cwpila14.finalproject.R;


public class OpponentPokemonFragment extends Fragment {

    ImageView pokemon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pokemon, container, false);
        pokemon = (ImageView) root.findViewById(R.id.playerpokemon);
        if(getActivity().getClass() == BattleActivity.class) {
            pokemon.setBackground(getResources().getDrawable(BattleActivity.getOpponentPokemon().getFrontSprite(), null));
        } else {
            pokemon.setBackground(getResources().getDrawable(WildBattleActivity.getWildPokemon().getFrontSprite(), null));
        }

        return root;
    }

}
