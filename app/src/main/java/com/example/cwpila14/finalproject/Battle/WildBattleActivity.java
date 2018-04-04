package com.example.cwpila14.finalproject.Battle;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.cwpila14.finalproject.GameStuff.MainGameActivity;
import com.example.cwpila14.finalproject.Pokemon.Pokemon;
import com.example.cwpila14.finalproject.Pokemon.Rattata;
import com.example.cwpila14.finalproject.Pokemon.Weedle;
import com.example.cwpila14.finalproject.R;

import java.util.Random;

public class WildBattleActivity extends Activity {

    // instance variables
    MediaPlayer mediaPlayer = null;
    static private Pokemon player;
    static private Pokemon wild;

    static private Random random;

    private PlayerInfoFragment playerInfoFragment;
    private MovesFragment movesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerInfoFragment = new PlayerInfoFragment();
        movesFragment = new MovesFragment();
        player = MainGameActivity.getPlayerPokemon();
        random = new Random();
        wildPokemon();
        setContentView(R.layout.battle_layout);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // media stuff
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // media stuff
        mediaPlayer = MediaPlayer.create(this, R.raw.battle);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    // disable back button at this point
    @Override
    public void onBackPressed() {
        // sound stuff
        //sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
       // buttonSound = sp.load(this, R.raw.button, 1);
        //wrongSound = sp.load(this, R.raw.cancel, 1);
        //sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
        Toast.makeText(this, "Cannot go back at this point", Toast.LENGTH_SHORT).show();

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    public static Pokemon getPlayerPokemon(){
        return player;
    }

    public static Pokemon getWildPokemon(){
        return wild;
    }

    private void wildPokemon(){
        int n = random.nextInt(2);
        int lvl = (player.getlvl() - 5) + random.nextInt(9); //wild pokemon will be +- 4 lvls of player
        switch (n){
            case 0:
                //Weedle
                wild = new Weedle("Weedle", lvl);
                break;
            case 1:
                //rattata
                wild = new Rattata("rattata", lvl);
                break;
        }
    }

    public void turn(int move_number){
        //random pokemon goes first
        final int move = move_number;
        int n = random.nextInt(2);
        findViewById(R.id.battlebuttons).setVisibility(View.VISIBLE);
        findViewById(R.id.moves_fragment).setVisibility(View.GONE);
        if (n == 0){
            //user goes first
            user_turn(move_number);

            //text
            BattleButtonsFragment.setText("You hit " + wild.getName() + " with " + player.getMove(move));

            //user won
            //see if user won
            if(wild.getHP() <= 0){
                MainGameActivity.lvlup();
                this.finish();
            }

            //opponent
            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int r = wild_turn();
                    BattleButtonsFragment.setText(wild.getName() + " used " + wild.getMove(r));
                    //see if the user lost
                    if(player.getHP() <= 0){
                        finish();
                    }
                }
            }, 2000);
        } else {
            //opponent goes first
            int r = wild_turn();

            //text
            BattleButtonsFragment.setText(wild.getName() + " used " + wild.getMove(r));

            //see if the user lost
            if(player.getHP() <= 0){
                this.finish();
            }

            //user
            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    user_turn(move);
                    //text
                    BattleButtonsFragment.setText("You hit " + wild.getName() + " with " + player.getMove(move));

                    //see if user won
                    if(wild.getHP() <= 0){
                        MainGameActivity.lvlup();
                        finish();
                    }
                }
            }, 2000);
        }
    }

    public void user_turn(int move_number){
        int damage = player.getDamage(move_number);
        wild.setHp(wild.getHP()-damage);
        OpponentInfoFragment.updateHPWild(wild.getHP());
    }

    public int wild_turn(){
        int r = random.nextInt(4);
        int damage = wild.getDamage(r);
        player.setHp(player.getHP()-damage);
        //playerInfoFragment.updateHPWild(damage);
        PlayerInfoFragment.updateHPWild(player.getHP());
        return r;
    }


}
