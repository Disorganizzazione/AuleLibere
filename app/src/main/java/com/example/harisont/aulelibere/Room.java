package com.example.harisont.aulelibere;

import android.widget.ImageView;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;

/**
 * Created by harisont on 26/07/17.
 */

public class Room {
    public int id;
    protected String name;
    protected boolean is_free;
    protected ImageView green_view;
    protected ImageView red_view;
    protected String opening_time;
    protected String closing_time;
    protected String next_event;

    protected Room(int id, String name) {
        this.id=id;
        this.name=name;
        this.is_free=true;
        this.opening_time="08:00";
        this.closing_time="19:00";
        this.next_event=null;
    }

    //costruttore da usare per le aule che hanno un orario di apertura/chiusura proprio: biblioteca, laboratori...
    protected Room(int id, String name, String opening_time, String closing_time) {
        this.id=id;
        this.name=name;
        this.is_free=true;
        this.opening_time=opening_time;
        this.closing_time=closing_time;
        this.next_event=null;
    }
}
