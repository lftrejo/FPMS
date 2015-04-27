package com.se2.team3.fpms;

/**
 * Created by Scott on 3/30/2015.
 */
public interface WindListener {
    // callback sent when WindProvider senses change in local wind direction or speed
    void onWindChanged(float windDirection, float speed);
}
