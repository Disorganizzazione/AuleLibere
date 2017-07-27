package com.example.harisont.aulelibere;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by harisont on 25/07/17.
 */

public class Floor0 extends Floor {

    public Floor0() {
        this.roomn=3;
        this.rooms=new Room[roomn];
        rooms[0]=new Room(3, "A0");
        rooms[1]=new Room(2, "LabVerde", "09:00", "18:00");  //controllare orari laboratori
        rooms[2]=new Room(1, "LabGiallo", "09:00", "18:00");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor0, container, false);
        rooms[0].green_view=rootView.findViewById(R.id.ag0);
        rooms[1].green_view=rootView.findViewById(R.id.gg0);
        rooms[2].green_view=rootView.findViewById(R.id.vg0);
        rooms[0].red_view=rootView.findViewById(R.id.ar0);
        rooms[1].red_view=rootView.findViewById(R.id.gr0);
        rooms[2].red_view=rootView.findViewById(R.id.vr0);
        showCurrentSituation();
        return rootView;
    }
}
