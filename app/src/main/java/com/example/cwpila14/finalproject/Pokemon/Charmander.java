package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/1/2016.
 */

public class Charmander extends Pokemon {

    // constructor
    public Charmander(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Scratch", "Tackle", "Ember", "Flamethrower");
        setDamage(25, 10, 35, 40);
        setBackSprite(R.drawable.charmander_back);

    }

}
