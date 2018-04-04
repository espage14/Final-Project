package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Gengar extends Pokemon{
    // lick, suckerPunch, shadowBall, dreamEater

    // constructor
    public Gengar(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Lick", "Sucker Punch", "Shadow Ball", "Dream Eater");
        setDamage(10, 20, 25, 30);
        setFrontSprite(R.drawable.gengar);

    }

}
