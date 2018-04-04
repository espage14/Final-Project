package com.example.cwpila14.finalproject.TutorialStuff;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import com.example.cwpila14.finalproject.R;
import com.example.cwpila14.finalproject.TutorialStuff.CustomPagerAdapter;

public class TutorialActivity extends Activity {

    // instance variables
    ViewPager viewPager;
    //CustomSwipeAdaptor adaptor;
    Button quit_tut;

    // sound and media stuff
    private SoundPool s = null;
    private MediaPlayer mediaPlayer = null;

    //MainActivity m = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_view);
        getWindow().getAttributes().windowAnimations = R.style.Fade;

        // get items in activity
        this.viewPager = (ViewPager) findViewById(R.id.view_pager);
        // set adapter
        viewPager.setAdapter(new CustomPagerAdapter(this));


    }

    @Override
    protected void onPause() {
        super.onPause();
        // music stuff
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
        // music stuff
        mediaPlayer = MediaPlayer.create(this, R.raw.tut);
        mediaPlayer.setVolume(0.5f, 0.5f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

    }
}