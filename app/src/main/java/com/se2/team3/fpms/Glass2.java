package com.se2.team3.fpms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Glass2 extends ActionBarActivity
    implements AircraftMotionListener {

    private double fuel = 255;

    private TextView txtHeading;
    private TextView txtAltitude;
    private TextView txtLatLng;

    // test location to head towards
    private Location airportLoc;
    private double airportLat = 31.833;
    private double airportLong = -106.38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glass2);

        txtHeading = (TextView) findViewById(R.id.txtHeading);
        txtAltitude = (TextView) findViewById(R.id.txtAltitude);
        txtLatLng = (TextView) findViewById(R.id.textView9);

        AircraftMotionManager.getInstance(this).addAircraftMotionUpdates(this);
        airportLoc = new Location("FnPMS");
        airportLoc.setLatitude(airportLat);
        airportLoc.setLongitude(airportLong);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button buttonLeft = (Button) findViewById(R.id.buttonleft2);
        buttonLeft.setOnClickListener(goLeft);
        Button buttonRight = (Button) findViewById(R.id.buttonright2);
        buttonRight.setOnClickListener(goRight);


    }

/*
    protected void onStop() {
        AircraftMotionManager.getInstance(this).removeAircraftMotionUpdates(this);
    }
    */
    private View.OnClickListener goLeft = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Glass.class);
            startActivity(intent);
        };
    };


    private View.OnClickListener goRight = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Glass3.class);
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
            case R.id.action_exit:
                new AlertDialog.Builder(this).setTitle("FPMS").setMessage("\n  Created By Team 3\n  Software Engineering II").show();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onAircraftMotion(Location location, float trueAirspeed, float trueCourse) {
        //Toast.makeText(this.getBaseContext(), "onAircraftMotion event", Toast.LENGTH_SHORT).show();

//        Log.i("GG", "onAircraftMotion(loc, spd, course)");
        //if (checkBox.isChecked())
        String latLng = Double.toString(location.getLatitude()) + " " +
                Double.toString(location.getLongitude());
        txtLatLng.setText(latLng);
        txtHeading.setText(Float.toString(location.bearingTo(airportLoc)));
        txtAltitude.setText(Double.toString(Math.round(fuel = fuel - 0.1)));
    }

}
