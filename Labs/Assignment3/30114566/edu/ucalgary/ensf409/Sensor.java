/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.util.regex.*;

public class Sensor implements Cloneable, FormattedOutput{
    private String sensor;

    private static final String REGEX  = "\\(([a-z]+)\\)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * Constructor for Sensor
     * @param sensor
     * @throws IllegalArgumentException
     */
    public Sensor(String sensor) throws IllegalArgumentException {
        Matcher sensorMatch = PATTERN.matcher(sensor);

        if(sensorMatch.find()) {
            this.sensor = sensorMatch.group(1);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Getter method for sensor
     * @return value of sensor
     */
    public String getSensor() {
        return this.sensor;
    }

    /**
     * Clone method for Sensor
     */
    public Object clone() throws CloneNotSupportedException {
        Sensor copy = (Sensor)super.clone();
        return copy;
    }

    @Override
    public String getFormatted() {
        String output = "Sensor: " + this.sensor;
        return output;
    }
    
}
