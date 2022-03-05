/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;

import java.util.Arrays;


/**
 * Class which describes a class of planets spaceships might wish to visit
**/
public class InhabitedPlanet extends Planet implements LocationFormatting {
    // Top five cities on the planet
    String[] majorCities = new String[5];

    /** Constructors **/

    /**
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
    public InhabitedPlanet(String commonName, String officialName, String sun, double xCoordinate, double yCoordinate, double zCoordinate, float diameter, String type) throws IllegalArgumentException {
        super(commonName, officialName, sun, xCoordinate, yCoordinate, zCoordinate, diameter, type);
   }

    /**
     * Partial constructor, for outposts without common name
     * @param officialName - formal name for the planet
     * @param sun - name of sun planet orbits
     * @param xCoordinate - X coordinate in space
     * @param yCoordinate - Y coordinate in space
     * @param zCoordinate - Z coordinate in space
     * @param diameter - diameter in 10s of km
     * @param type - planet type
     * @throws IllegalArgumentException if planet type is invalid
    */
    public InhabitedPlanet(String officialName, String sun, double xCoordinate, double yCoordinate, double zCoordinate, float diameter, String type) throws IllegalArgumentException {
        super(officialName, sun, xCoordinate, yCoordinate, zCoordinate, diameter, type);
   }

   /** Getters and Setters **/

   /**
    * Getter for major cities
    * @return an array of major cities
    */
   public String[] getMajorCities() { return this.majorCities; }

   /**
    * Getter for major cities in a String
    * @return a comma-delimited list of non-null major cities, e.g., "City One, City Two"
    */
   public String getMajorCitiesAsString() { 
       String cities = new String();
       for(String city: this.majorCities) {
           if(city != null) {
               cities = cities.concat(city + ", ");
           }
       }
       cities = cities.replaceAll(", $", "");
       //also delete the final comma that isn't needed in the list
       return cities.toString();
   }

   /**
    * Add a new city to the list of major cities. If the array is full, remove the first major city and add the new one at the end.
    * @param city - The city to be added to the list of major cities
    */
    public void addOrReplaceMajorCity(String city) {
        String[] newCities = new String[5];
        int i;
        for(i = 0; i < this.majorCities.length; i++) {
            if(this.majorCities[i] == null) {
                break;
            }
        }
        //use this for loop to find the first empty spot in the array

        if(i < this.majorCities.length) {
            this.majorCities[i] = city;
            //if there are less than 5 elements already in the array, simply add the new city
        }
        else {
            for(int j = 0; j < this.majorCities.length - 1; j++) {
                this.majorCities[j] = this.majorCities[j + 1];
            }
            //otherwise, shift all existing elements, deleting the first one
            //and add the new city at the end
            this.majorCities[this.majorCities.length - 1] = city;
        }
   }
}
