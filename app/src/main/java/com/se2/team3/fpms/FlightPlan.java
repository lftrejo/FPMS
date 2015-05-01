package com.se2.team3.fpms;

import java.util.Calendar;

/**
 * Created by Scott on 4/30/2015.
 */



public class FlightPlan
    extends Resource{
    String fpName;
    Aircraft aircraft;
    float cruiseSpeed;
    int cruiseAlt;
    Waypoint departure;
    Waypoint arrival;
    Calendar date;
    RoutingPreference routingPreference;
    Calendar ETE;
    float fuel;
    Waypoint alt1;
    Waypoint alt2;
    Waypoint alt3;
    String pilotName;
    String contactInfo;
    int peopleOnboard;
    String destContact;
    String remarks;
    boolean TGFApproval;

    public enum RoutingPreference {
        SHORTEST, FASTEST, ARCHIVED, CUSTOM
    }

    public String getfpName() {
        return fpName;
    }

    public void setfpName(String fpName) {
        this.name = fpName;
        this.fpName = fpName;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public float getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(float cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public int getCruiseAlt() {
        return cruiseAlt;
    }

    public void setCruiseAlt(int cruiseAlt) {
        this.cruiseAlt = cruiseAlt;
    }

    public Waypoint getDeparture() {
        return departure;
    }

    public void setDeparture(Waypoint departure) {
        this.departure = departure;
    }

    public Waypoint getArrival() {
        return arrival;
    }

    public void setArrival(Waypoint arrival) {
        this.arrival = arrival;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public RoutingPreference getRoutingPreference() {
        return routingPreference;
    }

    public void setRoutingPreference(RoutingPreference routingPreference) {
        this.routingPreference = routingPreference;
    }

    public Calendar getETE() {
        return ETE;
    }

    public void setETE(Calendar ETE) {
        this.ETE = ETE;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public Waypoint getAlt1() {
        return alt1;
    }

    public void setAlt1(Waypoint alt1) {
        this.alt1 = alt1;
    }

    public Waypoint getAlt2() {
        return alt2;
    }

    public void setAlt2(Waypoint alt2) {
        this.alt2 = alt2;
    }

    public Waypoint getAlt3() {
        return alt3;
    }

    public void setAlt3(Waypoint alt3) {
        this.alt3 = alt3;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getPeopleOnboard() {
        return peopleOnboard;
    }

    public void setPeopleOnboard(int peopleOnboard) {
        this.peopleOnboard = peopleOnboard;
    }

    public String getDestContact() {
        return destContact;
    }

    public void setDestContact(String destContact) {
        this.destContact = destContact;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isTGFApproval() {
        return TGFApproval;
    }

    public void setTGFApproval(boolean TGFApproval) {
        this.TGFApproval = TGFApproval;
    }
}
