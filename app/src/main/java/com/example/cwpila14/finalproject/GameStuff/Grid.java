package com.example.cwpila14.finalproject.GameStuff;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cwpila14.finalproject.Battle.WildBattleActivity;
import com.example.cwpila14.finalproject.R;

public class Grid extends Fragment {

    static private int tileIds[];
    private boolean usedIds[];
    private int currentTileId;
    private int currentTileNumber;
    private Drawable player;
    private static final double pi = Math.PI;
    private Resources r;
    private AlertDialog mDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_grid, container, false);



        tileIds = new int[110];
        usedIds = new boolean[110];
        r = getResources();
        //get the ids for all the tiles
        for(int i = 0; i < 110; i++){
            int id = r.getIdentifier("Tile" + i, "id", getActivity().getPackageName());
            tileIds[i] = id;
            usedIds[i] = false;
            if(i == 59){
                currentTileId = id;
                currentTileNumber = i;
            }
        }
        return root;
    }

    // moves the current character
    public void move_player(Location current_loc, Location last_loc){
        ImageView currentTile = (ImageView) getActivity().findViewById(currentTileId);

        //get the direction
        String result = direction(last_loc, current_loc);
        switch(result){
            case "N" :
                //move the user up, make sure they aren't at the wall
                if((currentTileNumber% 10) > 0) {
                    currentTile.setVisibility(View.GONE);
                    currentTileNumber--;
                    currentTileId = tileIds[currentTileNumber];
                    usedIds[currentTileNumber] = true;
                    flipTile(currentTileId);
                    break;
                }

            case "S":
                //move_player the user down, make sure they aren't at the wall
                if(((currentTileNumber-9)% 10) > 0) {
                    currentTile.setVisibility(View.GONE);
                    currentTileNumber++;
                    currentTileId = tileIds[currentTileNumber];
                    usedIds[currentTileNumber] = true;
                    flipTile(currentTileId);
                    break;
                }

            case "W":
                //move the user left, make sure they aren't at the wall
                if(currentTileNumber > 9) {
                    currentTile.setVisibility(View.GONE);
                    currentTileNumber = currentTileNumber - 10;
                    currentTileId = tileIds[currentTileNumber];
                    usedIds[currentTileNumber] = true;
                    flipTile(currentTileId);
                    break;
                }

            case "E":
                //move the user right, make sure they aren't at the wall
                if(currentTileNumber < 100) {
                    currentTile.setVisibility(View.GONE);
                    currentTileNumber = currentTileNumber + 10;
                    currentTileId = tileIds[currentTileNumber];
                    usedIds[currentTileNumber] = true;
                    flipTile(currentTileId);
                    break;
                }
            case "NW" :
                //move the user up, make sure they aren't at the wall
                if(((currentTileNumber% 10) > 0) && (currentTileNumber > 9)) {
                    currentTile.setVisibility(View.GONE);
                    currentTileNumber = currentTileNumber - 11;
                    currentTileId = tileIds[currentTileNumber];
                    usedIds[currentTileNumber] = true;
                    flipTile(currentTileId);
                    break;
                }

            case "NE":
                //move_player the user down, make sure they aren't at the wall
                if(((currentTileNumber% 10) > 0) && (currentTileNumber < 100)) {
                    currentTile.setVisibility(View.GONE);
                    currentTileNumber = currentTileNumber + 9;
                    currentTileId = tileIds[currentTileNumber];
                    usedIds[currentTileNumber] = true;
                    flipTile(currentTileId);
                    break;
                }

            case "SW":
                //move the user left, make sure they aren't at the wall
                if((((currentTileNumber-9)% 10) > 0) && (currentTileNumber > 9)) {
                    currentTile.setVisibility(View.GONE);
                    currentTileNumber = currentTileNumber - 9;
                    currentTileId = tileIds[currentTileNumber];
                    usedIds[currentTileNumber] = true;
                    flipTile(currentTileId);
                    break;
                }

            case "SE":
                //move the user right, make sure they aren't at the wall
                if((((currentTileNumber-9)% 10) > 0) && (currentTileNumber < 100)) {
                    currentTile.setVisibility(View.GONE);
                    usedIds[currentTileNumber] = true;
                    currentTileNumber = currentTileNumber + 11;
                    currentTileId = tileIds[currentTileNumber];
                    flipTile(currentTileId);
                    break;
                }
        }
    }

    // flips the tile and does the action that follows it
    //TODO currently just flips it, change to battle, item, etc...
    public void flipTile(int TileID){
        ImageView currentTile = (ImageView) getActivity().findViewById(TileID);
        currentTile.setBackground(player);

        if(MainGameActivity.getPlayerPokemon().getHP() > 0) {
            Intent intent = new Intent(getActivity(), WildBattleActivity.class);
            getActivity().startActivity(intent);
        } else {
            // display dialogue
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.heal);
            builder.setMessage(MainGameActivity.getPlayerPokemon().getName() + " is too weak! You need to heal first!");
            builder.setCancelable(false);
            builder.setNegativeButton(R.string.ok_label,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // dismiss dialog box.

                        }
                    });
            // show
            mDialog = builder.show();
        }

        if(usedIds[currentTileNumber] == true) {
            currentTile.setVisibility(View.VISIBLE);
        } else{
            //battle
        }
    }

    //Code below is from: http://stackoverflow.com/questions/8502795/get-direction-compass-with-two-longitude-latitude-points
    //Edited to fit the needs of the app
    //Takes a starting and ending location
    //Figures out what direction the user travelled to get from the starting loc to the ending one
    //Returns 'up' (N), 'down' (S), 'left' (W), and 'right' (E)
    public String direction(Location start, Location end){

        double startX = start.getLatitude();
        double endX = end.getLatitude();
        double startY = start.getLongitude();
        double endY = end.getLongitude();

        //get the difference in radians
        //double radians = Math.atan2((endY - startY),(endX - startX));

        //turn it into degress (like a compass)
        //double degree = Math.round((radians * (180/Math.PI)));
        float degree = start.bearingTo(end);
        degree = degree/45;

        if(degree < 0){
            degree =+ 8;
        }

        switch((int)degree){
            case 0:
                //right
                return "N";
            case 1:
                //up
                return "NE";
            case 2:
                //left
                return "E";
            case 3:
                //down
                return "SE";
            case 4:
                //right
                return "S";
            case 5:
                return "SW";
            case 6:
                return "W";
            case 7:
                return "NW";
            case 8:
                return "N";
        }
        //something went wrong
        Toast woops = Toast.makeText(this.getContext(), "" + degree, Toast.LENGTH_LONG);
        woops.show();
        return "";
    }

    public String dir(Location start, Location end){
        double margin = pi/90;
        double o = start.getLatitude() - end.getLatitude();
        double a = start.getLongitude() - end.getLongitude();
        double angle = Math.atan2(o,a);
        if (angle > -margin && angle < margin) {
            return "E";
        } else if (angle > pi/2 - margin && angle < pi/2 + margin) {
            return "N";
        } else if (angle > pi - margin && angle < -pi + margin) {
            return "W";
        } else if (angle > -pi/2 - margin && angle < -pi/2 + margin) {
            return "S";
        }
        if (angle > 0 && angle < pi/2) {
            return "NE";
        } else if (angle > pi/2 && angle < pi) {
            return "NW";
        } else if (angle > -pi/2 && angle < 0) {
            return "SE";
        } else {
            return "SW";
        }
    }

    public void setPlayermodel(String s){
        if (s == "boy"){
            player = r.getDrawable(R.drawable.playermodel_guy,null);
        } else {
            player = r.getDrawable(R.drawable.playermodel_girl,null);
        }
    }

}