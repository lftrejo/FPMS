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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;

// Class to display the preferences activity
public class preferencesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        CheckBox altCheck = (CheckBox) findViewById(R.id.altitudeCheckbox);
        altCheck.setOnCheckedChangeListener(cl);
        ((CheckBox) findViewById(R.id.checkBox3)).setOnCheckedChangeListener(cl2);
    }

    private OnCheckedChangeListener cl = new OnCheckedChangeListener(){
        public void onCheckedChanged(CompoundButton b, boolean boo){
                ((EditText) findViewById(R.id.editText)).setEnabled(boo);
        };
    };
    private OnCheckedChangeListener cl2 = new OnCheckedChangeListener(){
        public void onCheckedChanged(CompoundButton b, boolean boo){
            ((ListView) findViewById(R.id.listViewAiports)).setEnabled(boo);
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
}
