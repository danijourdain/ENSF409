/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.3
@since      1.0
*/

package edu.ucalgary.ensf409;

public class ReportCard {
    private final Booking REPORT;

    /**
     * Constructor for ReportCard.
     * @param reportInfo The booking to create a report card from.
     */
    public ReportCard(Booking reportInfo) {
        this.REPORT = reportInfo;
    }

    /**
     * This method will print a brief report on the pet's stay.
     * @return A String containing the report.
     */
    public String printReport() {
        String report = new String();

        report =report.concat(this.REPORT.getCaregiver().getName());
        //add the employee's name

        report =report.concat(" enjoyed taking care of ");
        report =report.concat(this.REPORT.getBookedPet().getName());
        //add the pet's name

        report =report.concat(". See you next time!");
        return report;
    }
}
