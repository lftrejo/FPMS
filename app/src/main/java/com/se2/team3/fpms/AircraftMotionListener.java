package com.se2.team3.fpms;

import android.location.Location;

/**
 * Created by Scott on 3/30/2015.
 */
public interface AircraftMotionListener {
    void onAircraftMotionChanged(Location location, float trueAirspeed, float trueCourse);
}
