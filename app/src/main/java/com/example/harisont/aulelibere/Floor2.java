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
        this.freerooms=new boolean[roomn];
        this.green=new ImageView[roomn];
        this.red=new ImageView[roomn];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor2, container, false);
        green[0]=rootView.findViewById(R.id.ag2);
        green[1]=rootView.findViewById(R.id.bg2);
        green[2]=rootView.findViewById(R.id.cg2);
        green[3]=rootView.findViewById(R.id.dg2);
        red[0]=rootView.findViewById(R.id.ar2);
        red[1]=rootView.findViewById(R.id.br2);
        red[2]=rootView.findViewById(R.id.cr2);
        red[3]=rootView.findViewById(R.id.dr2);
        showCurrentSituation();
        return rootView;
    }
}