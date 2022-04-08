/*
Copyright Ann Barcomb and Emily Marasco, 2021
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.ensf409;
import java.time.LocalDate;
import java.util.*;

public class HouseholdParking extends CalgaryProperty {
    private String residentLicence;
    private VisitorParking vp;
    /*
    // Each residental property is allowed one street parking permit
    private LinkedList<String> residentLicence = new LinkedList<String>();
    private int maxLicences = 3;
    */

    public HouseholdParking(int taxRollNumber, String zoning, String streetName, int buildingNumber, String postCode, String buildingAnnex) throws IllegalArgumentException {
        super(taxRollNumber,zoning,streetName,buildingNumber,postCode,buildingAnnex);
        this.vp = new VisitorParking();
    }

    public HouseholdParking(int taxRollNumber, String zoning, String streetName, int buildingNumber, String postCode) throws IllegalArgumentException {
        super(taxRollNumber,zoning,streetName,buildingNumber,postCode);
        this.vp = new VisitorParking();
    }

    /*
     * Add a licence to the first empty spot in residentLicence, or replace the most recent
     * Ignore if the licence is already stored
     * @param licence - The licence plate to be added
     * @throws IllegalArgumentException if licence plate isn't a valid Alberta licence
    
    public void addOrReplaceResidentLicence(String licence) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        int len = residentLicence.size();

        // If licence is already in the list, don't continue
        for (String val : residentLicence) {
            if (val.equals(licence)) {
                return;
            }
        }

        // If fewer than three are stored, add to list
        if (len < maxLicences) {
            residentLicence.add(licence);
 
        // If more than three are stored, replace last in list
        } else {
            this.residentLicence.set(maxLicences-1, licence);
        }
    }

    /*
     * Remove all listed licences
     * @return whether the operation succeeded or not
    
    public boolean removeResidentLicence() {
        this.residentLicence.clear();
        return true;
    }

    /*
     * Remove a specific listed licence 
     * @param licence - the licence to be removed
     * @return whether the operation succeeded or not
    public boolean removeResidentLicence(String licence) {
        // Standardize the licence name. If it is invalid, it can't exist so return false.
        try {
            licence = Parking.standardizeAndValidateLicence(licence);
        }
        catch (Exception e) {
            return false;
        }

        for (int i=0; i < this.residentLicence.size(); i++) {
            if (licence.equals(this.residentLicence.get(i))) {
                this.residentLicence.remove(i);
                return true;
            }
        }

        // Couldn't find entry
        return false;
    }
    */

    /**
     * Replaces the current resident licence with the given licence.
     * @param licence The new licence to be used.
     * @throws IllegalArgumentException thrown if the new licence is invalid.
     */
    public void addOrReplaceResidentLicence(String licence) throws IllegalArgumentException {
        licence = Parking.standardizeAndValidateLicence(licence);
        this.residentLicence = licence;
    }

    /**
     * Removes current resident licence.
     * @return True if the removal was successful.
     */
    public boolean removeResidentLicence() {
        this.residentLicence = "";
        return true;
    }

    /**
     * Removes the resident licence if it matches the one given.
     * @param licence The licence to attempt to remove.
     * @return True if the licence could be removed, false otherwise.
     */
    public boolean removeResidentLicence(String licence) {
        try {
            licence = Parking.standardizeAndValidateLicence(licence);
        }
        catch (Exception e) {
            return false;
            //if the given licence is invalid, the current licence cannot be removed
        }

        if(this.residentLicence.equals(licence)) {
            this.residentLicence = "";
            return true;
            //if the given licence matches the current licence, remove it
        }
        else {
            return false;
            //otherwise no removal is done
        }
    }
        
    /**
     * Get all the licences stored for the resident
     * @return An array containing the resident's licences
    */
    public String getResidentLicence() {
       return this.residentLicence;
    }

    /**
     * Adds a visitor reservation for the given licence starting today.
     * @param licence The visitor to add a reservation for.
     * @throws IllegalArgumentException Thrown if the reservation cannot be made. If the licence is invalid, 
     * or the household already has 2 reservations overlapping today, the exception will be thrown.
     */
    public void addVisitorReservation(String licence) throws IllegalArgumentException {
        this.vp.addVisitorReservation(licence);
    }

    /**
     * Adds a visitor reservation for the given licence starting on the given date.
     * @param licence The visitor to add a reservation for.
     * @param day The date the reservation should start.
     * @throws IllegalArgumentException Thrown if the reservation cannot be made. If the licence is invalid, 
     * the date is in the past, the licence already has a reservation overlapping this date, or this household 
     * already has 2 reservations overlapping this date the exception will be thrown.
     */
    public void addVisitorReservation(String licence, LocalDate day) throws IllegalArgumentException {
        this.vp.addVisitorReservation(licence, day);
    }

    /**
     * Getter for the VisitorParking object.
     * @return The VisitorParking object
     */
    public VisitorParking getVisitors() {
        return this.vp;
    }

    /**
     * Method to find all visitors registered for a given date.
     * @param date The date to check.
     * @return All visitor licences with reservations overlapping with date.
     */
    public ArrayList<String> getLicencesRegisteredForDate(LocalDate date) {
        return this.vp.getLicencesRegisteredForDate(date);
    }

    /**
     * Method to find all visitors registered for today.
     * @return All visitor licences with reservations overlapping today.
     */
    public ArrayList<String> getLicencesRegisteredForDate() {
        return this.getLicencesRegisteredForDate(LocalDate.now());
    }

    /**
     * Method to find if a visitor has a reservation today.
     * @param licence The licence to check the reservation for.
     * @return True if there is an overlapping reservation, false otherwise.
     */
    public boolean licenceIsRegisteredForDate(String licence) {
        return this.vp.licenceIsRegisteredForDate(licence);
    }

    /**
     * Method to find if a visitor has a reservation for a given date.
     * @param licence The licence to check the reservation for.
     * @param startDate The date to check.
     * @return True if the licence has a reservation overlapping date, false otherwise.
     */
    public boolean licenceIsRegisteredForDate(String licence, LocalDate startDate) {
        return this.vp.licenceIsRegisteredForDate(licence, startDate);
    }

    /**
     * Method to get all days a visitor licence has reservations for.
     * @param licence The licence to get reservations for.
     * @return An ArrayList of LocalDates containing all days the licence has reservations.
     */
    public ArrayList<LocalDate> getAllDaysLicenceIsRegistered(String licence) {
        ArrayList<LocalDate> result = this.vp.getAllDaysLicenceIsRegistered(licence);
        return result;
    }

    /**
     * Method to get start days a visitor licence has reservations for.
     * @param licence The licence to get reservations.
     * @return An ArrayList of LocalDates containing the start days the licences has reservations for.
     */
    public ArrayList<LocalDate> getStartDaysLicenceIsRegistered(String licence) {
        ArrayList<LocalDate> result = this.vp.getStartDaysLicenceIsRegistered(licence);
        return result;
    }
}