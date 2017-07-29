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

public class Floor3 extends Floor {

    public Floor3() {
        this.roomn=3;
        this.rooms=new Room[roomn];
        rooms[0]=new Room(5, "A3");
        rooms[1]=new Room(7, "B3");
        rooms[2]=new Room(13, "C3");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor3, container, false);
        rooms[0].green_view=rootView.findViewById(R.id.ag3);
        rooms[1].green_view=rootView.findViewById(R.id.bg3);
        rooms[2].green_view=rootView.findViewById(R.id.cg3);
        rooms[0].red_view=rootView.findViewById(R.id.ar3);
        rooms[1].red_view=rootView.findViewById(R.id.br3);
        rooms[2].red_view=rootView.findViewById(R.id.cr3);
        ImageView iv = rootView.findViewById(R.id.floor3);
        iv.setId(13);
        if (iv != null) iv.setOnTouchListener(this);
        return rootView;
    }
}