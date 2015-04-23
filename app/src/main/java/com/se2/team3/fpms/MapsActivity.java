package com.se2.team3.fpms;

import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static MarkerOptions plane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
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
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
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
//        InputStream inputStream = getResources().openRawResource(R.raw.airports);
//        Airports csvFile = new Airports(inputStream);
        List scoreList = Airports.getAirports();
        String[] item;// = (String[])scoreList.remove(0);


        int i =0;
        while (i<scoreList.size()){
            item = (String[])scoreList.get(i);
            //if (item[3].substring(1,item[3].length()-1).equals("United States"))
            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(item[6]),Double.parseDouble( item[7]))).title(item[1]).snippet(item[2]+" , "+item[3]));
            i++;
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
}
