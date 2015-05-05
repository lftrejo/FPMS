package com.se2.team3.fpms;

import android.app.Activity;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

// Class that manages the motion and wind updates, it creates a new thread that
// will create events when the GPS location has changed. It will notify the
// main thread when location changes so that it may be handled accordingly
// and updated on the map
public class AircraftMotionManager implements LocationListener, WindListener
{
    private static AircraftMotionManager gInstance;
    private static Activity gActivity;
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
    // Hacky way to get the UI thread Activity needed to execute from this thread to UI thread
    public static synchronized AircraftMotionManager getInstance(Activity activity) {
        if (activity != null)
            gActivity = activity;
        if (gInstance == null)
            gInstance = new AircraftMotionManager();
        return gInstance;
    }
    // get Singleton
    public static synchronized AircraftMotionManager getInstance() {
        if (gInstance == null)
            gInstance = new AircraftMotionManager();
        return gInstance;
    }

    // Initialize location provider when this created
    // run in this thread not UI thread
    void initLocationProvider()
    {
        // create basic test location provider
        //new LocationProvider(this);
        new LocationProvider.FixedPath(this);
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

    // When WindProvider senses a change in wind direction/speed
    public void onWindChanged(float windDirection, float speed) {

    }

    // Container to hold event state. Events run on the UI thread
    abstract class DispatchedEvent implements Runnable {
        protected AircraftMotionListener listener;
        protected Location location;
        protected float trueAirspeed;
        protected float trueCourse;

        public DispatchedEvent(AircraftMotionListener listener,
                               Location location, float trueAirspeed, float trueCourse) {
            this.listener = listener;
            this.location = location;
            this.trueAirspeed = trueAirspeed;
            this.trueCourse = trueCourse;
        }
    }

    // Send location change event to all listeners, received from location provider thread
    private void dispatch(Location location) {
        // Figure out if a location change should be sent as an event
        // If you haven't moved dont send an event and other sanity checks

        // for now just see if this event sys works
        for (AircraftMotionListener listener : listeners) {
            gActivity.runOnUiThread(new DispatchedEvent(listener, location, trueAirspeed(), trueCourse()) {
                public void run() {
                    this.listener.onAircraftMotion(this.location, this.trueAirspeed, this.trueCourse);
                }
            });
        }
    }

    // received wind change from location provider thread
    private void dispatch(float windSpeed, float windDirection) {
        // compute true heading and course
        // sanity check the result
        // send events if need be, don't send if nothing changed

        for (AircraftMotionListener listener : listeners) {
//            Log.i("AMM", "dispatch(windSpeed, windDirection) listener:" + listener.toString());
            //Toast.makeText(gActivity.getBaseContext(), "Flights are loading", Toast.LENGTH_SHORT);
            gActivity.runOnUiThread(new DispatchedEvent(listener, location, trueAirspeed(), trueCourse()) {
                public void run() {
                    this.listener.onAircraftMotion(this.location, this.trueAirspeed, this.trueCourse);
                }
            });
        }
    }

    // Aircraft speed through the airmass not ground speed
    // Computed from location changes, and wind speed/heading
    private float trueAirspeed() {
        //todo: compute trueAirspeed
        return groundSpeed;
    }

    // Heading of aircraft motion along ground
    private float trueCourse() {
        return heading;
    }

}

