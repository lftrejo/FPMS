package com.se2.team3.fpms;

import android.graphics.Bitmap;

/**
 * Created by Scott on 4/30/2015.
 */
public class Airport
    extends Waypoint{
    Bitmap airportMap;

    public Bitmap getAirportMap() {
        return airportMap;
    }

    public void setAirportMap(Bitmap airportMap) {
        this.airportMap = airportMap;
    }
}
