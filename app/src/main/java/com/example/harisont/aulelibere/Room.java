package com.example.harisont.aulelibere;

import android.widget.ImageView;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;

/**
 * Created by harisont on 26/07/17.
 */

public class Room {
    int id;
    String name;
    boolean is_free;
    ImageView green_view;
    ImageView red_view;
    String opening_time;
    String closing_time;

    public Room(int id, String name) {
        this.id=id;
        this.name=name;
        this.is_free=true;
        this.opening_time="08:00";
        this.closing_time="19:00";
    }

    //costruttore da usare per le aule che hanno un orario di apertura/chiusura proprio: biblioteca, laboratori...
    public Room(int id, String name, String opening_time, String closing_time) {
        this.id=id;
        this.name=name;
        this.is_free=true;
        this.opening_time=opening_time;
        this.closing_time=closing_time;
    }
}
