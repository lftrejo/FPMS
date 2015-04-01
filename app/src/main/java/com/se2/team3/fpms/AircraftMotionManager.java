package com.se2.team3.fpms;

import android.app.Activity;
import android.location.Location;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Scott on 3/30/2015.
 */
public class AircraftMotionManager
    implements LocationListener, WindListener
{
    private static AircraftMotionManager instance;
    private static Activity activity;
    private static LocationProvider locProvider;
    private static WindProvider windProvider;
    private ArrayList<AircraftMotionListener> listeners = new ArrayList<AircraftMotionListener>();

    private Location location; // current location
    private float groundSpeed; // aircraft groundSpeed, derived from location changes
    private float heading;  // aircraft heading
    private float windSpeed; // current wind speed
    private float windDirection; // current heading wind is blowing from
    private float trueAirspeed;
    private float trueCourse;

    private AircraftMotionManager() {
        new Thread(new Runnable() {
            public void run() {
                // Initialize
                initLocationProvider();
                initWindProvider();
            }
        }).start();
    }

    // get Singleton
    public static synchronized AircraftMotionManager getInstance() {
        if (instance == null)
            instance = new AircraftMotionManager();
        return instance;
    }

    // Initialize location provider when this created
    // run in this thread not UI thread
    void initLocationProvider()
    {
        //todo: create loc provider
    }

    // Initialize location provider when this created
    // run in this thread not UI thread
    void initWindProvider()
    {
        //todo: create wind provider
    }

    // Add class that will receive AircraftMotion events
    public void addAircraftMotionUpdates(AircraftMotionListener aml) {
        listeners.add(aml);
    }

    // Stop sending events to a class currently receiving events
    public void removeAircraftMotionUpdates(AircraftMotionListener aml) {
        listeners.remove(aml);
    }

    // When Location provider senses a change in aircraft location
    public void onLocationChanged(Location location) {
            dispatch(location);
    }

    // When WindProvider senses a change in wind direction or speed affecting aircraft
    public void onWindChanged(float windDirection, float speed) {

    }

    // received location change from location provider thread
    private void dispatch(Location location) {
        // Figure out if a location change should be sent as an event
        // If you haven't moved dont send an event and other sanity checks

        // for now just see if this event sys works
        for (AircraftMotionListener listener : listeners)
            listener.onAircraftMotion(location, trueAirspeed(), trueCourse());
    }

    // received wind change from location provider thread
    private void dispatch(float windSpeed, float windDirection) {
        // compute true heading and course
        // sanity check the result
        // send events if need be, don't send if nothing changed

        for (AircraftMotionListener listener : listeners)
            listener.onAircraftMotion(location, trueAirspeed(), trueCourse());
    }

    // Aircraft speed through the airmass not ground speed
    // Computed from location changes, and wind speed/heading
    private float trueAirspeed() {
        //todo: compute trueAirspeed
        return groundSpeed;
    }

    // Heading of aircraft
    private float trueCourse() {
        return heading;
    }

}

