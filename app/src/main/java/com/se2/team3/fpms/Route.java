package com.se2.team3.fpms;

/**
 * Created by emart_000 on 4/26/2015.
 */
/**
 * Created by emart_000 on 4/26/2015.
 */


import java.util.LinkedList;
import android.location.Location;



public class Route {
    String routeName;
    String waypoint;


    float location;
    float latitude;
    int trueCourse;
    int distanceTo;
    int speed;
    int RTA;


    public static void listRoute(Waypoint waypoint) {
        LinkedList<Location> Routes = new LinkedList(); // we will have a list and we will list them here
    }
}