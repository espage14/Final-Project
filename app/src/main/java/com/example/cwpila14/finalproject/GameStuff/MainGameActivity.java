package com.example.cwpila14.finalproject.GameStuff;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cwpila14.finalproject.BioStuff.BioFragment;
import com.example.cwpila14.finalproject.BioStuff.StarterPokemonFragment;
import com.example.cwpila14.finalproject.Pokemon.Bulbasaur;
import com.example.cwpila14.finalproject.Pokemon.Charmander;
import com.example.cwpila14.finalproject.Pokemon.Pokemon;
import com.example.cwpila14.finalproject.Pokemon.Squirtle;
import com.example.cwpila14.finalproject.StartScreenStuff.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.example.cwpila14.finalproject.R;

import static java.security.AccessController.getContext;

public class MainGameActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, SensorEventListener {

    // instance variables
    MediaPlayer mediaPlayer;

    private Grid grid;

    public static final int NOTIFICATION_ID = 123;

    private GoogleApiClient mGoogleApiClient = null;
    private LocationRequest mLocationRequest;

    //Google
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private static final String DIALOG_ERROR = "dialog_error";
    private boolean mResolvingError = false;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    //Compass
    private float currentDegree = 0f;
    private SensorManager mSensorManager;

    private TextView name;
    private ImageView character;
    private ImageView starter;

    private static
    Pokemon player;

    //TODO remove unused things when done
    Location mLastLocation = null;
    Location mCurrentLocation = null;
    private static final int PERMISSION_REQUEST_FINE_LOCATION = 1;

    // sound stuff
    private SoundPool sp = null;
    private int buttonSound, wrongSound;

    private AlertDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
        onPrepared();

        //Compass
        //compass = (ImageView) findViewById(R.id.compass);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        String permission = getString(R.string.permission_resource);


        //Check permissions
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            // Create an instance of GoogleAPIClient
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

        } else {
            // request permission from the user
            ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQUEST_FINE_LOCATION);
        }


        // set up some things
        grid = (Grid) getFragmentManager().findFragmentById(R.id.grid_fragment);

        name = (TextView) findViewById(R.id.textView3);
        character = (ImageView) findViewById(R.id.imageView2);
        starter = (ImageView) findViewById(R.id.imageView3);

        name.setText(BioFragment.getName());

        setStarter();
        setCharacter();

        //instance state
        mResolvingError = savedInstanceState != null && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        stopLocationUpdates();
        //Compass
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.game);
        mediaPlayer.setVolume(0.1f, 0.1f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    // request permissions


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSION_REQUEST_FINE_LOCATION:
                // Need to see if permission was granted
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // Create an instance of GoogleAPIClient
                    mGoogleApiClient = new GoogleApiClient.Builder(this)
                            .addApi(LocationServices.API)
                            .addConnectionCallbacks(this)
                            .addOnConnectionFailedListener(this)
                            .build();
                }
                mGoogleApiClient = null;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        //TODO check permissions
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        //startLocationUpdates();

        //Start updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {


        if(mResolvingError){
            //already attempting to resolve an error
            return;
        }else if (result.hasResolution()) {
            try {
                mResolvingError = true;
                result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again
                mGoogleApiClient.connect();
            }
        } else {
            // Show dialog
            showErrorDialog(result.getErrorCode());
            mResolvingError = true;
        }

    }

    // Creates a dialog for an error msg
    private void showErrorDialog(int errorCode){
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        //dialogFragment.show(getSupportFragmentManager(), "errordialog");
    }

    public void onDialogDismissed(){
        mResolvingError = false;
    }

    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() { }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GoogleApiAvailability.getInstance().getErrorDialog(
                    this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            //((MainGameActivity) getActivity()).onDialogDismissed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_RESOLVE_ERROR){
            mResolvingError = false;
            if (resultCode == RESULT_OK){
                if(!mGoogleApiClient.isConnecting() && !mGoogleApiClient.isConnected()){
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        move();

    }

    protected void stopLocationUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    private void move(){
        float distance = mCurrentLocation.distanceTo(mLastLocation);
        if(distance >= 5.0){
            grid.move_player(mCurrentLocation, mLastLocation);
            mLastLocation = mCurrentLocation;
        }
    }

    //Compass stuff
    @Override
    public void onSensorChanged(SensorEvent event) {
        float degree = Math.round(event.values[0]);
        //Rotation Animation
        RotateAnimation rotate = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(210);
        rotate.setFillAfter(true);
        //compass.startAnimation(rotate);
        currentDegree = -degree;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void setStarter(){
        String s = StarterPokemonFragment.getStarter();
        switch (s){
            case "Charmander":
                starter.setBackground(getDrawable(R.drawable.charmanderstarter));
                player = new Charmander("Charamander", 6);
                break;
            case "Squirtle":
                starter.setBackground(getDrawable(R.drawable.squirtlestarter));
                player = new Squirtle("Squirtle", 6);
                break;
            case "Bulbasaur":
                starter.setBackground(getDrawable(R.drawable.bulbasaurstarter));
                player = new Bulbasaur("Bulbasaur", 6);
                break;
        }
    }

    public static Pokemon getPlayerPokemon(){
        return player;
    }

    public void setCharacter(){
        String c = BioFragment.getCharacter();
        if(c == "boy"){
            character.setBackground(getDrawable(R.drawable.ash));
        } else {
            character.setBackground(getDrawable(R.drawable.girl));
        }
        grid.setPlayermodel(c);
    }

    // disable back button at this point
    @Override
    public void onBackPressed() {
        // sound stuff
        sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        buttonSound = sp.load(this, R.raw.button, 1);
        wrongSound = sp.load(this, R.raw.cancel, 1);
        sp.play(wrongSound, 1f, 1f, 1, 0, 1f);
        Toast.makeText(this, "Cannot go back at this point", Toast.LENGTH_SHORT).show();

        // super.onBackPressed(); // Comment this super call to avoid calling finish()
    }

    @Override
    public void onDestroy() {

        NotificationManager nmgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nmgr.cancel(NOTIFICATION_ID);
    }


    public void onPrepared() {

        // build a notification object

        // create a notification builder object
        Notification.Builder bldr =
                new Notification.Builder(this).
                        setSmallIcon(R.drawable.icon).
                        setContentTitle("Pokemon").
                        setContentText("Why take a break? Get back to playing Pokemon");

        NotificationManager nmgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // set up code to go back to app with notification action

        Intent intent = new Intent(this, MainGameActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        bldr.setContentIntent(pendingIntent);

        nmgr.notify(NOTIFICATION_ID, bldr.build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settingsMenu:
                //your code
                // EX : call intent if you want to swich to other activity
                return true;
            case R.id.helpMenu:
                //your code
                // display dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.help);
                builder.setMessage(R.string.Info);
                builder.setCancelable(false);
                builder.setNegativeButton(R.string.ok_label,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // dismiss dialog box

                            }
                        });
                // show
                mDialog = builder.show();

                return true;
            case R.id.toggleMusic:
                //your code
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                else{
                    mediaPlayer = MediaPlayer.create(this, R.raw.game);
                    mediaPlayer.setVolume(0.1f, 0.1f);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void lvlup(){
        player.lvlup();
    }
}
