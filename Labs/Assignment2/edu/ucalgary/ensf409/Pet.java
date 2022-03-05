/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.3
@since      1.0
*/

package edu.ucalgary.ensf409;

public class Pet {
    private final String NAME;
    private final String SPECIES;
    private final String BREED;
    private final String COLOUR;
    private boolean vaccineStatus;
    private Client owner;
    private EmergVet vet;
    private CareProfile care;

    /**
     * Constructor for Pet.
     * @param name Name of the pet.
     * @param species Species of the pet.
     * @param breed Breed of the pet.
     * @param colour Colour of the pet.
     * @param owner The Client who owns the pet.
     */
    public Pet(String name, String species, String breed, String colour, Client owner) {
        this.NAME = name;
        this.SPECIES = species;
        this.BREED = breed;
        this.COLOUR = colour;
        this.owner = owner;
        this.vaccineStatus = false;
    }

    /**
     * Setter method for the Emergency Vet of the Pet.
     * @param vet The vet to be set as the vet for the Pet.
     */
    public void setVet(EmergVet vet) {
        this.vet = vet;
    }

    /**
     * Getter method for the Pet's vet.
     * @return The Pet's emergency vet.
     */
    public EmergVet getVet() {
        return this.vet;
    }

    /**
     * Method to update the Pet's current vaccine status.
     * @param change The value to change the Pet's vaccine status to.
     */
    public void updateVaccineStatus(boolean change) {
        this.vaccineStatus = change;
    }

    /**
     * Getter method for the Pet's vaccine status.
     * @return The Pet's current vaccine status.
     */
    public boolean getVaccineStatus() {
        return this.vaccineStatus;
    }

    /**
     * This method sets the care profile for the pet.
     * @param meds The list of meds the pet needs to take.
     * @param medInstr The instructions for the pet's medications.
     * @param feedingInstr The instructions for feeding the pet.
     */
    public void setCare(String[] meds, String medInstr, String feedingInstr) {
        this.care = new CareProfile(meds, medInstr, feedingInstr);
    }

    /**
     * Getter method for the Care summary for the Pet.
     * @return A String containing a summary of the Care instructions and
     * the Pet's name as a header.
     */
    public String getCareSummary() {
        String careSummary = new String();
        careSummary = careSummary.concat("Care for " + this.NAME + ":\n");
        careSummary = careSummary.concat(this.care.summarizeCareInstructions());

        return careSummary;
    }

    /**
     * Getter method for the Pet's owner.
     * @return The Pet's owner as a Client.
     */
    public Client getOwner() {
        return this.owner;
    }

    /**
     * Setter method for the Pet's owner.
     * @param updateOwner The updated Client information for the owner.
     */
    public void setOwner(Client updateOwner) {
        this.owner = updateOwner;
    }

    /**
     * Getter method for the Pet's name.
     * @return The Pet's name.
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * Getter method for the Pet's species.
     * @return The Pet's species.
     */
    public String getSpecies() {
        return this.SPECIES;
    }

    /**
     * Getter method for the Pet's breed.
     * @return The Pet's breed.
     */
    public String getBreed() {
        return this.BREED;
    }

    /**
     * Getter method for the Pet's colour.
     * @return The Pet's colour.
     */
    public String getColour() {
        return this.COLOUR;
    }
    
}
