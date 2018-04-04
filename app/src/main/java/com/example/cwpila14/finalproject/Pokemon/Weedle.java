package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Weedle extends Pokemon {

    // constructor
    public Weedle(String aName, int alvl) {
        super(aName, alvl);
        setMoves("String Shot", "Tackle", "Poison Sting", "Bug Bite");
        setDamage(0, 5, 10, 15);
        setFrontSprite(R.drawable.weedle);

    }

}
