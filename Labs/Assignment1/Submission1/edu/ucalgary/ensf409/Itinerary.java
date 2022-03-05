/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
            UCID: 30114566
@version    1.5
@since      1.0
*/

package edu.ucalgary.ensf409;

/*
Copyright Ann Barcomb and Emily Marasco, 2021-2022
All rights reserved.
*/

public class Itinerary {
    private Trip[] trips = new Trip[20];
    //member variable for Itinerary class - holds an array of Trips

    // Returns a string in the format of:
    // value (key)
    /**
     * This function takes a key and a string, and returns a String in the form string (key)
     * @param key the value to go in between the brackets in the return string
     * @param value the value to go at the beginning of the return string
     * @return a new string in the form value (key)
     */
    public static String fmtString(String key, String value) {
        String returnString = new String();
        //create a new String object to hold the string to be returned

        returnString += value;
        //add the value to the new string
        
        returnString += " (";
        returnString += key;
        returnString += ")";

        return returnString;
    }

    // Constructor
    /**
     * Constructor for Itinerary
     * @param myTrips a two-dimensional array containing a list of trips
     */
    public Itinerary(String[][] myTrips) {
        for(int i = 0; i < myTrips.length && i < 20; i++) {
            /* i < myTrips.length ensures the constructor is not called for a 
             * non-existent object, and i < 20 ensures the constructor can
             * only be called when the number of elements is less than the max
             * of 20.
             */

            this.trips[i] = new Trip(myTrips[i]);
            //since each elements of the trips array is a Trip object, call
            //the Trip constructor for each element
        }
    }

    // Getter
    /**
     * getter function for the trips variable
     * @return an array of Trip variables
     */
    public Trip[] getTrips() {
        return this.trips;
    }

    /**
     * Creates a multi-line string of trips sorted by arrival year and month
     * @return a multi-line string of trips by arrival date
     */
    public String formatByArrival() {
        String formattedString = new String();
        //formattedString is the return multi-line string
        
        int currentYear = 0;
        int currentMonth = 0;
        //currentYear and currentMonth are the months and years respectively
        //that the program is currently on

        for(int i = 0; i < trips.length; i++) {
            if(trips[i] == null) {
                break;
                //if at the end of the array of trips, end the loop
            }

            int year = Trip.getYear(trips[i].getArrival());
            //year is the arrival year at the current index of trips
            if(year!= currentYear){
                //if the list has reached a new year, update the current
                //year and add the new heading
                currentYear = year;
                currentMonth = 0;
                //reset currentMonth for the new year

                if(year != 2021) {
                    formattedString += "\n";
                    //if it is not the first year in the list, add a newline character
                }

                formattedString += Trip.fmtString("Year", String.valueOf(currentYear));
            }

            int month = Trip.getMonth(trips[i].getArrival());
            //month is the arrival month at the current index of trips
            if(month != currentMonth) {
                //if the list has reached a new month, update the current
                //month and add the new sub-heading
                currentMonth = month;
                formattedString += "\n--";
                formattedString += Trip.fmtString("Month", String.valueOf(currentMonth));
            }

            formattedString += "\n----";

            String placeStr = trips[i].formatPlace();
            /* placeStr is a temporary string to format the place in the list
             * this is because of the final (Place) at the end, which uses the same
             * fmtString method and requires a string as one of the arguments
             */

            formattedString += Trip.fmtString("Place", placeStr);
            //add the destination to the return string
        }

        return formattedString;
    }

    // The first array holds years (2021-2023).
    // The second array holds months.
    // The third array holds formatted locations occurring in the year/month
    /**
     * Creates a three-dimensional array sorted by arrival year and month.
     * Only works for trips where the arrival and destination months and years are the same
     * @return Three-dimensional array sorted by arrival year and month
     */
    public String[][][] byDate() {
        String[][][] locations = new String[3][12][1];
        //dimensions of locations are for 3 possible years, 12 possible months, and 1 possible string in each

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 12; j++) {
                locations[i][j][0] = "null";
                //default value for the array is "null"
            }
        }

        for(Trip value: trips) {
            if(value == null) {
                //if at the end of the list of trips, end the for loop
                break;
            }

            int arrivalYear = value.getYear(value.getArrival());
            int arrivalMonth = value.getMonth(value.getArrival());
            int departureYear = value.getYear(value.getDeparture());
            int departureMonth = value.getMonth(value.getDeparture());
            //find the arrival and departure years and months using getter methods

            if(arrivalYear != departureYear || arrivalMonth != departureMonth) {
                continue;
                //if the trip has different arrival and departure months or
                    //years, there is no change
            }

            arrivalYear -= 2021;
            arrivalMonth -= 1;
            //translate arrivalYear and arrivalMonth into appropriate array indices
            //since there are only 3 possible years starting at 2021, subtract
                //2021 from the arrival year
            //since there are 12 possible months, subtract 1 to translate to a 
                //valid index

            if(locations[arrivalYear][arrivalMonth][0].contentEquals("null") == true) {
                //only change the valus if there is not another trip there
                locations[arrivalYear][arrivalMonth][0] = value.formatPlace();
            }
        }

        return locations;
    }

}