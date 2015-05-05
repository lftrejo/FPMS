package com.se2.team3.fpms;


// Interface to be used by classes that listen to wind change events
public interface WindListener {
    // callback sent when WindProvider senses change in local wind direction or speed
    void onWindChanged(float windDirection, float speed);
}
