package com.example.harisont.aulelibere;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by harisont on 17/07/17.
 */

public class Floormin1 extends Floor {

    public Floormin1() {
        this.roomn=1;
        this.rooms=new Room[roomn];
        rooms[0]=new Room(3, "A0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floormin1, container, false);
        rooms[0].green_view=rootView.findViewById(R.id.agm1);
        rooms[0].red_view=rootView.findViewById(R.id.arm1);
        showCurrentSituation();
        return rootView;
    }
}