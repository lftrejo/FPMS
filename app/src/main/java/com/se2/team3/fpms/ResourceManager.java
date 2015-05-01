package com.se2.team3.fpms;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
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

        // Initialize caches
        cachedAirports = new ArrayList<Airport>();

        externalImport();

        /*
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
        */

    }


    // Call before using resource manager
    public void init() {
        String tag;

        /*
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
*/
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

    public List<Airport> getAirportList() {
        return cachedAirports;
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

    // Find all airports partialy or completly matching
    public List<Airport> findAirports(String name) {
        Iterator i = cachedAirports.iterator();
        List<Airport> matchList = new ArrayList<Airport>();

        while(i.hasNext()) {
            Airport a = (Airport) i.next();
            if(a.match(name))
                matchList.add((Airport) i.next());
        }

        return matchList;
    }

    public void externalImport() {
        // Load external airports
        parseAirports(read(mContext.getResources().openRawResource(R.raw.us_airports)));
    }

    private ArrayList read(InputStream inputStream){
        ArrayList resultList = new ArrayList();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (row[3].substring(1,row[3].length()-1).equals("United States"))
                    resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }

    // parse an external csv file containing airports
    private void parseAirports(List list) {
        String[] item;// = (String[])scoreList.remove(0);

        int i = 0;
        Airport a;
        while (i < list.size()){
            a = new Airport();
            cachedAirports.add(a);
            item = (String[])list.get(i);

            // Name
            a.setName(item[4] + " - " + item[1]);

            // Lat Long
            Location loc = new Location("FPMS");
            loc.setLatitude(Double.parseDouble(item[6]));
            loc.setLatitude(Double.parseDouble(item[7]));
            a.setLoc(loc);

            // runway closures
            a.setRunwayClosures(item[5]);

            // operating hours
            a.setOperatingHours(Math.abs(Integer.parseInt(item[9])));

            i++;
        }
    }

}

