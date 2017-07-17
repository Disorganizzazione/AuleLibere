package com.example.harisont.aulelibere;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by harisont on 17/07/17.
 */

public abstract class Floor extends Fragment {

    public int roomn;
    public boolean[] freerooms;     //un piano è un array di stanze
    public ImageView[] green;
    public ImageView[] red;

    public void showCurrentSituation() {
        for (int i=0; i<roomn; i++) {
            if (freerooms[i]==true)
                green[i].setVisibility(View.VISIBLE);
            else
                red[i].setVisibility(View.VISIBLE);
        }
    }

}