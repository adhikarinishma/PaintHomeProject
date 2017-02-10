package com.example.nishmaadhikari.painthomeproject;

/**
 * Created by nishmaadhikari on 2/7/17.
 */

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TabHost;
import android.content.Intent;
import android.widget.Toast;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;
import android.support.design.widget.TabLayout;
import com.google.android.gms.maps.MapView;
import android.widget.TabHost.TabContentFactory;
import android.support.v4.view.ViewPager;



public class Media extends FragmentActivity {


    private static final String MAP_TAB_TAG = "Map";
    private MapView mapView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media);


        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.locationicon));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.calcicon));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //   @Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    //     getMenuInflater().inflate(R.menu.menu_main, menu);
    //    return true;
    // }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private int mNumOfTabs;

        private PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return (new Map_location());
                case 1:
                    return (new CalcHint());
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

}
