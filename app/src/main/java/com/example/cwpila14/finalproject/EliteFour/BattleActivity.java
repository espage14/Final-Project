package com.example.cwpila14.finalproject.EliteFour;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.cwpila14.finalproject.Battle.BattleButtonsFragment;
import com.example.cwpila14.finalproject.Battle.OpponentInfoFragment;
import com.example.cwpila14.finalproject.Battle.PlayerInfoFragment;
import com.example.cwpila14.finalproject.GameStuff.AgathaActivity;
import com.example.cwpila14.finalproject.GameStuff.LanceActivity;
import com.example.cwpila14.finalproject.GameStuff.LoreleiActivity;
import com.example.cwpila14.finalproject.GameStuff.LoserActvity;
import com.example.cwpila14.finalproject.GameStuff.MainGameActivity;
import com.example.cwpila14.finalproject.GameStuff.WinnerActivity;
import com.example.cwpila14.finalproject.Pokemon.Alakazam;
import com.example.cwpila14.finalproject.Pokemon.Arcanine;
import com.example.cwpila14.finalproject.Pokemon.Gengar;
import com.example.cwpila14.finalproject.Pokemon.Lapras;
import com.example.cwpila14.finalproject.Pokemon.Pokemon;
import com.example.cwpila14.finalproject.R;

import java.util.Random;

public class BattleActivity extends Activity {

    // instance variables
    MediaPlayer mediaPlayer = null;
    static private Pokemon player;
    static private Pokemon opponent;
    static private Random random;
    private String Elite;
    private Class nextClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        Elite = i.getStringExtra("activity");

        player = MainGameActivity.getPlayerPokemon();
        random = new Random();
        opponentPokemon();
        setContentView(R.layout.battle_layout);
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
            BattleButtonsFragment.setText("You hit " + opponent.getName() + " with " + player.getMove(move));

            //user won
            //see if user won
            if(opponent.getHP() <= 0){
                MainGameActivity.lvlup();
                this.finish();
            }

            //opponent
            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int r = opponent_turn();
                    BattleButtonsFragment.setText(opponent.getName() + " used " + opponent.getMove(r));
                    //see if the user lost
                    if(player.getHP() <= 0){
                        Intent i = new Intent(BattleActivity.this, LoserActvity.class);
                        BattleActivity.this.startActivity(i);
                    }
                }
            }, 2000);
        } else {
            //opponent goes first
            int r = opponent_turn();

            //text
            BattleButtonsFragment.setText(opponent.getName() + " used " + opponent.getMove(r));

            //see if the user lost
            if(player.getHP() <= 0){
                Intent i = new Intent(BattleActivity.this, LoserActvity.class);
                BattleActivity.this.startActivity(i);
            }

            //user
            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    user_turn(move);
                    //text
                    BattleButtonsFragment.setText("You hit " + opponent.getName() + " with " + player.getMove(move));

                    //see if user won
                    if(opponent.getHP() <= 0){
                        MainGameActivity.lvlup();
                        MainGameActivity.lvlup();
                        Intent i = new Intent(BattleActivity.this, nextClass);
                        BattleActivity.this.startActivity(i);

                    }
                }
            }, 2000);
        }
    }

    public void user_turn(int move_number){
        int damage = player.getDamage(move_number);
        opponent.setHp(opponent.getHP()-damage);
        OpponentInfoFragment.updateHP(opponent.getHP());
    }

    public int opponent_turn(){
        int r = random.nextInt(4);
        int damage = opponent.getDamage(r);
        player.setHp(player.getHP()-damage);
        //playerInfoFragment.updateHPWild(damage);
        PlayerInfoFragment.updateHP(player.getHP());
        return r;
    }

    private void opponentPokemon(){
        switch (Elite){
            case "bruno":
                opponent = new Arcanine("Arcanine", 40);
                nextClass = LoreleiActivity.class;
                break;
            case "lorelei":
                opponent = new Gengar("Gengar", 45);
                nextClass = AgathaActivity.class;
                break;
            case "agatha":
                opponent = new Alakazam("Alakazam", 50);
                nextClass = LanceActivity.class;
                break;
            case "lance":
                opponent = new Lapras("Lapras", 55);
                nextClass = WinnerActivity.class;
                break;
        }
    }

    public static Pokemon getOpponentPokemon(){
        return opponent;
    }

}
