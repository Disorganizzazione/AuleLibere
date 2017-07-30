package com.example.harisont.aulelibere;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Maps extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        Toast.makeText(getApplicationContext(), "Scorri il dito sullo schermo per passare al piano successivo. Tocca un aula per visualizzare ulteriori informazioni", Toast.LENGTH_LONG).show ();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mappe, menu);
        return true;
    }
    
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch (position) {
                case 0:
                    return new Floor0();
                case 1:
                    return new Floor1();
                case 2:
                    return new Floor2();
                case 3:
                    return new Floor3();
                case 4:
                    return new Floor4();
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
        }                   //numero di frammenti (santoddio)

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PIANO -1";
                case 1:
                    return "PIANO 1";
                case 2:
                    return "PIANO 2";
                case 3:
                    return "PIANO 3";
                case 4:
                    return "PIANO 4";
                case 5:
                    return "PIANO 5";
                case 6:
                    return "PIANO 6";
            }
            return null;
        }
    }
}


