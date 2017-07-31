package com.example.harisont.aulelibere;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.os.Bundle;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by harisont on 17/07/17.
 */

public abstract class Floor extends Fragment implements OnTouchListener {

    protected int roomn;
    protected Room[] rooms;     //un piano è un array di stanze
    long timestamp = 0;

    private void showCurrentSituation() {
        /*parte che verrà modularizzata*/
        Date now;
        Date opening;
        Date closing;
        Calendar curr_date = Calendar.getInstance();
        int hour=curr_date.get(Calendar.HOUR_OF_DAY); //non posso credere che HOUR_OF_DAY sia diverso da HOUR
        int minute=curr_date.get(Calendar.MINUTE);
        int weekday=curr_date.get(Calendar.DAY_OF_WEEK);
        now = parseDate(hour + ":" + minute);
        if((timestamp - System.currentTimeMillis()/1000) > 3600 || timestamp == 0) {
            updateEntries();
            timestamp = System.currentTimeMillis()/1000;
        }
        for (int i=0; i<roomn; i++) {
            opening = parseDate(rooms[i].opening_time);
            closing = parseDate(rooms[i].closing_time);
            if (rooms[i].is_free && opening.before(now) && closing.after(now) && weekday!=1 && weekday!=7)
                rooms[i].green_view.setVisibility(View.VISIBLE);
            else
                rooms[i].red_view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Creazione frammento");
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState==null) {
            showCurrentSituation();
        }
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

   public boolean onTouch (View v, MotionEvent event) {

        final int evX = (int) event.getX();
        final int evY = (int) event.getY();
        ImageView vm=null;
        switch(v.getId()) {
            case 10:  // they go from 10 to \4 because from 0 to 4 is not allowed
                vm=getView().findViewById(R.id.mask0);
                break;
            case 11:
                vm=getView().findViewById(R.id.mask1);
                break;
            case 12:
                vm=getView().findViewById(R.id.mask2);
                break;
            case 13:
                vm=getView().findViewById(R.id.mask3);
                break;
            case 14:
                vm=getView().findViewById(R.id.mask4);
                break;
            default: break;
        }
        int floor=v.getId()%10;  //look comment line 80
        Bitmap bitmap=((BitmapDrawable)vm.getDrawable()).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(bitmap, v.getWidth(), v.getHeight(), false);
        int pixel=bitmapResized.getPixel(evX, evY);
       FindWhenIsBusy findWhenisBusy;
       if(pixel==Color.RED){
            if(rooms[0].is_free){
                findWhenisBusy = new FindWhenIsBusy(getContext(), rooms[0]);
                try {
                    findWhenisBusy.execute(String.valueOf(System.currentTimeMillis())).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            showInfo(0);
        }
        else if(pixel==Color.GREEN) {
           if(rooms[0].is_free){
               findWhenisBusy = new FindWhenIsBusy(getContext(), rooms[1]);
               try {
                   findWhenisBusy.execute(String.valueOf(System.currentTimeMillis())).get();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (ExecutionException e) {
                   e.printStackTrace();
               }
           }
            showInfo(1);
        }
        else if(pixel==Color.BLUE){
           if(rooms[0].is_free){
               findWhenisBusy = new FindWhenIsBusy(getContext(), rooms[2]);
               try {
                   findWhenisBusy.execute(String.valueOf(System.currentTimeMillis())).get();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (ExecutionException e) {
                   e.printStackTrace();
               }
           }
           showInfo(2);
        }
        else if(pixel==Color.YELLOW) { //special cases
            if (floor==3) {
                showInfo(3);
            }
            else showInfo(2);
        }
        return false;
    }
    private void showInfo(int r) {
        Date now;
        Calendar curr_date=Calendar.getInstance();
        int weekday=curr_date.get(Calendar.DAY_OF_WEEK);
        int time=curr_date.get(Calendar.HOUR_OF_DAY);
        if(weekday!=1 && weekday!=7 && time<=19 && time>=8) { //se l'università è aperta
            Toast.makeText(getActivity(), "Orario di apertura: " + this.rooms[r].opening_time + " - " +this.rooms[r].closing_time, Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getActivity(), "L'università è chiusa.", Toast.LENGTH_LONG).show();

    }

    /*private void showInfo(int r) { //r indica la stanza, a seconda del colore sulla maschera
        {
            Date now;
            Calendar curr_date=Calendar.getInstance();
            int weekday=curr_date.get(Calendar.DAY_OF_WEEK);
            int time=curr_date.get(Calendar.HOUR_OF_DAY);
            if(weekday!=1 && weekday!=7 && time<=19 && time>=8) { //se l'università è aperta
                if (this.rooms[r].is_free) {
                    if (this.rooms[r].next_event != null) {
                        System.out.println(this.rooms[r].next_event);
                        Toast.makeText(getActivity(), "Quest'aula sarà disponibile fino alle " + this.rooms[r].next_event, Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(getActivity(), "Quest'aula rimarrà aperta fino alle " + this.rooms[r].closing_time, Toast.LENGTH_LONG).show();
                } else {
                    if (this.rooms[r].next_event != null)
                        Toast.makeText(getActivity(), "Quest'aula sarà occupata fino alle " + this.rooms[r].next_event, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), "Quest'aula rimarrà chiusa fino alle " + this.rooms[r].closing_time, Toast.LENGTH_LONG).show();
                }
            }
             else Toast.makeText(getActivity(), "L'università è chiusa.", Toast.LENGTH_LONG).show();
        }
    }*/
    private void updateEntries() {
        String time = String.valueOf(System.currentTimeMillis() / 1000); // milliseconds is seconds * 1000
        for (Room room : rooms) {
            FindEntries query = new FindEntries(getContext(),room);
            try {
                query.execute(time).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}