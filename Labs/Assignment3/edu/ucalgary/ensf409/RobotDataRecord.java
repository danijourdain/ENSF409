/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.util.*;

public class RobotDataRecord implements Cloneable{
    private ArrayList<RobotDataLine> log;

    /**
     * Constructor for RobotDataRecord
     * @param array
     */
    public RobotDataRecord(String[] array) {
        this.log = new ArrayList<>();
        for(String robot: array) {
            RobotDataLine newRobot = new RobotDataLine(robot);
            this.log.add(newRobot);
        }
    }

    /**
     * This method returns the RobotDataLine at a given index.
     * @param index The index to find the record at.
     */
    public RobotDataLine getLine(int index) {
        return this.log.get(index);
    }

    /**
     * This is a getter method for the whole ArrayList log.
     * @return log.
     */
    public ArrayList<RobotDataLine> getDataRecord() {
        return this.log;
    }

    /**
     * Clone method for RobotDataRecord class.
     */
    public Object clone() throws CloneNotSupportedException {
        int size = this.log.size();
        String[] logString = new String[size];

        for(int i = 0; i < size; i++) {
            logString[i] = this.log.get(i).getDataLine();
        }


        RobotDataRecord copy = new RobotDataRecord(logString);
        return copy;
    }
}
