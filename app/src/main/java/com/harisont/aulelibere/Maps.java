package com.harisont.aulelibere;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Maps extends AppCompatActivity implements View.OnTouchListener {
    //variabili globali a caso per testare showCurrentSituation
    public boolean a3=true;
    public boolean b3=false;
    public boolean c3=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        showCurrentSituation();

        ImageView iv = (ImageView) findViewById (R.id.floor3);
        if (iv != null) {
            iv.setOnTouchListener (this);
        }

        toast ("Tocca le aule per vedere cosa c'è in programma.");
    }

    public void showCurrentSituation() {
        ImageView freea3=(ImageView)findViewById(R.id.ag3);
        ImageView busya3=(ImageView)findViewById(R.id.ar3);
        if(a3==true) {
            freea3.setVisibility(View.VISIBLE);
        }
        else busya3.setVisibility(View.VISIBLE);

        ImageView freeb3=(ImageView)findViewById(R.id.bg3);
        ImageView busyb3=(ImageView)findViewById(R.id.br3);
        if(b3==true) {
            freeb3.setVisibility(View.VISIBLE);
        }
        else busyb3.setVisibility(View.VISIBLE);

        ImageView freec3=(ImageView)findViewById(R.id.cg3);
        ImageView busyc3=(ImageView)findViewById(R.id.cr3);
        if(c3==true) {
            freec3.setVisibility(View.VISIBLE);
        }
        else busyc3.setVisibility(View.VISIBLE);
    }

    public boolean onTouch (View v, MotionEvent ev)
    {
        boolean handledHere = false;

        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        int nextImage = -1;			// resource id of the next image to display

        ImageView imageView = (ImageView) v.findViewById (R.id.floor3);
        if (imageView == null) return false;

        Integer tagNum = (Integer) imageView.getTag ();
        int currentResource = (tagNum == null) ? R.drawable.tre : tagNum.intValue ();

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                if (currentResource == R.drawable.tre) {
                    nextImage = R.drawable.trem;
                    handledHere = true;
       /*
       } else if (currentResource != R.drawable.p2_ship_default) {
         nextImage = R.drawable.p2_ship_default;
         handledHere = true;
       */
                } else handledHere = true;
                break;

            case MotionEvent.ACTION_UP :
                // On the UP, we do the click action.
                // The hidden image (mask3) has three different hotspots on it.
                // The colors are red, blue, and yellow.
                // Use mask3 to determine which region the user touched.
                int touchColor = getHotspotColor (R.id.mask3, evX, evY);

                // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
                // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
                // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
                // varying pixel density.
                ColorTool ct = new ColorTool ();
                int tolerance = 25;
                nextImage = R.drawable.tre;
                if (ct.closeMatch (Color.RED, touchColor, tolerance)) nextImage = R.drawable.trem;
                else if (ct.closeMatch (Color.BLUE, touchColor, tolerance)) nextImage = R.drawable.trem;
                else if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)) nextImage = R.drawable.trem;
                else if (ct.closeMatch (Color.WHITE, touchColor, tolerance)) nextImage = R.drawable.trem;

                // If the next image is the same as the last image, go back to the default.
                // toast ("Current image: " + currentResource + " next: " + nextImage);
                if (currentResource == nextImage) {
                    nextImage = R.drawable.tre;
                }
                handledHere = true;
                break;

            default:
                handledHere = false;
        } // end switch

        if (handledHere) {

            if (nextImage > 0) {
                imageView.setImageResource (nextImage);
                imageView.setTag (nextImage);
            }
        }
        return handledHere;
    }

    /*@Override protected void onResume() {
        super.onResume();

        View v  = findViewById (R.id.wglxy_bar);
        if (v != null) {
            Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            //anim1.setAnimationListener (new StartActivityAfterAnimation (i));
            v.startAnimation (anim1);
        }
    }*/

    /**
     * Handle a click on the Wglxy views at the bottom.
     *
     */

    public void onClickWglxy (View v) {
        Intent viewIntent = new Intent ("android.intent.action.VIEW",
                Uri.parse("http://double-star.appspot.com/blahti/ds-download.html"));
        startActivity(viewIntent);

    }


/**
 */
// More methods

    /**
     * Get the color from the hotspot image at point x-y.
     *
     */

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        if (img == null) {
            Log.d ("ImageAreasActivity", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }

    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
    } // end toast


}
