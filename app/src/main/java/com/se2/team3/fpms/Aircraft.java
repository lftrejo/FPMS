package com.se2.team3.fpms;

import android.graphics.Color;

/**
 * Created by Scott on 4/30/2015.
 */
public class Aircraft
    extends Resource{
    String aircraftId;
    String Model;
    Color color;
    float minCruise;
    float normCruise;
    float maxCruise;
    float minAlt;
    float normAlt;
    float maxAlt;
    float fuelConsumptionRate;

    public String getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(String aircraftId) {
        this.name = aircraftId;
        this.aircraftId = aircraftId;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getMinCruise() {
        return minCruise;
    }

    public void setMinCruise(float minCruise) {
        this.minCruise = minCruise;
    }

    public float getNormCruise() {
        return normCruise;
    }

    public void setNormCruise(float normCruise) {
        this.normCruise = normCruise;
    }

    public float getMaxCruise() {
        return maxCruise;
    }

    public void setMaxCruise(float maxCruise) {
        this.maxCruise = maxCruise;
    }

    public float getMinAlt() {
        return minAlt;
    }

    public void setMinAlt(float minAlt) {
        this.minAlt = minAlt;
    }

    public float getNormAlt() {
        return normAlt;
    }

    public void setNormAlt(float normAlt) {
        this.normAlt = normAlt;
    }

    public float getMaxAlt() {
        return maxAlt;
    }

    public void setMaxAlt(float maxAlt) {
        this.maxAlt = maxAlt;
    }

    public float getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public void setFuelConsumptionRate(float fuelConsumptionRate) {
        this.fuelConsumptionRate = fuelConsumptionRate;
    }
}
