/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

/**
 * Class which describes a residential property in Calgary
**/

package edu.ucalgary.ensf409;

public class ResidentialProperty extends CalgaryProperty {
    String description;

    /** Constructors **/

    public ResidentialProperty(String description, int taxRollNumber, String streetName, int buildingNumber, String postCode) throws IllegalArgumentException {
        super(taxRollNumber, "RESIDENTIAL", streetName, buildingNumber, postCode);
        this.description = description;
    }

    public ResidentialProperty(String description, int taxRollNumber, String streetName, 
        int buildingNumber, String postCode, String buildingAnnex) throws IllegalArgumentException {
            
            super(taxRollNumber, "RESIDENTIAL", streetName, buildingNumber, postCode, buildingAnnex);
            this.description = description;
    }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String formatOutput() {
        StringBuilder output = new StringBuilder();
        String address = this.buildingNumber + " " + this.streetName;
        output.append(StandardFormatting.formatOutput("Description", this.description) + "\n");


        if(this.getBuildingAnnex() != null) {
            output.append(StandardFormatting.formatOutput("Address", address + " " + this.getBuildingAnnex()));
        }
        else {
            output.append(StandardFormatting.formatOutput("Address", address));
        }

        output.append(StandardFormatting.formatOutput("\nPostcode", this.postCode));
        return output.toString(); 
    }

    public static  String formatOutput(String key, String value) {
        return StandardFormatting.formatOutput(key, value);
    }
}
