/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.io.Serializable;

public class TranslationText implements Serializable {
    static final long serialVersionUID = 19;

    private String[] months, days;
    private String sentence;

    /**
     * Constructor for the TranslationText object.
     * @param months An array storing the months of the year in order.
     * @param days An array storing the possible days of a month in order.
     * @param formattedSentence A formatted string which needs a day, month, and year to be complete
     */
    public TranslationText(String[] months, String[] days, String formattedSentence) {
        this.months = months;
        this.days = days;
        this.sentence = formattedSentence;
    }

    /**
     * Getter method for the sentence.
     * @return The formatted String sentence.
     */
    public String getSentence() {
        return this.sentence;
    }

    /**
     * Getter method for the month array.
     * @return The array of months.
     */
    public String[] getMonths() {
        return this.months;
    }

    /**
     * Getter method for the day array.
     * @return The array of days.
     */
    public String[] getDays() {
        return this.days;
    }

    /**
     * This method gets a particular month of the year from the month array.
     * @param month The number corresponding to the desired month (chosen from 0-11).
     * @return The desired month as a string.
     */
    public String getMonth(int month) {
        if(month < 0 || month > 11) {
            throw new IllegalArgumentException("Given month is invalid. Must be between 0-11");
        }
        return this.months[month];
    }

    /**
     * This method gets a particular day from the day array.
     * @param day The number corresponding to the desired day (chosen from 0-30).
     * @return The desired day as a string.
     */
    public String getDay(int day) {
        if(day < 0 || day > 30) {
            throw new IllegalArgumentException("Given day is invalid. Must be between 0-30");
        }
        return this.days[day];
    }
}
