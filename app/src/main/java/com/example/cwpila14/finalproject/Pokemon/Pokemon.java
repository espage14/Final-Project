package com.example.cwpila14.finalproject.Pokemon;

import android.graphics.drawable.Drawable;

public class Pokemon {
    // instance variables
    private String[] Moves = new String[4];
    private int[] Damage = new int[4];

    private String name;
    private int HP;
    private int lvl;

    private int FrontSprite;
    private int BackSprite;
    // constructor
    public Pokemon(String aName, int alvl) {
        name = aName;
        lvl = alvl;
        HP = 4 * lvl;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public void setHp(int hp){
        this.HP = hp;
    }

    public void setMoves(String move1, String move2, String move3, String move4){
        Moves[0] = move1;
        Moves[1] = move2;
        Moves[2] = move3;
        Moves[3] = move4;
    }

    public void setDamage(int one, int two, int three, int four){
        Damage[0] = one;
        Damage[1] = two;
        Damage[2] = three;
        Damage[3] = four;
    }

    public int getDamage(int index){
        return Damage[index];
    }

    public int getBackSprite() {
        return BackSprite;
    }

    public int getFrontSprite() {
        return FrontSprite;
    }

    public void setFrontSprite(int i){
        FrontSprite = i;
    }

    public String getMove(int i){
        return Moves[i];
    }

    public void setBackSprite(int i){
        BackSprite = i;
    }

    public int getlvl() {
        return lvl;
    }

    public void lvlup(){
        lvl++;
        setHp(lvl*4);
    }

    public void heal(){
        setHp(lvl*4);
    }

}

