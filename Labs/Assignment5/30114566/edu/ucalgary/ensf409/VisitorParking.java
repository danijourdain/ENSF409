/**
 * @author Danielle Jourdain
 * @version 1.17
 * @since 1.0
 */

package edu.ucalgary.ensf409;

import java.time.LocalDate;
import java.util.*;

public class VisitorParking {
    private HashMap<String, TreeSet<LocalDate>> parkingRecord;
    
    /**
     * One-argument constructor for VisitorParking. Creates a VisitorParking object with one entry. 
     * The entry will be one reservation for the given licence starting today.
     * @param licence The licence to create a reservation for.
     * @throws IllegalArgumentException Thrown if the licence is invalid.
     */
    public VisitorParking(String licence) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        //check if the licence is valid

        this.parkingRecord = new HashMap<>();
        TreeSet<LocalDate> date = new TreeSet<>();
        date = (TreeSet<LocalDate>)date.descendingSet();
        //set the TreeSet to be in reverse order
        date.add(LocalDate.now());
        //create a new TreeSet to hold the date

        this.parkingRecord.put(licence, date);
        //add the licence and TreeSet to the HashMap
    }

    /**
     * Two-argument constructor for VisitorParking. Creates a VisitorParking object with one entry.
     * The entry will be one reservation for the given licence starting on the provided date.
     * @param licence The licence to create a reservation for.
     * @param startDate The day to start the reservation.
     * @throws IllegalArgumentException Thrown if the licence is invalid OR the provided date is in the past.
     */
    public VisitorParking(String licence, LocalDate startDate) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        //check if the licence is valid

        if(startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Given date must be a present or future date.");
            //if the date is in the past, throw an exception
        }

        this.parkingRecord = new HashMap<>();
        TreeSet<LocalDate> date = new TreeSet<>();
        date = (TreeSet<LocalDate>)date.descendingSet();
        //set the TreeSet to be in reverse order
        date.add(startDate);
        //create a new TreeSet to hold the date

        this.parkingRecord.put(licence, date);
        //add the licence and TreeSet to the HashMap
    }

    /**
     * Default constructor for VisitorParking.
     */
    public VisitorParking() {
        this.parkingRecord = new HashMap<>();
        //create a new HashMap
    }

    /**
     * Getter method for the parkingRecord.
     * @return A Hashmap with Strings as the Keys and TreeSets of LocalDates as the elements.
     */
    public HashMap<String, TreeSet<LocalDate>> getParkingRecord() {
        return this.parkingRecord;
    }

    /**
     * Helper method to check if there is overlap for a given date with existing reservations.
     * @param date The date to check overlap for.
     * @throws IllegalArgumentException Thrown if there are already 2 reservations on the date.
     */
    private void checkOverlap(LocalDate date) throws IllegalArgumentException {
        int numRegisteredForDate = 0;

        for(Map.Entry<String, TreeSet<LocalDate>> set: this.parkingRecord.entrySet()) {
            //iterate through the HashMap
            TreeSet<LocalDate> dates = set.getValue();
            if(dates == null) {
                continue;
                //if the TreeSet is empty, skip this iteration of the loop
            }

            LocalDate start2 = date.minusDays(1);
            LocalDate start3 = date.minusDays(2);
            //get the 3 days the reservation is active

            if(dates.contains(date) || dates.contains(start2) || dates.contains(start3)) {
                numRegisteredForDate++;
                //increment if one of the licences has a reservation on any days the new 
                    //reservation is being made
            }
        }

        if(numRegisteredForDate >= 2) {
            //if there are too many registrations on the current day, throw an exception
            throw new IllegalArgumentException("Cannot register more than 2 visitors for one date");
        }
    }

    /**
     * Add a new visitor reservation for today on the given licence.
     * @param licence The licence to add the reservation to.
     * @throws IllegalArgumentException Thrown if the licence is invalid OR there are too many
     * reservations overlapping today.
     */
    public void addVisitorReservation(String licence) throws IllegalArgumentException{
        addVisitorReservation(licence, LocalDate.now());
    }

    /**
     * Add a new visitor reservation for the date on the given licence.
     * @param licence The licence to add the reservation to.
     * @param startDate The date to start the reservation on.
     * @throws IllegalArgumentException Thrown if the reservation cannot be made. If the licence is invalid, 
     * the date is in the past, the licence already has a reservation overlapping this date, or this household 
     * already has 2 reservations overlapping this date the exception will be thrown. 
     */
    public void addVisitorReservation(String licence, LocalDate startDate) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        //check if the licence is valid 

        if(startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Given date must be a present or future date.");
            //if startDate is in the past, throw an exception
        }

        checkOverlap(startDate);
        //check if there are too many reservations on startDate already

        if(this.parkingRecord.get(licence) == null) {
            TreeSet<LocalDate> reservedDate = new TreeSet<>();
            reservedDate = (TreeSet<LocalDate>)reservedDate.descendingSet();
            //set the TreeSet to be in reverse order

            reservedDate.add(startDate);
            this.parkingRecord.put(licence, reservedDate);
            //if the licence is not in the HashMap, create a new TreeSet and add them to the HashMap
        }
        else {
            TreeSet<LocalDate> registeredDates = this.parkingRecord.get(licence);
            Iterator<LocalDate> TreeSetIter = registeredDates.iterator();

            while(TreeSetIter.hasNext()) {
                //iterate through the TreeSet of LocalDates to see if there is an overlap with
                    //any of the existing reservations for the licence
                LocalDate existingDate = TreeSetIter.next();

                if(!(startDate.isBefore(existingDate)) && !(startDate.isAfter(existingDate.plusDays(2)))) {
                    throw new IllegalArgumentException("Error. Trying to add a new reservation overlapping with an exisiting one.");
                    //throw an exception if there is an overlap
                }
            }

            registeredDates.add(startDate);

            this.parkingRecord.put(licence, registeredDates);
            //otherwise add the new startDate and add the licence and TreeSet to the HashMap
        }
    }

    /**
     * Checks if the provided licence has a reservation on the given date.
     * @param licence The licence to check
     * @param startDate The date to check
     * @return True if there is an overlapping reservation. False otherwise.
     * @throws IllegalArgumentException thrown if the licence is invalid or the provided date is in the past.
     */
    public boolean licenceIsRegisteredForDate(String licence, LocalDate startDate) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        if(startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Given date must be a present or future date.");
            //if startDate is in the past, throw an exception
        }

        TreeSet<LocalDate> dates = this.parkingRecord.get(licence);
        //get the TreeSet associated with the licence
        if(dates == null) {
            return false;
            //if the TreeSet doesn't exist return false
        }
        else {
            for(LocalDate day: dates) {
                LocalDate day2 = day.plusDays(1);
                LocalDate day3 = day.plusDays(2);
                //for each reservation in the TreeSet, get all days the reservation is active

                if(day.equals(startDate) || day2.equals(startDate) || day3.equals(startDate)) {
                    return true;
                    //if the start date overlaps the reservation, return true.
                }
            }
            return false;
            //if no match was found, return false
        }
    }

    /**
     * Checks if the provided licence has a reservation today.
     * @param licence The licence to check
     * @return True if there is an overlapping reservation. False otherwise.
     * @throws IllegalArgumentException thrown if the licence is invalid.
     */
    public boolean licenceIsRegisteredForDate(String licence) throws IllegalArgumentException {
        return licenceIsRegisteredForDate(licence, LocalDate.now());
    }

    /**
     * Finds all visitor licences registered today.
     * @return An ArrayList of Strings containing all of the licences.
     */
    public ArrayList<String> getLicencesRegisteredForDate() {
        return getLicencesRegisteredForDate(LocalDate.now());
    }

    /**
     * Finds all visitor licences registered for a given date.
     * @param date The date to check.
     * @return An ArrayList of Strings containing all the licences.
     * @throws IllegalArgumentException Thrown if the provided date is in the past.
     */
    public ArrayList<String> getLicencesRegisteredForDate(LocalDate date) throws IllegalArgumentException {
        if(date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Given date must be a present or future date.");
            //if date is in the past, throw an exception
        }

        ArrayList<String> result = new ArrayList<>();
        for(Map.Entry<String, TreeSet<LocalDate>> set: this.parkingRecord.entrySet()) {
            TreeSet<LocalDate> dates = set.getValue();
            
            if(dates == null) {
                continue;
            }

            LocalDate start2 = date.minusDays(1);
            LocalDate start3 = date.minusDays(2);
            //get the 3 days the reservation is active

            if(dates.contains(date) || dates.contains(start2) || dates.contains(start3)) {
                result.add(set.getKey());
            }
        }

        return result;
    }

    /**
     * Finds all days a visitor licence is registered.
     * @param licence The licence to check.
     * @return An ArrayList of LocalDates containing all dates the visitor licence has reservations.
     * @throws IllegalArgumentException Thrown if the licence is invalid.
     */
    public ArrayList<LocalDate> getAllDaysLicenceIsRegistered(String licence) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        //standardize the licence

        ArrayList<LocalDate> result = new ArrayList<>();
        TreeSet<LocalDate> registeredDates = this.parkingRecord.get(licence);
        Iterator<LocalDate> iter = registeredDates.iterator();

        while(iter.hasNext()) {
            LocalDate date = iter.next();
            result.add(date.plusDays(2));
            result.add(date.plusDays(1));
            result.add(date);
            //add each start dates plus the next 2 days to the ArrayList
        }

        Collections.reverse(result);
        //reverse the order since the TreeSet is in reverse order
        return result;
    } 


    /**
     * Finds the starting reservation days a visitor licence is registered.
     * @param licence The licence to check.
     * @return An ArrayList of LocalDates containing all of the start dates.
     * @throws IllegalArgumentException thrown if the licence is invalid.
     */
    public ArrayList<LocalDate> getStartDaysLicenceIsRegistered(String licence) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        //standardize the licence
        ArrayList<LocalDate> result = new ArrayList<>();
        TreeSet<LocalDate> registeredDates = this.parkingRecord.get(licence);
        Iterator<LocalDate> iter = registeredDates.iterator();

        while(iter.hasNext()) {
            LocalDate date = iter.next();
            result.add(date);
            //add the start dates to the ArrayList
        }
        
        Collections.reverse(result);
        //reverse the order of the ArrayList since the TreeSet is in reverse order
        return result;
    }
} 