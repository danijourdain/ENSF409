/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.util.regex.*;

public class Movement implements Cloneable, FormattedOutput {
    private String action;
    private String direction;

    private static final String REGEX = "\"([A-Z]+) - ([A-Z]{1,2})";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * Constructor for Movement
     * @param movement
     * @throws IllegalArgumentException
     */
    public Movement(String movement) throws IllegalArgumentException {
        Matcher movementMatch = PATTERN.matcher(movement);

        if(movementMatch.find()) {
            for(Actions action: Actions.values()) {
                if(movementMatch.group(1).equals(String.valueOf(action))) {
                    this.action = movementMatch.group(1);
                }
            }

            for(Directions direction: Directions.values()) {
                if(movementMatch.group(2).equals(direction.name())) {
                    this.direction = movementMatch.group(2);
                }
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Getter method for action.
     * @return value in action.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Getter method for direction.
     * @return value in direction.
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * Clone method for Movement.
     */
    public Object clone() throws CloneNotSupportedException {
        Movement copy = (Movement)super.clone();
        return copy;
    }

    /**
     * Overrides the getFormatted method in FormattedOuput interface
     * @return
     */
    @Override
    public String getFormatted() {
        StringBuilder output = new StringBuilder();
        output.append("Action: " + this.action);
        output.append(", Direction: " + this.direction.toString());

        return output.toString();
    }
}