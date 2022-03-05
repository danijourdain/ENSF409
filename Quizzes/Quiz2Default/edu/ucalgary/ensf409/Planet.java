/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;

import java.util.*;
import java.util.regex.*;


/**
 * A component of a spaceship navigation system used by a future society
**/
abstract class Planet {
    // Recognized types of planets
    final String[] PLANET_TYPES = {"ROCKY", "GAS GIANT", "ICE GIANT",
        "NEPTUNIAN", "TERRESTRIAL" };

    // Basic information about this planet
    protected final String OFFICIAL_NAME;
    protected String commonName; 
    protected final String PLANET_TYPE;
    protected final String SUN; 
    protected final float DIAMETER;
    protected final double[] COORDINATES; 

    /** Getters and Setters **/

    /*
     * Getter
     * @return planet official name
    */
    public String getOfficialName() { return this.OFFICIAL_NAME; }

    /*
     * Getter
     * @return common name or empty String if no common name
    */
    public String getCommonName() { return this.commonName; }

    /*
     * Getter
     * @return type of planet
    */
    public String getPlanetType() { return this.PLANET_TYPE; }

    /*
     * Getter
     * @return planet's sun
    */
    public String getSun() { return this.SUN; }

    /*
     * Getter
     * @return diameter in 10s of km
    */
    public float getDiameter() { return this.DIAMETER; }

    /*
     * Getter
     * @return all coordinates, in the order: x, y, z
    */
    public double[] getCoordinates() { return this.COORDINATES; }

    /*
     * Getter
     * @return x space coordinate
    */
    public double getXCoordinate() { return this.COORDINATES[0]; }

    /*
     * Getter
     * @return y space coordinate
    */
    public double getYCoordinate() { return this.COORDINATES[1]; }

    /*
     * Getter
     * @return z space coordinate
    */
    public double getZCoordinate() { return this.COORDINATES[2]; }

    /*
     * Set the common name of the planet
     * @param name - common name of the planet
    */
    public void setCommonName(String name) { this.commonName = name; }

    /*
     * Give a name which includes the official name, and common name if it exists. 
     * Format if no common name: Official Name
     * Format if common name: Official Name ("Common Name")
     * @return Name(s) of the planet
    */
    public String getName() {
        if (this.commonName == null) {
            return this.OFFICIAL_NAME;
        }
        else {
            return this.OFFICIAL_NAME + " (\"" + this.commonName + "\")";
        }
    }

    /** Constructors **/

    /*
     * Complete constructor
     * @param commonName - colloquial name for the planet
     * @param officialName - formal name for the planet
     * @param sun - name of sun planet orbits
     * @param xCoordinate - X coordinate in space
     * @param yCoordinate - Y coordinate in space
     * @param zCoordinate - Z coordinate in space
     * @param diameter - diameter in 10s of km
     * @param type - planet type
     * @throws IllegalArgumentException if planet type is invalid
    */
    public Planet(String commonName, String officialName, String sun, double xCoordinate, double yCoordinate, double zCoordinate, float diameter, String type) throws IllegalArgumentException {
       this.commonName = commonName;
       this.OFFICIAL_NAME = officialName;
       this.SUN = sun;
       this.COORDINATES = new double[] { xCoordinate, yCoordinate, zCoordinate };
       this.DIAMETER = diameter;
       this.PLANET_TYPE = this.normalizeAndValidatePlanetType(type);
   }

    /*
     * Simplified constructor for planets without common names
     * @param officialName - formal name for the planet
     * @param sun - name of sun planet orbits
     * @param xCoordinate - X coordinate in space
     * @param yCoordinate - Y coordinate in space
     * @param zCoordinate - Z coordinate in space
     * @param diameter - diameter in 10s of km
     * @param type - planet type
     * @throws IllegalArgumentException if planet type is invalid
    */
    public Planet(String officialName, String sun, double xCoordinate, double yCoordinate, double zCoordinate, float diameter, String type) throws IllegalArgumentException {
       this.OFFICIAL_NAME = officialName;
       this.SUN = sun;
       this.COORDINATES = new double[] { xCoordinate, yCoordinate, zCoordinate };
       this.DIAMETER = diameter;
       this.PLANET_TYPE = this.normalizeAndValidatePlanetType(type);
   }


    /** Helpers **/

    /* 
     * Normalize provided planet and ensure it is on the list of PLANET_TYPES
     * Normalize by making upper case and replacing underscores with spaces
     * @param type - proposed planet type
     * @return Normalized planet type
     * @throws IllegalArgumentException if planet type is invalid
    */
    private String normalizeAndValidatePlanetType(String type) throws IllegalArgumentException {
        // Normalize to uppercase and spaces
        type = type.toUpperCase();
        type = type.replaceAll("_", " ");
        
        String allTypes = "";
        for (String val : PLANET_TYPES) {
            if (val.equals(type)) {
                return type;
            }
            allTypes = allTypes + val + ", ";
        }
        allTypes = allTypes.replaceAll(", $", ""); 
        String err = String.format("'%s' is not an existing planet type. Planet types are %s", type, allTypes);
        throw new IllegalArgumentException(err);
    } 
}
