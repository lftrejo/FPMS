package com.se2.team3.fpms;

import android.graphics.Bitmap;

/**
 * Created by Scott on 4/30/2015.
 */
public class Airport
    extends Waypoint{
    Bitmap airportMap;
    String runwayClosures;
    int operatingHours;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getAirportMap() {
        return airportMap;
    }

    public void setAirportMap(Bitmap airportMap) {
        this.airportMap = airportMap;
    }

    public String getRunwayClosures() {
        return runwayClosures;
    }

    public void setRunwayClosures(String runwayClosures) {
        this.runwayClosures = runwayClosures;
    }

    public int getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(int operatingHours) {
        this.operatingHours = operatingHours;
    }
}
