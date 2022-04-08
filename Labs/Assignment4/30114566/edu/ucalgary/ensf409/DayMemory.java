/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.1
@since      1.0
*/

package edu.ucalgary.ensf409;

/* main()
 * Accept a command-line argument which specifies a translation file.
 * The argument should be in the form of a two-letter language code,
 * followed by a dash and a two-letter region code, e.g., en-US
 * which corresponds with files en-US.txt and en-US.ser
 * If no argument is specified, it throws a custom exception,
 * CommandArgumentNotProvidedException, which extends Exception. 
 * Additional arguments are ignored.
 */

public class DayMemory {
    /**
     * Main method for Assignment 4.
     * @param args Command-line arguments.
     * @throws Exception Thrown if an error occurs.
     */
    public static void main(String[] args) throws Exception{
        if(args.length == 0) {
            throw new CommandArgumentNotProvidedException();
        }

        Translator translate = new Translator(args[0]);

        /*
        //the following lines are just used to test the code
        System.out.println(translate.translate(3, 8, 2021));
        System.out.println(translate.translate(7, 26, -400));
        System.out.println(translate.translate(10, 9, 123));
        translate.serialize();

        testing(translate);
        */
    }

    /*
    public static void testing(Translator translate) {
        System.out.println(translate.getTranslation().getSentence());
        System.out.println(translate.getTranslation().getMonth(3));
        System.out.println(translate.getTranslation().getDay(16));
    }
    */
}
