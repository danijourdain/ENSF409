/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;

import java.util.*;
import java.util.regex.*;

/**
 * Class which describes a piece of property in Calgary, for use in multiple city applications.
**/
abstract class CalgaryProperty extends StandardFormatting {
    // Recognized types of property zoning
    final String[] zoningTypes = {"COMMERCIAL", "RESIDENTIAL", "INDUSTRIAL",
        "AGRICULTURAL", "MIXED_USE" };

    // Basic information about property. Annex is optional.
    protected final int TAX_ROLL_NUMBER;
    protected String zoning;
    protected int buildingNumber;
    protected String buildingAnnex;
    protected String streetName;
    protected String postCode;

    /** Getters and Setters **/
    public int getTaxRollNumber() { return this.TAX_ROLL_NUMBER; }
    public String getZoning() { return zoning; }
    public int getBuildingNumber() { return this.buildingNumber; }
    public String getBuildingAnnex() { return this.buildingAnnex; }
    public String getStreetName() { return this.streetName; }
    public String getPostCode() { return this.postCode; }
    public void setBuildingNumber(int number) { this.buildingNumber = number; }
    public void setStreetName(String name) { this.streetName = name; }
    public void setBuildingAnnex(String id) { this.buildingAnnex = id; }

    // Setter with validity check - zones must match those in zoningTypes enum
    public void setZoning(String zone) throws IllegalArgumentException {
        zone = zone.toUpperCase();
        String allZones = "";
        for (String type : zoningTypes) {
            if (zone.equals(type)) {
                this.zoning = type;
                return;
            }
            allZones = allZones + type + ", ";
        }
        allZones = allZones.replaceAll(", $", "");
        String err = String.format("'%s' is not an existing zoning type. Zoning types are %s", zone, allZones);
        throw new IllegalArgumentException(err);
    }

    // Setter with validity check - postcodes must be 6 characters long
    public void setPostCode(String code) {
        // Normalize to uppercase and just letters/numbers
        // Internal storage is 3 characters, space, 3 characters
        code = code.toUpperCase();
        code = code.replaceAll("[^\\w]", " ");

        // Check if it is exactly 6 characters
        Pattern thePattern = Pattern.compile("^(\\w{3})(\\w{3})$");
        Matcher theMatcher = thePattern.matcher(code);
        boolean valid = theMatcher.find();
    
        if(valid == false) {
            throw new IllegalArgumentException();
        }

        String formattedCode = theMatcher.group(1) + " " + theMatcher.group(2);

        this.postCode = formattedCode;
        return;
    }

    /** Constructors **/
    public CalgaryProperty(int taxRollNumber, String zoning, String streetName, int buildingNumber, String postCode, String buildingAnnex) throws IllegalArgumentException {
        this.TAX_ROLL_NUMBER = taxRollNumber;
        createProperty(zoning, streetName, buildingNumber, postCode);
        this.buildingAnnex = buildingAnnex;
    }

    // Annex is optional, all other arguments required
    public CalgaryProperty(int taxRollNumber, String zoning, String streetName, int buildingNumber, String postCode) throws IllegalArgumentException {
        createProperty(zoning, streetName, buildingNumber, postCode);
        this.TAX_ROLL_NUMBER = taxRollNumber;
    }


    /** Helpers **/
    private void createProperty(String zoning, String streetName, int buildingNumber, String postCode) throws IllegalArgumentException{
        setPostCode(postCode);
        setZoning(zoning);
        this.buildingNumber = buildingNumber;
        this.streetName = streetName;
        return;
    }
}
