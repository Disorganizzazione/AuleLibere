package com.example.harisont.aulelibere;

import android.widget.ImageView;

/**
 * Created by harisont on 26/07/17.
 */

public class Room {
    int id;
    String name;
    boolean is_free;
    ImageView green_view;
    ImageView red_view;
    //aggiungere orario di apertura-chiusura per incrociare i dati con quelli del db. verde=non prenotata AND aperta

    public Room(int id, String name) {
        this.id=id;
        this.name=name;
        this.is_free=true;
    }
}
