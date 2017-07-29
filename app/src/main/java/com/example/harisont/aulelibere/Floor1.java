package com.example.harisont.aulelibere;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by harisont on 17/07/17.
 */

public class Floor1 extends Floor {

    private static boolean flag1=false;

    public Floor1() {
        this.roomn=2;
        this.rooms=new Room[roomn];
        rooms[0]=new Room(4, "A2");
        rooms[1]=new Room(6, "B1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor1, container, false);
        rooms[0].green_view=(rootView.findViewById(R.id.ag1));
        rooms[1].green_view=rootView.findViewById(R.id.bg1);
        rooms[0].red_view=rootView.findViewById(R.id.ar1);
        rooms[1].red_view=rootView.findViewById(R.id.br1);
        ImageView iv = rootView.findViewById(R.id.floor1);
        iv.setId(1001);
        if (iv != null) iv.setOnTouchListener(this);
        return rootView;
    }
}
