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
        this.freerooms=new boolean[roomn];
        this.green=new ImageView[roomn];
        this.red=new ImageView[roomn];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor4, container, false);
        green[0]=rootView.findViewById(R.id.ag4);
        green[1]=rootView.findViewById(R.id.bg4);
        red[0]=rootView.findViewById(R.id.ar4);
        red[1]=rootView.findViewById(R.id.br4);
        showCurrentSituation();
        return rootView;
    }
}