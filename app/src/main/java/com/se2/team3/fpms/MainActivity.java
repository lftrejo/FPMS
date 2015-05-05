package com.se2.team3.fpms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceActivity;
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

// Class to display the home screen, which includes a map displaying
// all airports in the United states.
public class MainActivity extends ActionBarActivity {
    private static GoogleMap gMap;

    @Override
    // Initialize all objects to be displayed on the main screen
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Airports.readAirports(getResources().openRawResource(R.raw.airports));
        setUpMapIfNeeded();
        Button loadFlight = (Button) findViewById(R.id.loadFlight);
        loadFlight.setOnClickListener(manageFlight);
        Button startFlightButton = (Button) findViewById(R.id.startFlight);
        startFlightButton.setOnClickListener(startFlight);
        Button newFlightButton = (Button) findViewById(R.id.newFlight);
        newFlightButton.setOnClickListener(createFlight);
    }

    // Add the airports to the map with extra information
    public void addAirports(){
        List scoreList = Airports.getAirports();
        String[] item;
        int i=0;
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
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.80725, -106.377583), 6));
        gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        addAirports();
    }


    // Display a menu for creating a new flight, which includes shortest,
    // fastest or custom path
    private OnClickListener startFlight = new OnClickListener(){
        public void onClick(View v){
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Select type of flight to create");
            builder.setItems(new CharSequence[]
                            {"Shortest", "Fastest", "Custom"},
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            switch (which) {
                                case 0:
                                    Toast.makeText(getApplicationContext(), "Sorry not Implemented yet", Toast.LENGTH_LONG).show();
                                    break;
                                case 1:
                                    Toast.makeText(getApplicationContext(), "Sorry not Implemented yet", Toast.LENGTH_LONG).show();
                                    break;
                                case 2:
                                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
//                                    Toast.makeText(getApplicationContext(), "clicked 3", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    });
            builder.create().show();
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
            Intent intent = new Intent(context, preferencesActivity.class);
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
                startActivity(new Intent(getApplicationContext(), inFlightActivity.class));
                return true;
            case R.id.glassscreen:
                startActivity(new Intent(getApplicationContext(), Glass.class));
                return true;
            case R.id.action_exit:
                new AlertDialog.Builder(this).setTitle("FPMS").setMessage("\n  Created By Team 3\n  Software Engineering II").show();
                return false;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
