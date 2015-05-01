package com.se2.team3.fpms;

import android.graphics.Color;
import android.location.GpsStatus;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements AircraftMotionListener, GoogleMap.OnMarkerClickListener{

    private static GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static MarkerOptions plane;
    List<LatLng> points = new ArrayList<>();
    static Marker planeMarker;
    static PolylineOptions lineTemp = new PolylineOptions().color(Color.CYAN).geodesic(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
        AircraftMotionManager.getInstance().addAircraftMotionUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                mMap.setOnMarkerClickListener(this
//                        new GoogleMap.OnMarkerClickListener() {
//                                                  @Override public boolean onMarkerClick(Marker marker) {
//                                                      addPoint(marker.getPosition());
//                                                      drawLine();
//                                                      return true;
//                                                  }
//                                              }
                );
            }
        }


    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(31.80725, -106.377583), 8));
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        planeInit();
        addAirports();
    }

    public boolean onMarkerClick(Marker marker){
        if (!points.contains(marker.getPosition())){
            addPoint(marker.getPosition());
            drawLine();
            (Toast.makeText(getApplicationContext(), "added point", Toast.LENGTH_LONG)).show();
        }else{
            mMap.clear();
            addAirports();
            points.remove(marker.getPosition());
            drawLine();
            (Toast.makeText(getApplicationContext(), "deleted point", Toast.LENGTH_LONG)).show();
        }
        //Log.d("-------------------",points.toString());
        return true;
    }

    public void drawLine(){
        Iterator<LatLng> pointsTemp = points.iterator();
        while (pointsTemp.hasNext()){
            lineTemp.add(pointsTemp.next());
        }
        mMap.addPolyline(lineTemp);
    }

    public void addPoint(LatLng point){
        points.add(point);
    }

    public void addAirports(){
        List scoreList = Airports.getAirports();
        String[] item;// = (String[])scoreList.remove(0);


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

    public static void planeChangePosition(double lat, double lon){
        //Log.d("plane position changed", "plane position changed");

        plane.position(new LatLng(lat, lon));
        if (planeMarker!=null){
            plane.rotation(getBearing(planeMarker.getPosition(), plane.getPosition()));
            planeMarker.remove();
        }
        planeMarker = mMap.addMarker(plane);
    }

    public static float getBearing(double lat1, double lng1, double lat2, double lng2){
        Location loc = new Location("loc 1");
        Location loc2 = new Location("loc 2");
        loc.setLatitude(lat1);
        loc.setLongitude(lng1);
        loc2.setLatitude(lat2);
        loc2.setLongitude(lng2);
        return loc.bearingTo(loc2);
    }

    public static float getBearing(LatLng pos1, LatLng pos2){
        return getBearing(pos1.latitude, pos1.longitude,pos2.latitude,pos2.longitude);
    }

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
