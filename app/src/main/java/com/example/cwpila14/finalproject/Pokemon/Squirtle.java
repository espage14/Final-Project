package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Squirtle extends Pokemon {

    // constructor
    public Squirtle(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Scratch", "Tackle", "Watergun", "Hydropump");
        setDamage(25, 10, 30, 35);
        setBackSprite(R.drawable.squirtle_back);

    }

}
