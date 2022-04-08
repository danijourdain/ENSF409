/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.util.regex.*;
import java.time.*;

public class RobotDataLine implements Cloneable{
    private String dataLine;
    private String robotID;
    private Sensor sensor;
    private Movement movement;
    private LocalDate date;

    private static final String DATE_REGEX = "\\[([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})\\]";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);

    private static final String ROBOT_REGEX = "\\s([0-9]{3}[A-Z]{1})\\s";
    private static final Pattern ROBOT_PATTERN = Pattern.compile(ROBOT_REGEX);

    /**
     * Constructor for RobotDataLine.
     * @param line Line of data to be broken down into different parts.
     * @throws IllegalArgumentException 
     */
    public RobotDataLine(String line) throws IllegalArgumentException {
        this.dataLine = line;

        Matcher findDate = DATE_PATTERN.matcher(line);
        if(findDate.find()) {
            int year = Integer.parseInt(findDate.group(3));
            int month = Integer.parseInt(findDate.group(2));
            int day = Integer.parseInt(findDate.group(1));
            try {
                this.date = LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                throw new IllegalArgumentException("Date entered is invalid");
            }
        }
        else {
            throw new IllegalArgumentException();
        }

        Matcher findID = ROBOT_PATTERN.matcher(line);
        if(findID.find()) {
            this.robotID = findID.group(1);
        }
        else {
            throw new IllegalArgumentException();
        }

        this.movement = new Movement(line);
        this.sensor = new Sensor(line);
    }

    /**
     * Getter method for robotID.
     * @return robotID.
     */
    public String getRobotID() {
        return this.robotID;
    }

    /**
     * Getter method for dataLine
     * @return dataLine
     */
    public String getDataLine() {
        return this.dataLine;
    }

    /**
     * Getter method for sensor
     * @return sensor
     */
    public Sensor getSensor() {
        return this.sensor;
    }

    /**
     * Getter method for movement
     * @return movement.
     */
    public Movement getMovement() {
        return this.movement;
    }

    /**
     * Getter method for date
     * @return
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Clone method for RobotDataLine class
     */
    public Object clone() throws CloneNotSupportedException {
        Sensor sensor = (Sensor)this.sensor.clone();
        Movement movement = (Movement)this.movement.clone();

        RobotDataLine copy = (RobotDataLine)super.clone();
        
        copy.setMovement(movement);
        copy.setSensor(sensor);

        return copy;
    }

    /**
     * helper method for clone
     * @param sensor the sensor to be set
     */
    private void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /**
     * helper method for clone
     * @param movement the movement to be set
     */
    private void setMovement(Movement movement) {
        this.movement = movement;
    }
}
