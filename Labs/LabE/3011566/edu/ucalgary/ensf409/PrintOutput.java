/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.0
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.util.*;

public interface PrintOutput{
    void printStats(GameCharacter theCharacter);

    void printFightLog(ArrayList<String> log);

    void printMessage(String message);
}
