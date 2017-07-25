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

public class Floor2 extends Floor {

    public Floor2() {
        this.roomn=4;
        this.rooms=new Room[roomn];
        rooms[0]=new Room(4, "A2");
        rooms[1]=new Room(100, "B2");   //assente nel db
        rooms[2]=new Room(8, "C2");
        rooms[3]=new Room(100, "D2");   //assente nel db
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor2, container, false);
        rooms[0].green_view=rootView.findViewById(R.id.ag2);
        rooms[1].green_view=rootView.findViewById(R.id.bg2);
        rooms[2].green_view=rootView.findViewById(R.id.cg2);
        rooms[3].green_view=rootView.findViewById(R.id.dg2);
        rooms[0].red_view=rootView.findViewById(R.id.ar2);
        rooms[1].red_view=rootView.findViewById(R.id.br2);
        rooms[2].red_view=rootView.findViewById(R.id.cr2);
        rooms[3].red_view=rootView.findViewById(R.id.dr2);
        showCurrentSituation();
        return rootView;
    }
}