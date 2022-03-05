/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.3
@since      1.0
*/

package edu.ucalgary.ensf409;

public class Client {
    private String name;
    private String phoneNumber;
    private String address;
    private RewardsProfile rewardsInfo;

    /**
     * Constructor for the Client class. Takes a name, phone number, and
     * address to create the Client profile.
     * @param name Name of the client.
     * @param phoneNumber Client's phone number.
     * @param address Client's home address.
     */
    public Client(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.rewardsInfo = new RewardsProfile();
    }
    
    /**
     * This method attempts to enroll the Client in the rewards program.
     * In order for enrollment to be successful, newNumber must be a string 
     * containing 7 digits.
     * @param newNumber The rewards number to be registered for the Client
     * @return It will return true if enrollment it successfull and false if it fails.
     */
    public boolean enrollRewards(String newNumber) {
        try {
            this.rewardsInfo = new RewardsProfile(newNumber);
        } catch(InvalidRewardsNumException e) {
            return false;
            //return false if an exception is thrown
        }
        //use a try-catch block to check if creating a new RewardsProfile will throw an exception

        return true;
        //if there is no error, return true
    }

    /**
     * Get the number of points on the Client's reward profile
     * @return The current point total for the Client's RewardsProfile
     */
    public int getRewardsPoints() {
        return this.rewardsInfo.getPoints();
        //use the getter method from RewardsProfile to get the point value
    }

    /**
     * Get the rewards number on the Client's reward profile.
     * @return
     */
    public String getRewardsNumber() {
        return this.rewardsInfo.getNumber();
        //use the getter method from the RewardsProfile class
    }

    /**
     * Getter method for the Client's name.
     * @return The name of the Client.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method for the Client's name.
     * @param name The new name for the Client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the Client's phone number.
     * @return The Client's phone number.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Setter method for the Client's phone number.
     * @param num The new phone number for the Client.
     */
    public void setPhoneNumber(String num) {
        this.phoneNumber = num;
    }

    /**
     * Getter method for the Client's address.
     * @return The Client's address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter method for the Client's address.
     * @param address The Client's new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method will update the points total in the Client's RewardsProfile.
     * @param addPoints The number of points to add to the total.
     */
    public void updatePoints(int addPoints) {
        int currentPoints = this.rewardsInfo.getPoints();
        //get the current number of points

        this.rewardsInfo.setPoints(currentPoints + addPoints);
        //use the setter method to set the points as the new total
    }

}
