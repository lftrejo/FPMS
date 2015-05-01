package com.se2.team3.fpms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 5/1/2015.
 */
public class HomeMapActivity
        extends ActionBarActivity
{
    private ResourceManager mResourceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_map);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //(Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_LONG)).show();
        //Airports.readAirports(getResources().openRawResource(R.raw.airports));
        //MapsActivity.planeInit();

//        Temporarily removed to work without fragments, will be restored in the short future
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }

        // Set event handler for bottom buttons
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(switchToGlass);
//        loadFlight.setText("another");
        //Button settings = (Button) findViewById(R.id.startFlight);
        //settings.setOnClickListener(switchToPreferences);

        Button btnCenter = (Button) findViewById(R.id.btnCenter);
        btnCenter.setOnClickListener(switchToNewFlight);

        Button btnRight = (Button) findViewById(R.id.btnRight);
        btnRight.setOnClickListener(switchToNewFlight);

        // Initialize resource manager
        mResourceManager = new ResourceManager(this);
        mResourceManager.init();
    }

    protected void onDestroy() {
        super.onDestroy();
        mResourceManager.close();
    }

    private View.OnClickListener switchToNewFlight = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            //Toast toast = Toast.makeText(context, "Button Pressed", Toast.LENGTH_LONG);
            //toast.show();
            Intent intent = new Intent(context, createFlightPlanActivity.class);
            startActivity(intent);
        };
    };
    private View.OnClickListener switchToGlass = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Glass.class);
            startActivity(intent);
        };
    };

    private View.OnClickListener manageFlight = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, manageFlightPlans.class);
            startActivity(intent);
        };
    };

    private View.OnClickListener createFlight = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Glass.class);
            startActivity(intent);
        };
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_resources:
                startActivity(new Intent(getApplicationContext(), manageResources.class));
                return true;
            case R.id.action_preferences:
                startActivity(new Intent(getApplicationContext(), preferencesActivity.class));
                return true;
            case R.id.action_maps:
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                return true;
            case R.id.action_exit:
                new AlertDialog.Builder(this).setTitle("FPMS").setMessage("\n  Created By Team 3\n  Software Engineering II").show();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /*
    public void switchToSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}