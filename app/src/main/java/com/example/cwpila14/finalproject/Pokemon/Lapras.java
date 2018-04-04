package com.example.cwpila14.finalproject.Pokemon;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/10/2016.
 */

public class Lapras extends Pokemon{

    // Water Pulse, Ice Beam, Hydro Pump, Body Slam

    // constructor
    public Lapras(String aName, int alvl) {
        super(aName, alvl);
        setMoves("Water Pulse", "Ice Beam", "Hydro Pump", "Body Slam");
        setDamage(25, 35, 40, 30);
        setFrontSprite(R.drawable.lapras);

    }

}
