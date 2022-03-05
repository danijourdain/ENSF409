/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
            UCID: 30114566
@version    1.6
@since      1.0
*/

package edu.ucalgary.ensf409;
import java.util.regex.*;

/*
Copyright Ann Barcomb and Emily Marasco, 2021-2022
All rights reserved.
*/


public class Trip {
    private String arrival;
    private String departure;
    private String city;
    private String country;
    //member variables of Trip describing qualities of a trip

    // Returns a string in the format of:
    // value (key)
    /**
     * This function takes a key and a value, and returns a String in the form value (key)
     * @param key the value to go in between the brackets in the return string
     * @param value the value to go at the beginning of the return string
     * @return a new string in the form value (key)
     */
    public static String fmtString(String key, String value) {
        String returnString = new String();
        //create a new String object to hold the string to be returned

        returnString += value;
        //adds the value to the new String

        returnString += " (";
        returnString += key;
        returnString += ")";
        //adds the key surrounded by parentheses to the new String

        return returnString;
    }

    // Constructor
    /**
     * Constructor for Trip
     * @param array an array of Strings relating to attributes of a trip
     * in the order {arrival, departure, city, country}
     */
    public Trip(String[] array) {
        this.arrival = array[0];
        this.departure = array[1];
        this.city = array[2];
        this.country = array[3];
    }

    // Given a date string, return just the year
    /**
     * Find the year of the given date string
     * @param date String in the format YYYY-MM-DD
     * @return All 4 digits of the year
     */
    public static int getYear(String date) {
        String[] splitDate = date.split("-", 0);
        //since dates are always in the form YYY-MM-DD splitting the string at
        //every "-" will give the individual components

        //reuturn the first index of the new array to get the year
        return Integer.valueOf(splitDate[0]);
    }

    // Given a date string, return just the month
    // Since it is an int, a date like "2022-01-12" returns 1
    /**
     * Find the month of a given date string
     * @param date String in the format YYYY-MM-DD
     * @return the month from the date string
     */
    public static int getMonth(String date) {
        String[] splitDate = date.split("-", 0);
        //since dates are always in the form YYY-MM-DD splitting the string at
        //every "-" will give the individual components

        //return the second index of the new array to get the month
        return Integer.valueOf(splitDate[1]);
    }

    // Return a formatted string of key/value pairs, with commas
    // between each. See the output for an example.
    /**
     * Creates a string from the attributes of the trip
     * @return one string with all attributes of the trip
     */
    public String formatTrip() {
        String formattedTrip = new String();

        formattedTrip += Trip.fmtString("Arrival", this.arrival);
        formattedTrip += ", ";
        //add result of fmtString to formattedTrip
        //fmtString gives a "key" (the String literal argument)
        //and the second argument is the value of the arrival member varaible

        formattedTrip += Trip.fmtString("Departure", this.departure);
        formattedTrip += ", ";

        formattedTrip += this.formatPlace();
        //use the formatPlace method to get the correct format of the destination of the trip

        //after all formatting is complete return the formatted trip string
        return formattedTrip;
    }

    /**
     * Creates a string from the city and country of a trip
     * @return string with city and country of a trip formatted correctly
     */
    public String formatPlace() {
        String location = new String();

        location += Trip.fmtString("City", this.city);
        location += ", ";
        //using the fmtString method add the city and then the country separated
        //by a comma
        
        location += Trip.fmtString("Country", this.country);

        return location;
    }

    // Getter
    /**
     * Getter for the arrival date of the trip
     * @return a date string
     */
    public String getArrival() {
        return this.arrival;
    }

    // Getter
    /**
     * Getter for the departure date of the trip
     * @return a date string
     */
    public String getDeparture() {
        return this.departure;
    }

    // Getter
    /**
     * Getter for the city of the trip
     * @return a string with the city
     */
    public String getCity() {
        return this.city;
    }

    // Getter
    /**
     * Getter for the country of the trip
     * @return a string with the country
     */
    public String getCountry() {
        return this.country;
    }

    // Setter
    /**
     * Setter for the arrival date of the trip
     * @param date a date string
     */
    public void setArrival(String date) {
        this.arrival = date;
    }

    // Setter
    /**
     * Setter for the departure date of the trip
     * @param date a date string
     */
    public void setDeparture(String date) {
        this.departure = date;
    }

    // Setter 
    /**
     * Setter for the destination city of the trip
     * @param date a string with the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    // Setter 
    /**
     * Setter for the destination country of the trip
     * @param date a string with the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
