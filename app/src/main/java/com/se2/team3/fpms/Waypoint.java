package com.se2.team3.fpms;

import android.location.Location;
import java.util.Locale;

/**
 * Created by emart_000 on 4/26/2015.
 */
public class Waypoint {
    String waypointName;
    Location l = new Location("current");
    double alt;
    double longitude;
    public String getWaypointName() {
        return waypointName;
    }
    public void setWaypoint(String waypointName) {
        this.waypointName = waypointName;
    }
    public void getAlt() {
        alt = l.getAltitude();
    }
    public void setAlt(int alt) {
        this.alt = alt;
    }

    public void getLongitude() {
        longitude = l.getLongitude();
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setL(Location l) {
        this.l = l;
    }
    public void addWaypoint(Waypoint w1, Waypoint w2)
    {
    }
}
