package com.se2.team3.fpms;

import java.util.ArrayList;

/**
 * Created by Scott on 3/30/2015.
 */
public class AircraftMotionManager
{
    private static AircraftMotionManager instance;
    private ArrayList<AircraftMotionListener> listeners = new ArrayList<AircraftMotionListener>();

    private AircraftMotionManager() {
        new Thread(new Runnable() {
            public void run() {
                // Initialize
                initLocationProvider();
                initWindProvider();
            }
        }).start();

    // get Singleton
    public static synchronized AircraftMotionManager getInstance() {
        if (instance == null) {
            instance = new AircraftMotionManager();

        }

        return instance;
    }

    // Initialize location provider when this created
    // run in this thread not UI thread
    void initLocationProvider()
    {

    }

    // Initialize location provider when this created
    // run in this thread not UI thread
    void initWindProvider()
    {

    }





    }
}
