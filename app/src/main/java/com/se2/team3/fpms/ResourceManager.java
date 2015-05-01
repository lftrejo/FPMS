package com.se2.team3.fpms;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Scott on 4/30/2015.
 */
public class ResourceManager {
    List<FlightPlan> cachedFlightPlans;
    List<Route> cachedRoutes;
    List<Aircraft> cachedAircrafts;
    List<Airport> cachedAirports;
    Context mContext;

    public ResourceManager(Context context) {
        mContext = context;
        String tag;

        // default FlightPlan
        List<FlightPlan> cachedFlightPlans = new ArrayList<FlightPlan>();
        cachedFlightPlans.add(new FlightPlan("default"));
        tag = "FlightPlan";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedFlightPlans);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }

        // default route
        List<FlightPlan> cachedRoutes = new ArrayList<FlightPlan>();
        cachedFlightPlans.add(new FlightPlan("default"));
        tag = "Route";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedFlightPlans);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }

        List<FlightPlan> cachedAircrafts = new ArrayList<FlightPlan>();
        cachedFlightPlans.add(new FlightPlan("default"));
        tag = "FlightPlan";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedFlightPlans);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }

        List<FlightPlan> cachedAirports = new ArrayList<FlightPlan>();
        cachedFlightPlans.add(new FlightPlan("default"));
        tag = "FlightPlan";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedFlightPlans);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }

    }


    // Call before using resource manager
    public void init() {
        String tag;

        // Load flight plans
        tag = "FlightPlan";
        try {
            cachedFlightPlans = (List<FlightPlan>) InternalStorage.readObject(mContext, tag);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(tag, e.getMessage());
        }

        // Load Routes
        tag = "Route";
        try {
            cachedRoutes = (List<Route>) InternalStorage.readObject(mContext, tag);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(tag, e.getMessage());
        }

        // Load Aircraft
        tag = "Aircraft";
        try {
            cachedAircrafts = (List<Aircraft>) InternalStorage.readObject(mContext, tag);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(tag, e.getMessage());
        }

        // Load Airports
        tag = "Airport";
        try {
            cachedAirports = (List<Airport>) InternalStorage.readObject(mContext, tag);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e(tag, e.getMessage());
        }
    }

    // Necessary to save resource changes
    public void close() {
        String tag;

        // Load flight plans
        tag = "FlightPlan";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedFlightPlans);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }

        // Load Routes
        tag = "Route";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedRoutes);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }

        // Load Aircraft
        tag = "Aircraft";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedAircrafts);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }

        // Load Airports
        tag = "Airport";
        try {
            // Save the list of entries to internal storage
            InternalStorage.writeObject(mContext, tag, cachedAirports);
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }
    }

    public List<FlightPlan> getFlightPlanList() {
        return cachedFlightPlans;
    }

    public void add(FlightPlan fp) {
        cachedFlightPlans.add(fp);
    }

    public void delete(FlightPlan fp) {
        cachedFlightPlans.remove(fp);
    }

    public void add(Route r) {
        cachedRoutes.add(r);
    }

    public void delete(Route r) {
        cachedRoutes.remove(r);
    }

    public void add(Aircraft r) {
        cachedAircrafts.add(r);
    }

    public void delete(Aircraft r) {
        cachedAircrafts.remove(r);
    }

    public void add(Airport r) {
        cachedAirports.add(r);
    }

    public void delete(Airport r) {
        cachedAirports.remove(r);
    }

}

