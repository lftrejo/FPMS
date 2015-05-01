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
                        Thread.sleep(5000);  // sleep for 5s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    listener.onLocationChanged(new Location("FnPMS"));
                }
            }
        }).start();
    }

    // Generate gps route data for a semi adjustable route
    public static class FixedPath {
        private LocationListener listener;

        public FixedPath(LocationListener locationListener) {
            listener = locationListener;


            new Thread(new Runnable() {
                private double lat = 31.7676; //UTEP CS building
                private double lng = -106.5020;
                private float latSpeed = 16; // m/s 16 = 72mph, 32=143mph, 64=268mph very rough
                private float longSpeed = 16; // Latitude[+N, -S] longitude[+W, -E]

                protected Location loc = new Location("FnPMS");

                public void run() {
                    //Location loc = new Location("FnPMS");
                    loc.setLatitude(lat);
                    loc.setLongitude(lng);

                    for (int i = 0; i < 120; i++) {
                        try {
                            Thread.sleep(500);  // sleep for 5s
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        nextLoc();
                        listener.onLocationChanged(loc);
                    }

                    // First turn
                    for (int i = 0; i < 120; i++) {
                        try {
                            Thread.sleep(500);  // sleep for 5s
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        nextLoc();
                        setSpeed(32, 16);
                        listener.onLocationChanged(loc);
                    }

                    // Second turn
                    for (int i = 0; i < 120; i++) {
                        try {
                            Thread.sleep(500);  // sleep for 5s
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        nextLoc();
                        setSpeed(-16, 16);
                        listener.onLocationChanged(loc);
                    }
                }

                // Move the current location by lat and lngSpeed
                private void nextLoc() {
                    double newLat = loc.getLatitude() + (latSpeed * 1e-4);
                    double newLong = loc.getLongitude() + (longSpeed * 1e-4);
                    loc.setLatitude(newLat);
                    loc.setLongitude(newLong);
                }

                private void setSpeed(float latSpeed, float longSpeed) {
                    this.latSpeed = latSpeed;
                    this.longSpeed = longSpeed;
                }
            }).start();
        }
    }
}
