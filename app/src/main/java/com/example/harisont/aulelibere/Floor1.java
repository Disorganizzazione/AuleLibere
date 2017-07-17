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

    public Floor1() {
        this.roomn=2;
        this.freerooms=new boolean[roomn];
        this.green=new ImageView[roomn];
        this.red=new ImageView[roomn];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.floor1, container, false);
        freerooms[1]=true; //solo perché siamo quelli della B1
        green[0]=(rootView.findViewById(R.id.ag1));
        green[1]=rootView.findViewById(R.id.bg1);
        red[0]=rootView.findViewById(R.id.ar1);
        red[1]=rootView.findViewById(R.id.br1);
        showCurrentSituation();
        return rootView;
    }
}
