package com.example.harisont.aulelibere;

/**
 * Created by tomma on 30/07/2017.
 */

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FindWhenIsFree extends AsyncTask<String, Void, Void>{
    private Exception exception;
    ProgressBar progressBar;
    Room room;
    private Context context;
    String API_URL = "http://ec2-34-213-124-6.us-west-2.compute.amazonaws.com/entries_by_id_and_time.php";

    public FindWhenIsFree(Context context, Room room){
        this.context = context;
        this.room = room;
    }
    protected void onPreExecute() {
        progressBar = new ProgressBar(context);
        progressBar.setVisibility(View.VISIBLE);
    }
    protected Void doInBackground(String... params) {
        try {
            // today
            Calendar date = new GregorianCalendar();
            // reset hour, minutes, seconds and millis
            date.set(Calendar.HOUR_OF_DAY, 0);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("room_id", String.valueOf(room.id))
                    .appendQueryParameter("start_time", params[1])
                    .appendQueryParameter("end_time", String.valueOf(date.getTimeInMillis()));  //this should be today at 19:00
            String query = builder.build().getEncodedQuery();

            try {

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(os, "UTF-8"));
                urlConnection.connect();
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                Log.i("response code:" ,String.valueOf(urlConnection.getResponseCode()));
                String response = stringBuilder.toString();
                response = response.substring(response.indexOf("<body>") + 6, response.indexOf("</body>"));
                JSONObject jsonResponse1 = null;
                JSONObject jsonResponse2 = null;
                JSONArray jsonArrayResponse = null;
                try{

                    int i = 1;
                    long difference = 0;
                    long startHole = 0; //when the empty spot between entries start
                    long endHole =  0; //when it ends
                    jsonArrayResponse = (JSONArray) new JSONTokener(response).nextValue();
                    while( jsonArrayResponse.get(i + 1) != null){
                        jsonResponse1 = jsonArrayResponse.getJSONObject(i);
                        jsonResponse2 = jsonArrayResponse.getJSONObject(i + 1);
                        long end_time = Long.valueOf(jsonResponse1.get("end_time").toString());
                        long start_time = Long.valueOf(jsonResponse2.get("start_time").toString());
                        if(end_time != start_time){
                            Date time =new java.util.Date((start_time*1000));
                            room.next_event = time.toString();
                            return null;
                        }
                        i++;
                    }
                    Log.i("start", jsonResponse1.get("start_time").toString());
                    Log.i("end", jsonResponse2.get("end_time").toString());
                    Log.i("time", params[1]);
                    //here it means that it will always be busy for today
                    return null;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            finally{
                urlConnection.disconnect();
            }
        } catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
        return null;
    }

    protected void onPostExecute(String response) {
        if (response != null) Log.i("INFO", response);
        //Log.i("INFO", object.toString());
        progressBar.setVisibility(View.GONE);

    }
}
