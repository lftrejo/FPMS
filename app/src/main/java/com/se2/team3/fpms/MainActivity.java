package com.se2.team3.fpms;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    EditText txtName, txtAircraftID, txtAircraftModel,txtAircraftColor,
            txtMinimumSpeed, txtNormalSpeed, txtMaximumSpeed,
            txtMinimumAltitude, txtNormalAltitude, txtMaximumAltitude,
            txtFuelConsumption, txtFuelConsumptionRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_aircraft);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        txtName = (EditText) findViewById(R.id.textName);
        txtAircraftID = (EditText) findViewById(R.id.textAircraftID);
        txtAircraftModel = (EditText) findViewById(R.id.textAircraftModel);
        txtAircraftColor = (EditText) findViewById(R.id.textAircraftColor);
        txtMinimumSpeed = (EditText) findViewById(R.id.textMinimumSpeed);
        txtNormalSpeed = (EditText) findViewById(R.id.textNormalSpeed);
        txtMaximumSpeed = (EditText) findViewById(R.id.textMaximumSpeed);
        txtMinimumAltitude = (EditText) findViewById(R.id.textMinimumAltitude);
        txtNormalAltitude = (EditText) findViewById(R.id.textNormalAltitude);
        txtMaximumAltitude = (EditText) findViewById(R.id.textMaximumAltitude);
        txtFuelConsumption = (EditText) findViewById(R.id.textFuelConsumption);
        txtFuelConsumptionRate = (EditText) findViewById(R.id.textFuelConsumptionRate);

        final Button btnHome = (Button) findViewById(R.id.buttonHome);
        final Button btnSave = (Button) findViewById(R.id.buttonSave);
        Button btnBack = (Button) findViewById(R.id.buttonBack);

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });


        txtAircraftID.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });


        txtAircraftModel.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtAircraftColor.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtMinimumSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtNormalSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtMaximumSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtMinimumAltitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtNormalAltitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtMaximumAltitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtFuelConsumption.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });

        txtFuelConsumptionRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSave.setEnabled(!s.equals(""));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
