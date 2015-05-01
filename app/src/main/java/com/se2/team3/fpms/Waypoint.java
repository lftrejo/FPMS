package com.se2.team3.fpms;

import android.location.Location;

import java.util.Calendar;

/**
 * Created by Scott on 4/30/2015.
 */
public class Waypoint
    extends Resource{
    String name;
    Calendar RTA;
    Location loc;

    public Waypoint() {
        this.name = "default";
        Location testLoc = new Location("FPMS");
        double testLat = 31.7676;  // UTEP CS building
        double testLong = -106.5020;
        testLoc.setLatitude(testLat);
        testLoc.setLongitude(testLong);
        loc = testLoc;
    }

    public String getName() {
        return name;
    }

    public Calendar getRTA() {
        return RTA;
    }

    public Location getLoc() {
        return loc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRTA(Calendar RTA) {
        this.RTA = RTA;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
}
