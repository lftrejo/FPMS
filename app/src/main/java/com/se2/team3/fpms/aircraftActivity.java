package com.se2.team3.fpms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class aircraftActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aircraft);
        Button newFlightButton = (Button) findViewById(R.id.aircraftAddButton);
        newFlightButton.setOnClickListener(addAircraft);
        Button newFlightButton2 = (Button) findViewById(R.id.aircraftEditButton);
        newFlightButton2.setOnClickListener(viewAircraft);
    }

    private View.OnClickListener addAircraft = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, addAircraftActivity.class);
            startActivity(intent);
        };
    };
    private View.OnClickListener viewAircraft = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, aircraftDetails.class);
            startActivity(intent);
        };
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aircraft, menu);
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
}
