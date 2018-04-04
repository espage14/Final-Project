package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Bulbasaur extends Pokemon {

    // constructor
    public Bulbasaur(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Scratch", "Tackle", "Razor Leaf", "Petal Blizzard");
        setDamage(15, 10, 25, 35);
        setBackSprite(R.drawable.bulb_back);

    }

}
