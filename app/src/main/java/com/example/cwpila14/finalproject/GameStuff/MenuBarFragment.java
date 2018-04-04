package com.example.cwpila14.finalproject.GameStuff;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 11/4/2016.
 */

public class MenuBarFragment extends Fragment {

    // empty class for the menu bar

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu_bar_fragment, container, false);

        return rootView;
    }
}
