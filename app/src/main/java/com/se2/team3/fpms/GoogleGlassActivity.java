package com.se2.team3.fpms;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;


public class GoogleGlassActivity extends ActionBarActivity
    implements AircraftMotionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_glass);

        // Enable AircraftMotionEvents
        AircraftMotionManager.getInstance(this).addAircraftMotionUpdates(this);
        Toast.makeText(this.getBaseContext(), "Created GG onCreate", Toast.LENGTH_SHORT).show();
        CheckBox checkBox = (CheckBox) findViewById(R.id.cbxReceive);
        checkBox.setChecked(true);
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
        Toast.makeText(this.getBaseContext(), "onAircraftMotion event", Toast.LENGTH_SHORT).show();
        CheckBox checkBox = (CheckBox) findViewById(R.id.cbxReceive);
        if (checkBox.isChecked())
            checkBox.setChecked(false);
    }
}
