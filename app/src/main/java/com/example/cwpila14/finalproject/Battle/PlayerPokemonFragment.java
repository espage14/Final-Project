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
import com.example.cwpila14.finalproject.R;

public class PlayerPokemonFragment extends Fragment {

    ImageView pokemon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pokemon, container, false);

        pokemon = (ImageView) root.findViewById(R.id.playerpokemon);


        //get the players pokemon
        //Drawable sprite = getResources().getDrawable(WildBattleActivity.getPlayerPokemon().getBackSprite(), null);
        //pokemon.setBackground(sprite);

        String s = StarterPokemonFragment.getStarter();
        switch (s){
            case "Charmander":
                pokemon.setBackground(getResources().getDrawable(R.drawable.charmander_back, null));
                break;
            case "Squirtle":
                pokemon.setBackground(getResources().getDrawable(R.drawable.squirtle_back, null));
                break;
            case "Bulbasaur":
                pokemon.setBackground(getResources().getDrawable(R.drawable.bulb_back, null));
                break;
        }

        return root;
    }
}
