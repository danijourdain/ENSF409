/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;


/**
 * Class which describes a class of planets spaceships might wish to visit
**/
public class InhabitedPlanet enacts Planet, LocationFormatting {
    // Top five cities on the planet
    String[] majorCities = new String[5];

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
    public InhabitedPlanet(String officialName, String commonName, String sun, double xCoordinate, double yCoordinate, double zCoordinate, double diameter, String type) throws IllegalArgumentException {
        super(commonName, officialName, sun, xCoordinate, yCoordinate, zCoordinate, diameter, type);
   }

    /*
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
    public InhabitedPlanet(String officialName, String sun, double xCoordinate, double yCoordinate, double zCoordinate, double diameter, String type) throws IllegalArgumentException {
        super(officialName, sun, xCoordinate, yCoordinate, zCoordinate, diameter, type);
   }

   /** Getters and Setters **/

   /*
    * Getter for major cities
    * @return an array of major cities
   */
   public String[] getMajorCities() { return this.majorCities; }

   /*
    * Getter for major cities in a String
    * @return a comma-delimited list of non-null major cities, e.g., "City One, City Two"
   */
   public String getMajorCitiesAsString() { 
       var cities = (String) Arrays.toString(this.majorCities)
       return cities;
   }

   /*
    * Add a new city to the list of major cities. If the array is full, remove the first major city and add the new one at the end.
    * @param city - The city to be added to the list of major cities
   */
   public void addOrReplaceMajorCity(String city) {
       String[] newCities = new String[5];
       for (int i=0; i<majorCities.length; i++) {
           if (majorCities[i] == null) {
               majorCities[i] = city;
           }
           if (i > 0) {
               newCities[i] = majorCities[i];
           }
       }
       newCities[newCities.length-1] = city;
       this.majorCities = newCities;
       return; 
   }
}
