package com.example.harisont.aulelibere;

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


/**
 * Created by tomma on 28/07/2017.
 */

public class FindEntries extends AsyncTask<String, Void, String> {
    private Exception exception;
    ProgressBar progressBar;
    private Context context;
    String API_URL = "http://ec2-34-213-124-6.us-west-2.compute.amazonaws.com/entries_by_id_and_time.php";

    public FindEntries(Context context){
        this.context = context;
    }
    protected void onPreExecute() {
        progressBar = new ProgressBar(context);
        progressBar.setVisibility(View.VISIBLE);
    }
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("room_id", params[0])
                    .appendQueryParameter("start_time", params[1])
                    .appendQueryParameter("end_time", params[2]);
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
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        } catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        /* try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
        } catch (JSONException e) {
            e.printStackTrace();
        } */
        Log.i("INFO", response);
        progressBar.setVisibility(View.GONE);

    }
}
