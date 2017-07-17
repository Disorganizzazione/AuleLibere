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
        this.freerooms=new boolean[roomn];
        this.green=new ImageView[roomn];
        this.red=new ImageView[roomn];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor3, container, false);
        green[0]=rootView.findViewById(R.id.ag3);
        green[1]=rootView.findViewById(R.id.bg3);
        green[2]=rootView.findViewById(R.id.cg3);
        red[0]=rootView.findViewById(R.id.ar3);
        red[1]=rootView.findViewById(R.id.br3);
        red[2]=rootView.findViewById(R.id.cr3);
        showCurrentSituation();
        return rootView;
    }
}