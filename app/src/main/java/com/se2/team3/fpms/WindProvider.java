package com.se2.team3.fpms;

import android.location.Location;

// Class that will create a new thread that will send events in case
// the wind changes
public class WindProvider {
    private WindListener listener;

    public WindProvider(WindListener windListener) {
        listener = windListener;

        new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<20; i++) {
                    try {
                        Thread.sleep(5000);  // sleep for 5s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    listener.onWindChanged(0, 0);
                }
            }
        }).start();
    }
}
