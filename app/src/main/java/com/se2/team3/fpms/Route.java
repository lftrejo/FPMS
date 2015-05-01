package com.se2.team3.fpms;

import java.util.ArrayList;

/**
 * Created by Scott on 4/30/2015.
 */
public class Route
    extends Resource{
    ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();

    public Route() {
        // Default route ELP to DFW
    }

    public Route(Waypoint departure, Waypoint arrival) {
        waypoints.add(departure);
        waypoints.add(arrival);
    }
}
