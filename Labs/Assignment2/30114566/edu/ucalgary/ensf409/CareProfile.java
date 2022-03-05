/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.3
@since      1.0
*/

package edu.ucalgary.ensf409;

public class CareProfile {
    private String[] medList;
    private String medInstructions;
    private String feedingInstructions;

    /**
     * Constructor for the CareProfile of the animal.
     * @param medList The list of medication the pet must take.
     * @param meds The instructions for the pet's medication.
     * @param feeding The pet's instructions for feeding.
     */
    public CareProfile(String[] medList, String meds, String feeding) {
        this.medList = medList;
        this.medInstructions = meds;
        this.feedingInstructions = feeding;
    }

    /**
     * This method creates a String to summarize the care instructions
     * for a pet.
     * @return A multi-line String that summarizes the care for a pet.
     */
    public String summarizeCareInstructions() {
        String careInstructions = new String();

        for(int i = 0; i < this.medList.length; i++) {
            careInstructions = careInstructions.concat(medList[i]);
            //add the medicine to the String
            
            if(i < this.medList.length - 1) {
                careInstructions = careInstructions.concat(", ");
                //if this is not the last medication on the list, add a comma 
                    //to separate entries
            }
        }
        careInstructions = careInstructions.concat("\n" + this.medInstructions);
        careInstructions = careInstructions.concat("\n" + this.feedingInstructions);
        //add the medInstructions and feedingInstructions on their own lines

        return careInstructions;
    }
    
}
