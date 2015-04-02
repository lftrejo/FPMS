package com.se2.team3.fpms;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import android.os.Handler;


public class Glass extends ActionBarActivity {
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glass);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button buttonLeft = (Button) findViewById(R.id.buttonleft);
        buttonLeft.setOnClickListener(goLeft);
        Button buttonRight = (Button) findViewById(R.id.buttonright);
        buttonRight.setOnClickListener(goRight);

        speedLoop();
    }

    private View.OnClickListener goLeft = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Glass3.class);
            startActivity(intent);
        };
    };

    private View.OnClickListener goRight = new View.OnClickListener(){
        public void onClick(View v){
            Context context = getApplicationContext();
            Intent intent = new Intent(context, Glass2.class);
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


    public void speedLoop(){
        final TextView t = (TextView) findViewById(R.id.textView4);
        t.setText("0");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                t.setText(Integer.toString(new Integer(t.getText().toString())+1));
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
    }


    /*   Test code
    int delay = 1000; // delay for 1 sec.
    int period = 10000; // repeat every 10 sec.
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask()
    {
        public void run()
        {
            displayData();  // display the data
        }
    }, delay, period);

     */
}
