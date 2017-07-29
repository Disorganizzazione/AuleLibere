package com.example.harisont.aulelibere;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;

/**
 * Created by harisont on 17/07/17.
 */

public class Floor extends Fragment {

    public int roomn;
    public Room[] rooms;     //un piano è un array di stanze
    public void showCurrentSituation() {
        Date now;
        Date opening;
        Date closing;
        Calendar curr_date = Calendar.getInstance();
        int hour = curr_date.get(Calendar.HOUR_OF_DAY); //non posso credere che HOUR_OF_DAY sia diverso da HOUR
        int minute = curr_date.get(Calendar.MINUTE);
        now = parseDate(hour + ":" + minute);
        System.out.println(now);
        for (int i=0; i<roomn; i++) {
            opening = parseDate(rooms[i].opening_time);
            closing = parseDate(rooms[i].closing_time);
            if (rooms[i].is_free && opening.before(now) && closing.after(now))
                rooms[i].green_view.setVisibility(View.VISIBLE);
            //else
            //rooms[i].green_view.setImageResource(R.drawable.duear);
        }
        updateEntries();

    }

    private Date parseDate(String date) {
        final String inputFormat = "HH:mm";     //dichiaro il formato dell'ora, HH (maiuscolo) indica il formato 24h
        SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.ITALY);
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date();
        }

    }

    void updateEntries(){
        String time = String.valueOf(System.currentTimeMillis()/1000); // milliseconds is seconds * 1000
        for(Room room : rooms) {
            FindEntries query = new FindEntries(getContext());
            query.execute(Integer.toString(room.id), time, time);
        }
    }
}
