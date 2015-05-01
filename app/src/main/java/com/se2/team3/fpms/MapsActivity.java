package com.se2.team3.fpms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Iterator;
import java.util.List;

public class MapsActivity
        extends ActionBarActivity
        implements AircraftMotionListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static MarkerOptions plane;
    private ResourceManager mResourceManager;
    private MapState mapState = MapState.HOME;

    private enum MapState {HOME, EDIT, FLIGHT}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_map);

        // Listen to AircraftMotionEvents
        AircraftMotionManager.getInstance(this).addAircraftMotionUpdates(this);

        // Initialize resource manager
        mResourceManager = new ResourceManager(this);
        mResourceManager.init();

        setUpMapIfNeeded();



        // Set event handler for bottom buttons
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnCenter = (Button) findViewById(R.id.btnCenter);
        Button btnRight = (Button) findViewById(R.id.btnRight);

        switch (mapState) {
        case HOME:
            btnRight.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), preferencesActivity.class));
                }
            });
        break;
        case EDIT:
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    protected void onDestroy() {
        super.onDestroy();
        mResourceManager.close();
        //Log.d("Destroy", "onDestroy");
    }

    /**
     *
     * Setup Menu
     */
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

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.home_map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.80725, -106.377583), 8));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.addPolyline(new PolylineOptions().color(Color.CYAN).geodesic(true)
                .add(new LatLng(31.80725, -106.377583))
                .add(new LatLng(29.533694, -98.469778))
                .add(new LatLng(29.645419, -95.278889)));



        planeInit();
        mMap.addMarker(plane);
        addAirports();
    }

    public void addAirports(){
        if (mResourceManager == null)
            Log.d("FPMS", "mResourceManager NULL");
        Iterator i = mResourceManager.getAirportList().iterator();
        while (i.hasNext()) {
            Airport a = (Airport) i.next();
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(a.getLoc().getLatitude(),
                            a.getLoc().getLongitude()))
                    .title(a.getName()));

        }
    }

    public void clearAirports(){
        mMap.clear();
    }

    public static void planeChangePosition(double lat, double lon){
        plane.position(new LatLng(lat,lon));
    }
    public static void planeInit(){
        Location loc = new Location("loc 1");
        Location loc2 = new Location("loc 1");

        loc.setLatitude(31.80725);
        loc.setLongitude(-106.377583);
        loc2.setLatitude(29.533694);
        loc2.setLongitude(-98.469778);

        plane = (new MarkerOptions()).icon(BitmapDescriptorFactory.fromResource(R.drawable.airplanesmall))
                //.anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(31.80725, -106.377583))
                .rotation(loc.bearingTo(loc2))
                .flat(true);
    }

    @Override
    public void onAircraftMotion(Location location, float trueAirspeed, float trueCourse) {
        MapsActivity.planeChangePosition(location.getLatitude(), location.getLatitude());
    }
}
