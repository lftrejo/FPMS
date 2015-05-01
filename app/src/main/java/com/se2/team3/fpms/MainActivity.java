package com.se2.team3.fpms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MainActivity extends ActionBarActivity {
    private static GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        (Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_LONG)).show();
        Airports.readAirports(getResources().openRawResource(R.raw.airports));
        //setUpMapIfNeeded();
        //MapsActivity.planeInit();

//        Temporarily removed to work without fragments, will be restored in the short future
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }

        Button loadFlight = (Button) findViewById(R.id.loadFlight);
        loadFlight.setOnClickListener(manageFlight);
        Button startFlightButton = (Button) findViewById(R.id.startFlight);
        startFlightButton.setOnClickListener(startFlight);
        Button newFlightButton = (Button) findViewById(R.id.newFlight);
        newFlightButton.setOnClickListener(createFlight);
    }
    public void addAirports(){
        //Log.d("addAirports", "");
        List scoreList = Airports.getAirports();
        String[] item;// = (String[])scoreList.remove(0);


        int i =0;
        while (i<scoreList.size()){
            item = (String[])scoreList.get(i);
            gMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(item[6]),Double.parseDouble( item[7])))
                    .title(item[1]).snippet(item[2]+" , "+item[3]));
            i++;
        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (gMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            gMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (gMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.80725, -106.377583), 8));
        gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        addAirports();
    }


    private OnClickListener startFlight = new OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Glass.class);
            startActivity(intent);
        };
    };

    private OnClickListener manageFlight = new OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, manageFlightPlans.class);
            startActivity(intent);
        };
    };

    private OnClickListener createFlight = new OnClickListener(){
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
