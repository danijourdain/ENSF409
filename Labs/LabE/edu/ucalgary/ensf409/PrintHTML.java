/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.0
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.util.*;

public class PrintHTML implements PrintOutput {
    @Override
    public void printStats(GameCharacter theCharacter) {
        String characterName = theCharacter.characterName;
        String characterClass = theCharacter.getCharacterClass();
        System.out.println("<p>Name: " + characterName + "<br />");
        System.out.println("Life: " + theCharacter.lifeforce + "<br />");
        System.out.println("Class: " + characterClass + "<br />");
        System.out.println("Says: " + theCharacter.talk("I am " + characterName + " the " + characterClass + ".") + "<br />");
        System.out.println("Attack speed: " + theCharacter.getAttackPriority() + "<br />");
        System.out.println("Damage: " + theCharacter.getAttackDamage() + "<br />");
        System.out.println("Attack: " + theCharacter.getAttackMessage() + "</p>");
    }

    @Override
    public void printFightLog(ArrayList<String> log) {
        System.out.println("<ul>");
        Iterator<String> i = log.iterator();
        while(i.hasNext()) {
            System.out.println("<li>" + i.next() + "</li>");
        }
        System.out.println("</ul>");
    }

    @Override
    public void printMessage(String message) {
        System.out.println("<p>" + message + "</p>");
    }
    
}
