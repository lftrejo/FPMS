package com.se2.team3.fpms;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Glass3 extends ActionBarActivity
    implements AircraftMotionListener {

    private double fuel = 255;

    private TextView txtHeading;
    private TextView txtAltitude;
    private TextView txtLatLng;
    private TextView txtETA;
    private TextView txtRTA;

    // test location to head towards
    private Location airportLoc;
    private double airportLat = 31.833;
    private double airportLong = -106.38;

    private Date RTA = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glass3);

        txtHeading = (TextView) findViewById(R.id.txtHeading);
        txtAltitude = (TextView) findViewById(R.id.txtAltitude);
        txtLatLng = (TextView) findViewById(R.id.txtLatLng);
        txtETA = (TextView) findViewById(R.id.txtETA);
        txtRTA = (TextView) findViewById(R.id.txtRTA);
        AircraftMotionManager.getInstance(this).addAircraftMotionUpdates(this);
        airportLoc = new Location("FnPMS");
        airportLoc.setLatitude(airportLat);
        airportLoc.setLongitude(airportLong);


    }
/*
    protected void onStop() {
        AircraftMotionManager.getInstance(this).removeAircraftMotionUpdates(this);
    }
    */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_glass3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAircraftMotion(Location location, float trueAirspeed, float trueCourse) {
        //Toast.makeText(this.getBaseContext(), "onAircraftMotion event", Toast.LENGTH_SHORT).show();

        //Log.i("GG", "onAircraftMotion(loc, spd, course)");
        //if (checkBox.isChecked())
        String latLng = Double.toString(location.getLatitude()) + " " +
                Double.toString(location.getLongitude());
        txtLatLng.setText(latLng);
        txtHeading.setText(Float.toString(location.bearingTo(airportLoc)));
        txtAltitude.setText("1");

        if (RTA == null) {
            Calendar c = getETA(location, airportLoc, 32);
            Calendar now = Calendar.getInstance();
            now.add(Calendar.HOUR, c.get(Calendar.HOUR));
            now.add(Calendar.MINUTE, c.get(Calendar.MINUTE));
            now.add(Calendar.SECOND, c.get(Calendar.SECOND));
            RTA = now.getTime();
        }

        SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
        txtRTA.setText("RTA: " + HHmmss.format(RTA));
        txtETA.setText("ETA: " + HHmmss.format((getETA(location, airportLoc, 32)).getTime()));
        txtAltitude.setText(Double.toString(Math.round(fuel = fuel - 0.1)));
    }

    // dest waypoint
    // speed of aircraft
    public Calendar getETA(Location current, Location dest, float speedMps) {
        float m = current.distanceTo(dest);
        int seconds = Math.round(m / speedMps);
        return new GregorianCalendar(0,0,0,0,0,seconds);
    }
}
