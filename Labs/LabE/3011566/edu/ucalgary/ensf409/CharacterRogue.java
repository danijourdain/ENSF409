/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.2
@since      1.0
*/

package edu.ucalgary.ensf409;

public class CharacterRogue extends GameCharacter{
    private String weapon = new String("knife");

    /**
     * Constructor for the CharacterRogue class. Uses the constructor from parent 
     * class GameCharacter.
     * @param characterName The name of the character.
     * @param attackPriority Their attack priority.
     */
    public CharacterRogue(String characterName, int attackPriority) throws IllegalArgumentException {
        super(characterName, "rogue", attackPriority, 10);
    }

    /**
     * Getter method for the character's weapon
     * @return The character's weapon.
     */
    public String getWeapon() {
        return this.weapon;
    }

    /**
     * This method takes an input message and formats it for the character.
     * @param message The message to be formatted.
     * @return ...(<message>)...
     */
    public String talk(String message) {
        String returnMsg = "....(" + message + ")....";
        return returnMsg;
    }

    /**
     * This method creates the attack method for a character.
     * @return <characterName> attacks with their <weapon>. 
     */
    @Override
    public String getAttackMessage() {
        String message = this.getCharacterName() + " attacks with their " + this.getWeapon()  + ".";
        return message;
    }
    
}
