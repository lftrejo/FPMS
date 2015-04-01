package com.se2.team3.fpms;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by Scott on 3/30/2015.
 */
public class LocationProvider {
    private LocationListener listener;

    public LocationProvider(LocationListener locationListener) {
        listener = locationListener;

        new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<20; i++) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    listener.onLocationChanged(new Location("FnPMS"));
                }
            }
        }).start();
    }


}
