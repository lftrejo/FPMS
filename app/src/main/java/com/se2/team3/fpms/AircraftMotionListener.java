package com.se2.team3.fpms;

import android.location.Location;

// Interface that enables classes to receive GPS coordinates updates
public interface AircraftMotionListener {
    void onAircraftMotion(Location location, float trueAirspeed, float trueCourse);
}
