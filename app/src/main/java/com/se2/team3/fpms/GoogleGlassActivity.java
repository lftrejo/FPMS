package com.se2.team3.fpms;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// Class that updates the simulated google glass screens with the generated
// GPS updates. calculating the time of arrival from the coordinates and speed
public class GoogleGlassActivity extends ActionBarActivity
    implements AircraftMotionListener {

    private CheckBox checkBox;
    private TextView txtLat;
    private TextView txtLong;
    private TextView txtBearing;
    private TextView txtRTA;
    private TextView txtETA;

    // test location to head towards
    private Location airportLoc;
    private double airportLat = 31.833;
    private double airportLong = -106.38;

    private Date RTA = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_glass);

        // Enable AircraftMotionEvents
        AircraftMotionManager.getInstance(this).addAircraftMotionUpdates(this);

        checkBox = (CheckBox) findViewById(R.id.cbxReceive);
        txtLat = (TextView) findViewById(R.id.txtLat);
        txtLong = (TextView) findViewById(R.id.txtLong);
        txtBearing = (TextView) findViewById(R.id.txtBearing);
        txtRTA = (TextView) findViewById(R.id.txtRTA);
        txtETA = (TextView) findViewById(R.id.txtETA);

        // init test destination for rta and eta
        airportLoc = new Location("FnPMS");
        airportLoc.setLatitude(airportLat);
        airportLoc.setLongitude(airportLong);
    }
/*
    protected void onResume() {
        Toast.makeText(this.getBaseContext(), "Created GG onResume", Toast.LENGTH_SHORT).show();
        CheckBox checkBox = (CheckBox) findViewById(R.id.cbxReceive);
        checkBox.setChecked(true);
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_google_glass, menu);
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

//        Log.i("GG", "onAircraftMotion(loc, spd, course)");
        //if (checkBox.isChecked())
        checkBox.setChecked(true);
        txtLat.setText(Double.toString(location.getLatitude()));
        txtLong.setText(Double.toString(location.getLongitude()));

        txtBearing.setText(Float.toString(location.bearingTo(airportLoc)));

        if (RTA == null) {
            Calendar c = getETA(location, airportLoc, 32);
            Calendar now = Calendar.getInstance();
            now.add(Calendar.HOUR, c.get(Calendar.HOUR));
            now.add(Calendar.MINUTE, c.get(Calendar.MINUTE));
            now.add(Calendar.SECOND, c.get(Calendar.SECOND));
            RTA = now.getTime();
        }

        SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
        txtRTA.setText(HHmmss.format(RTA));
        txtETA.setText(HHmmss.format((getETA(location, airportLoc, 32)).getTime()));
    }

    // dest waypoint
    // speed of aircraft
    public Calendar getETA(Location current, Location dest, float speedMps) {
        float m = current.distanceTo(dest);
        int seconds = Math.round(m / speedMps);
        return new GregorianCalendar(0,0,0,0,0,seconds);
    }

}
