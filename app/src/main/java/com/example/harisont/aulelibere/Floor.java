package com.example.harisont.aulelibere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by harisont on 17/07/17.
 */

public class Floor extends Fragment {

    public int roomn;
    public Room[] rooms;     //un piano è un array di stanze

    public void showCurrentSituation() {
        for (int i=0; i<roomn; i++) {
            if (rooms[i].is_free==true)
                rooms[i].green_view.setVisibility(View.VISIBLE);
            else
                rooms[i].red_view.setVisibility(View.VISIBLE);
        }
    }
}