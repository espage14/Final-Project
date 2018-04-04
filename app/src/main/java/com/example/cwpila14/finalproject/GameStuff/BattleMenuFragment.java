package com.example.cwpila14.finalproject.GameStuff;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cwpila14.finalproject.R;

/**
 * Created by cwpila14 on 12/13/2016.
 */

public class BattleMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_grid, container, false);
        return root;
    }
}
