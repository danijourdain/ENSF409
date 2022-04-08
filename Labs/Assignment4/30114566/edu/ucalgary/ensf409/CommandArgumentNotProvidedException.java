/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.1
@since      1.0
*/

package edu.ucalgary.ensf409;

public class CommandArgumentNotProvidedException extends Exception {
    public CommandArgumentNotProvidedException() {
        super("No command-line argument was provided");
    }
}
