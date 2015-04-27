package com.se2.team3.fpms;

import android.location.Location;

/**
 * Created by Scott on 3/30/2015.
 */
public interface LocationListener {
    // callback triggered when location provider signals location of aircraft change
    void onLocationChanged(Location location);
}
