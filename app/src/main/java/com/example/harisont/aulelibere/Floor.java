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
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;



/**
 * Created by harisont on 17/07/17.
 */

public abstract class Floor extends Fragment implements OnTouchListener {

    protected int roomn;
    protected Room[] rooms;     //un piano è un array di stanze

    private void showCurrentSituation() {
        /*parte che verrà modularizzata*/
        Date now;
        Date opening;
        Date closing;
        Calendar curr_date = Calendar.getInstance();
        int hour = curr_date.get(Calendar.HOUR_OF_DAY); //non posso credere che HOUR_OF_DAY sia diverso da HOUR
        int minute = curr_date.get(Calendar.MINUTE);
        now = parseDate(hour + ":" + minute);
        updateEntries();
        for (int i=0; i<roomn; i++) {
            opening = parseDate(rooms[i].opening_time);
            closing = parseDate(rooms[i].closing_time);
            if (rooms[i].is_free && opening.before(now) && closing.after(now))
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
        if(pixel==Color.RED) Toast.makeText(getActivity(), "Sono l'aula A del piano "+floor, Toast.LENGTH_SHORT).show();
        else if(pixel==Color.GREEN) Toast.makeText(getActivity(), "Sono l'aula B del piano "+floor, Toast.LENGTH_SHORT).show();
        else if(pixel==Color.BLUE) Toast.makeText(getActivity(), "Sono l'aula C del piano "+floor, Toast.LENGTH_SHORT).show();
        else if(pixel==Color.YELLOW) Toast.makeText(getActivity(), "Sono l'aula D del piano "+floor, Toast.LENGTH_SHORT).show();
        //this.rooms[floor].closing_time=""; //ora si possono fare tutte le cose che si vuole :)
        return false;
    }

    private void updateEntries() {
        String time = String.valueOf(System.currentTimeMillis() / 1000); // milliseconds is seconds * 1000
        for (Room room : rooms) {
            FindEntries query = new FindEntries(getContext(),room);
            query.execute(Integer.toString(room.id), time, time);
        }
    }
}