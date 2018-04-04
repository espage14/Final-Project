package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Arcanine extends Pokemon{
    // heat wave, fire spin, flamethrower, bite

    // constructor
    public Arcanine(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Heat Wave", "Fire Spin", "Flamethrower", "Bite");
        setDamage(25, 35, 40, 10);
        setFrontSprite(R.drawable.arcanine);


    }

}
