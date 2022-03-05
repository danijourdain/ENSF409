/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

public class EmergVet {
    private String name;
    private String phoneNum;

    /**
     * Constructor for the EmergeVet object.
     * @param name Name of the emergency vet.
     * @param phoneNumber Phone number of the emergency vet.
     */
    public EmergVet(String name, String phoneNumber) {
        this.name = name;
        this.phoneNum = phoneNumber;
    }

    /**
     * Getter method for the name of the vet.
     * @return The name of the emergency vet.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for the phoen number of the vet.
     * @return The vet's phone number.
     */
    public String getPhoneNum() {
        return this.phoneNum;
    }

}
