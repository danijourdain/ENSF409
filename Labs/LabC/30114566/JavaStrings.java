/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.4
@since      1.0
*/

import java.util.regex.*;

public class JavaStrings {
    private static Pattern SECRET_CODE_REGEX;

    /**
     * Example main for JavaStrings class. Tests 3 different methods that manipulate and handle various Strings
     * @param args Ignored commmand-line arguments
     */
    public static void main(String[] args) {
        JavaStrings myObject = new JavaStrings();

        // Print out examples from addTogether.
        String oneExample = "12 4 6789";
        String twoExample = "abcdef gh";
        int theLength = myObject.addTogether(oneExample,twoExample);
        System.out.println(theLength);

        // Length is unchanged by adding whitespace to front and back
        oneExample = "   " + oneExample + "\n";
        twoExample = "\t" + twoExample;
        theLength = myObject.addTogether(oneExample,twoExample);
        System.out.println(theLength);

        oneExample = "   hello THERE     \n";
        twoExample = "          everyone";
        theLength = myObject.addTogether(oneExample,twoExample);
        System.out.println(theLength);

        // Print out example of idProcessing
        String personFirst = "Dorothy";
        String personLast = "Gale";
        String petName = "Toto";
        int petBirth = 1899;
        String theID = myObject.idProcessing(personFirst,personLast,
                       petName,petBirth);
        System.out.println(theID);

        personFirst = "joe";
        personLast = "smith";
        petName = "Fluffy";
        petBirth = 2010;
        theID = myObject.idProcessing(personFirst,personLast, petName,petBirth);
        System.out.println(theID);

        // Print out examples from secretCode
        String ingredientOne = "tomato";
        String ingredientTwo = "WATER";
        String ingredientThree = "ceese";
        String theCode = myObject.secretCode(ingredientOne);
        System.out.println(theCode);
        theCode = myObject.secretCode(ingredientTwo);
        System.out.println(theCode);
        theCode = myObject.secretCode(ingredientThree);
        System.out.println(theCode);
    }

    /**
     * This method will take two strings, remove all leading and trailing whitespace from both, concatanate them, 
     * then return the length of the new string
     * @param firstString The first string that will be added to the new, combined string
     * @param secondString The second string that will be added to the new, combined string
     * @return returns the length of the new combined string
     */
    public int addTogether(String firstString, String secondString) {
        String combinedString = new String();

        combinedString += firstString.strip();
        combinedString += secondString.strip();
        //the strip method will remove all leading and trailing whitespace
            //from both strings before adding them to combinedString

        return combinedString.length();
    }

    /**
     * This method takes information from an owner and their pet and created an ID for them
     * @param ownerFirst The owner's first name
     * @param ownerLast The owner's last name
     * @param petName The pet's name
     * @param petBirthYear The pet's year of birth
     * @return An ID for the pet and owner composed of the first letter of the owner's first name, 
     * then the first letter of the owner's last name, followed by the first letter of the pet's name,
     * finally followed by all 4 digits of the year the pet was born
     */
    public String idProcessing(String ownerFirst, String ownerLast, String petName, int petBirthYear) {
        String newID = new String();

        newID += ownerFirst.substring(0, 1);
        newID += ownerLast.substring(0, 1);
        newID += petName.substring(0, 1);
        //these 3 lines take the first letter of the owner first and last names, as well as the pet name
            //and add them to the ID in that order

        newID += Integer.toString(petBirthYear);
        //this converts the pet's birth year to a string and adds it to the end of the ID

        return newID;
    }

    /**
     * This method takes an ingredient, replaces all vowels with "z" and then returns the first 3 letters of the new string
     * @param ingredient The original ingredient to be encoded
     * @return The secret code of the ingredient
     */
    public String secretCode(String ingredient) {
        SECRET_CODE_REGEX = Pattern.compile("[aeiouAEIOU]");
        Matcher matchVowels = SECRET_CODE_REGEX.matcher(ingredient);
        //create a Matcher variable to replace all vowels

        String replacedVowels = matchVowels.replaceAll("z");
        System.out.println(replacedVowels);
        //use the replaceAll method to replace all vowels with a lowercase z
        
        SECRET_CODE_REGEX = Pattern.compile("^\\w{3}");
        Matcher firstThree = SECRET_CODE_REGEX.matcher(replacedVowels);
        firstThree.find();
        //create a second Matcher variable to find the first 3 characters

        return firstThree.group();
        //return the first 3 characters
    }

    public static Pattern getSecretCodeRegex() {
        return SECRET_CODE_REGEX;
    }
}
