package com.se2.team3.fpms;

import android.location.Location;

// Interface that enables classes to receive GPS coordinates updates
public interface LocationListener {
    // callback triggered when location provider signals location of aircraft change
    void onLocationChanged(Location location);
}
