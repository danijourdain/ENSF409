/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.5
@since      1.0
*/

package edu.ucalgary.ensf409;

import java.io.*;
import java.util.regex.*;

public class Translator {
    private TranslationText translated;
    private String fileName;

    private final String FILE_REGEX = "^[a-z]{2}-[A-Z]{2}$";
    private final Pattern FILE_PATTERN = Pattern.compile(FILE_REGEX);

    /**
     * Constructor for the Translator object. Creates a translation out of a .txt or .ser file. 
     * @param code The language/region code to be used. Should be in the form "aa-AA"
     * @throws ArgFileNotFoundException This is thrown if there is no file corresponding to the code in the correct fomat.
     */
    public Translator(String code) throws ArgFileNotFoundException {
        Matcher checkName = FILE_PATTERN.matcher(code);
        //use a regex to check if the code is in a valid format

        if(checkName.find() == true) {
            this.fileName = code;
            importTranslation();
            //use the importTranslation method to get the desired translation
        }
        else {
            throw new IllegalArgumentException("Invalid file name entered. Must be entered in the form \"aa-AA\"");
        }
    }

    /**
     * This is a getter method for the TranslationText stored in the object.
     * @return The TranslationText object.
     */
    public TranslationText getTranslation() {
        return this.translated;
    }

    /**
     * This method creates a formatted string in the desired language with the desired dates.
     * @param monthNum The desired month for the translation.
     * @param dayNum The desired day for the translation.
     * @param yearNum The desired year for the translation.
     * @return A sentence using the desired date in the desired language.
     */
    public String translate(int monthNum, int dayNum, int yearNum) {
        if(monthNum < 1 || monthNum > 12) {
            throw new IllegalArgumentException("Invalid month, must be between 1 and 12");
        }
        if(dayNum < 1 || dayNum > 31) {
            throw new IllegalArgumentException("Invalid day. Must be between 1 and 31");
        }
        //throw an IllegalArgumentException if one or more of the parameters are invalid. 
        
        String sentence = this.translated.getSentence();
        String day = this.translated.getDay(dayNum - 1);
        String month = this.translated.getMonth(monthNum - 1);
        //get the sentence, day, and month from the TranslationText object.

        return String.format(sentence, day, month, yearNum);
    }

    /**
     * This method creates a translation from either a .txt file or a .ser file.
     * @throws ArgFileNotFoundException Thrown if the desired code does not exist as either type of file.
     */
    public void importTranslation() throws ArgFileNotFoundException{
        File serFile = new File(this.fileName + ".ser");
        //create a File object to see if the desired code exists as a .ser file
        
        if(serFile.isFile()) {
            deserialize();
            //check if it exists as a .ser file first
        }
        else{
            importFromText();
            //if it does not exist as a .txt file, try import from a text file
        }
    }

    /**
     * This method imports a translation from a text file. 
     * @throws ArgFileNotFoundException Thrown if the desired code does not exist as a .txt file. 
     */
    public void importFromText() throws ArgFileNotFoundException {
        File textFile = new File(this.fileName + ".txt");

        if(textFile.isFile() == false) {
            throw new ArgFileNotFoundException("Files " + this.fileName + ".txt and " + this.fileName + ".ser both do not exist");
        }
        //check if the desired code exists as a text file.

        BufferedReader reader;
        String line;
        //these two variables will be used to read from the text file

        String[] fileContents = new String[44];
        int i = 0;
        //the string array will be used to hold the contents of the text file
            //and i will be used to iterate through it

        try {
            reader = new BufferedReader(new FileReader(this.fileName + ".txt"));
            while((line = reader.readLine()) != null) {
                fileContents[i] = line;
                i++;
            }
            reader.close();
            //read all contents into the fileContents array and close the BufferedReader
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String[] months = new String[12];
        String[] days = new String[31];
        i = 0;

        for(int j = 0; j < 12; j++, i++) {
            months[j] = fileContents[i];
        }
        for(int j = 0; j < 31; j++, i++) {
            days[j] = fileContents[i];
        }
        //transfer the contents into separate arrays for days and months

        this.translated = new TranslationText(months, days, fileContents[i]);
        //create a new TranslationText object to hold the information
    }

    /**
     * This method serializes a TranslationText object.
     */
    public void serialize() {
        try {
            FileOutputStream fileOutput = new FileOutputStream(this.fileName + ".ser");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            //create the new file and the output stream

            objectOutput.writeObject(this.translated);

            fileOutput.close();
            objectOutput.close();
            //close the streams when done
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * This method reads the data stored in a .ser file and creates a TranslationText object from it.
     */
    public void deserialize() {
        try {
            FileInputStream fileInput = new FileInputStream(this.fileName + ".ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            //create the input streams for reading the file

            this.translated = (TranslationText) objectInput.readObject();

            fileInput.close();
            objectInput.close();
            //close the files when finished
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}