/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.5
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.util.regex.*;

public class RewardsProfile {
    private String rewardsNum;
    private int pointsTotal;

    /**
     * Default constructor for RewardsProfile.
     */
    public RewardsProfile() {
        this.rewardsNum = "Not enrolled";
        this.pointsTotal = 10;
    }

    /**
     * Overloaded Constructor for RewardsProfile. 
     * @param newNumber The new rewardsNum to be tested.
     * @throws InvalidRewardsNumException If the rewardsNum has characters other
     * than digits, an exception is thrown.
     */
    public RewardsProfile(String newNumber) throws InvalidRewardsNumException{
        Pattern isDigit = Pattern.compile("^\\d{7}$");
        //a valid rewardsNum will consist of 7 digits and no letters

        Matcher match = isDigit.matcher(newNumber);
        if(match.find() == false) {
            //if the new numeber is ivalid throw an exception
            throw new InvalidRewardsNumException();
        }

        this.rewardsNum = newNumber;
        this.pointsTotal = 10;
        //if there is not an issue, set the new rewardsNum
    }

    /**
     * Setter method for pointsTotal.
     * @param addPoints new pointsTotal.
     */
    public void setPoints(int addPoints) {
        this.pointsTotal = addPoints;
    }

    /**
     * Getter method for pointsTotal.
     * @return Current value of pointsTotal.
     */
    public int getPoints() {
        return this.pointsTotal;
    }
    
    /**
     * Getter method for rewardsNum.
     * @return rewardsNum String.
     */
    public String getNumber() {
        return this.rewardsNum;
    }
}