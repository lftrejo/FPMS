package com.se2.team3.fpms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

// Class to display the flight plans in the knowledge base
// here the users can select, edit or delete a flight plan
public class manageFlightPlans extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_flight_plans);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button loadFlight = (Button) findViewById(R.id.buttonEdit);
        loadFlight.setOnClickListener(editFlight);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    private View.OnClickListener editFlight = new View.OnClickListener() {
        public void onClick(View v) {
            Context context = getApplicationContext();
            Intent intent = new Intent(context, flightPlanDetails.class);
            startActivity(intent);
        };
    };


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
}
