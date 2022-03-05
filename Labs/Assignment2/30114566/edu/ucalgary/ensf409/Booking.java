/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

public class Booking {
    private final String START_DATE;
    private final String END_DATE;
    private final Pet BOOKED_PET;
    private final Employee CAREGIVER;

    /**
     * Constructor for the Booking.
     * @param pet The pet to be booked in.
     * @param assigned The employee that will be caring for the pet.
     * @param startDate The start date of the animal's stay.
     * @param endDate The end date of the animal's stay.
     */
    public Booking(Pet pet, Employee assigned, String startDate, String endDate) {
        this.START_DATE = startDate;
        this.END_DATE = endDate;
        this.BOOKED_PET = pet;
        this.CAREGIVER = assigned;
    }

    /**
     * Getter method for the start date.
     * @return The start date of the pet's visit.
     */
    public String getStartDate() {
        return this.START_DATE;
    }

    /**
     * Getter method for the end date.
     * @return The end date of the pet's visit.
     */
    public String getEndDate() {
        return this.END_DATE;
    }

    /**
     * Getter method for the pet that was booked.
     * @return The pet that was booked in.
     */
    public Pet getBookedPet() {
        return this.BOOKED_PET;
    }

    /**
     * Getter method for the Employee taking care of the pet.
     * @return The employee caring for the pet during their stay.
     */
    public Employee getCaregiver() {
        return this.CAREGIVER;
    }
    
}
