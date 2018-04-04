package com.example.cwpila14.finalproject.Pokemon;

import android.content.res.Resources;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Alakazam extends Pokemon{

    // confusion, psybeam, psychic, tackle

    // constructor
    public Alakazam(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Confusion", "Psybeam", "Psychic", "Tackle");
        setDamage(20, 30, 40, 10);
        setFrontSprite(R.drawable.alakazam);


    }

}
