package com.se2.team3.fpms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class manageResources extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_resources);

        Button loadFlight = (Button) findViewById(R.id.aircraftButton);
        loadFlight.setOnClickListener(manageAircraft);
        Button startFlightButton = (Button) findViewById(R.id.routesButton);
        startFlightButton.setOnClickListener(manageRoutes);
        Button newFlightButton = (Button) findViewById(R.id.waypointsButton);
        newFlightButton.setOnClickListener(manageWaypoints);
    }

    private View.OnClickListener manageRoutes = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, routeActivity.class);
            startActivity(intent);
        };
    };

    private View.OnClickListener manageAircraft = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, aircraftActivity.class);
            startActivity(intent);
        };
    };

    private View.OnClickListener manageWaypoints = new View.OnClickListener(){
        public void onClick(View v){
            //Context context = getApplicationContext();
            //Intent intent = new Intent(context, Glass.class);
            //startActivity(intent);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
