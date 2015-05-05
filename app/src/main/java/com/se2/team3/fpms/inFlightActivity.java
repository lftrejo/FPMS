package com.se2.team3.fpms;

import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Class to display the map with inflight information. It will display the
// route of airports or waypoints with a line connecting them. Also will
// display the aircraft on the current GPS coordinates
public class inFlightActivity extends ActionBarActivity  implements AircraftMotionListener{

    private static GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static MarkerOptions plane;
    static Marker planeMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_flight);
        setUpMapIfNeeded();
        AircraftMotionManager.getInstance().addAircraftMotionUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    //
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map3))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }


    }

    // To be called only once, will setup the map, plane marker and add airports
    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.80725, -106.377583), 8));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        planeInit();
        addAirports();
    }


    // Draw a line between all the points in the current route in order
    public void drawLine(){
        Iterator<LatLng> pointsTemp = MapsActivity.points.iterator();
        PolylineOptions lineTemp = new PolylineOptions().color(Color.CYAN).geodesic(true);
        LatLng t;
        while (pointsTemp.hasNext()){
            t = pointsTemp.next();
            lineTemp.add(t);
            mMap.addMarker(new MarkerOptions().position(t));
        }
        mMap.addPolyline(lineTemp);
    }


    // Add airports
    public void addAirports(){
        List scoreList = Airports.getAirports();
        String[] item;
        int i =0;
        while (i<scoreList.size()){
            item = (String[])scoreList.get(i);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(item[6]),Double.parseDouble( item[7])))
                    .title(item[1]).snippet(item[2]+" , "+item[3]));
            i++;
        }
    }

    public void clearAirports(){
        mMap.clear();
    }

    // Method called when there is a change in the plane position, it clears and redraws
    // the plane marker on the map
    public static void planeChangePosition(double lat, double lon){
        plane.position(new LatLng(lat, lon));
        if (planeMarker!=null){
            plane.rotation(getBearing(planeMarker.getPosition(), plane.getPosition()));
            planeMarker.remove();
        }
        planeMarker = mMap.addMarker(plane);
    }

    // method to calculate the bearing between two coordinates
    public static float getBearing(double lat1, double lng1, double lat2, double lng2){
        Location loc = new Location("loc 1");
        Location loc2 = new Location("loc 2");
        loc.setLatitude(lat1);
        loc.setLongitude(lng1);
        loc2.setLatitude(lat2);
        loc2.setLongitude(lng2);
        return loc.bearingTo(loc2);
    }

    // method to calculate the bearing between two coordinates
    public static float getBearing(LatLng pos1, LatLng pos2){
        return getBearing(pos1.latitude, pos1.longitude,pos2.latitude,pos2.longitude);
    }

    // Initialize the plane marker
    public static void planeInit(){
        plane = (new MarkerOptions())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.airplanesmall))
                .flat(true);
    }

    @Override
    public void onAircraftMotion(Location location, float trueAirspeed, float trueCourse) {
        //Log.d("onAircraftMotion called","onAircraftMotion called");
        planeChangePosition(location.getLatitude(), location.getLongitude());
    }
}
