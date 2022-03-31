package edu.ucalgary.ensf409;

import java.time.LocalDate;
import java.util.*;

public class VisitorParking {
        private HashMap<String, TreeSet<LocalDate>> visitorInfo;
    
    public VisitorParking(String licence) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);

        this.visitorInfo = new HashMap<>();
        this.visitorInfo.put(licence, null);
        //add the licence into the hashmap and set the treeset to null since no date was given
    }

    public VisitorParking(String licence, LocalDate startDate) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);

        this.visitorInfo = new HashMap<>();
        TreeSet<LocalDate> date = new TreeSet<>();
        date.add(startDate);

        this.visitorInfo.put(licence, date);

    }

    public VisitorParking() {
        this.visitorInfo = new HashMap<>();
    }

    public void addVisitorReservation(String licence) {

    }

    public void addVisitorReservation(String licencce, LocalDate startDate) {

    }

    public boolean licenceIsRegisteredForDate(String licence, LocalDate startDate) {
        return false;
    }

    public boolean licenceIsRegisteredForDate(String licence) {
        return false;
    }

    public ArrayList<String> getLicencesRegisteredForDate() {
        return null;
    }

    public ArrayList<String> getLicencesRegisteredForDate(LocalDate date) {
        return null;
    }

    public ArrayList<LocalDate> getAllDaysLicenceIsRegistered(String licence) {
        return null;
    } 

    public ArrayList<LocalDate> getStartDaysLicenceIsRegistered(String licence) {
        return null;
    }
} 