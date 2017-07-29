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

public class Floor4 extends Floor {

    public Floor4() {
        this.roomn=2;
        this.rooms=new Room[roomn];
        rooms[0]=new Room(102, "AulaStudio");   //non presente nel db
        rooms[1]=new Room(103, "Biblioteca", "08:30", "18:00");   //non presente nel db, controllare orario biblio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor4, container, false);
        rooms[0].green_view=rootView.findViewById(R.id.ag4);
        rooms[1].green_view=rootView.findViewById(R.id.bg4);
        rooms[0].red_view=rootView.findViewById(R.id.ar4);
        rooms[1].red_view=rootView.findViewById(R.id.br4);
        ImageView iv = rootView.findViewById(R.id.floor4);
        iv.setId(14);
        if (iv != null) iv.setOnTouchListener(this);
        return rootView;
    }
}