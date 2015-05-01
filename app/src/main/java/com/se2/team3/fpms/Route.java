package com.se2.team3.fpms;

import java.util.ArrayList;
import java.util.Iterator;

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

    // Add newWaypoint in route with shortest time between point
    public void add(Waypoint newWaypoint) {
        Iterator i = waypoints.iterator();
        int index = 0;
        int min = Integer.MAX_VALUE;
        while (i.hasNext()) {
            Waypoint w = (Waypoint) i.next();
            if (w.getRTA().compareTo(newWaypoint.getRTA()) < min)
                index = waypoints.indexOf(w);
        }

        waypoints.add(index, newWaypoint);
    }

}
