package com.se2.team3.fpms;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 4/23/2015.
 */
public class Airports {

    static InputStream inputStream;
    static boolean initialized = false;
    static ArrayList resultList = new ArrayList();

    public Airports(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public static void readAirports(InputStream i){
        inputStream = i;
        getAirports();
    }

    public static ArrayList getAirports(){
        if(initialized)
            return resultList;
        initialized=true;
        return read();
    }

    public static ArrayList read(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (row[3].substring(1,row[3].length()-1).equals("United States"))
                    resultList.add(row);
            }
        }
        catch (IOException ex) {throw new RuntimeException("Error in reading CSV file: "+ex); }

        finally {
            try {inputStream.close(); }
            catch (IOException e) {throw new RuntimeException("Error while closing input stream: "+e); }
        }
        return resultList;
    }
}