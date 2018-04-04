package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Rattata extends Pokemon {

    // constructor
    public Rattata(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Quick Attack", "Tackle", "Bite", "Hyperfang");
        setDamage(20, 10, 25, 30);
        setFrontSprite(R.drawable.rattata);

    }

}
