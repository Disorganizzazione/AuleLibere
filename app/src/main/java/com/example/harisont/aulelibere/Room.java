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
    //aggiungere orario di apertura-chiusura per incrociare i dati con quelli del db. verde=non prenotata AND aperta

    public Room(int id, String name) {
        this.id=id;
        this.name=name;
        this.is_free=true;
        this.opening_time="9:00";
        this.closing_time="22:00";
    }
}
