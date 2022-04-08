/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;


public class DefaultTest {
    
    // Test data - these values may be changed in actual tests
    private final String TEST_FILE_NAME = "example.txt";
    private final String[] EXAMPLE_DATA = {"Y.Y.R","e.e.B","T.M.b"};
    
    /*
     * StitchPattern(File) is called with a file which cannot be read.
     * readFile() returns false and does not throw an exception.
    */
    @Test
    public void testReadFileReturnsFalseBadInput() {
        // Test data - these values may be changed in actual tests 
        File noSuchFile = new File("this_file_does_not_exist.txt");

        // Variables for status
        boolean returnIsTrue = true;
        boolean exceptionThrown = false;

        // Create object, read file
        StitchPattern pattern = new StitchPattern(noSuchFile);
        try {
            returnIsTrue = pattern.readFile();
        }
        catch (Exception e) {
            exceptionThrown = true;
        }

        assertFalse("An unexpected exception was thrown when the file couldn't be read.", exceptionThrown);
        assertFalse("readFile() returned true when operation failed due to file not existing.", returnIsTrue);
    }

    
    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * readFile() returns true and does not throw an exception.
    */
    @Test
    public void testReadFileReturnsTrueGoodInput() {
        
        createTestFile();
        File in = new File(TEST_FILE_NAME);

        // Variables for status
        boolean returnIsTrue = true;
        boolean exceptionThrown = false;
        
        // Create object, read file
        StitchPattern pattern = new StitchPattern(in);
        try {
            returnIsTrue = pattern.readFile();
        }
        catch (Exception e) {
            exceptionThrown = true;
        }

        assertFalse("An unexpected exception was thrown when reading a valid input file.", exceptionThrown);
        assertTrue("readFile() returned false when operation should have been successful.", returnIsTrue);
    }
    
    
    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * readFile() returns true and does not throw an exception.
     * getPattern() returns an ArrayList<ArrayList<Character>> of the contents of the file.
    */
    @Test
    public void testReadFileFillsContents() {
        
        createTestFile();
        File in = new File(TEST_FILE_NAME);

        // Variables for status
        boolean returnIsTrue = true;
        boolean exceptionThrown = false;
        
        StringBuilder tmp = new StringBuilder();;
        
        for (int i=0; i<EXAMPLE_DATA.length; i++) {
            tmp.append(EXAMPLE_DATA[i]+"\n");
        }
        String expectedContents = tmp.toString();

        // Create object, read file
        StitchPattern pattern = new StitchPattern(in);
        try {
            returnIsTrue = pattern.readFile();
        }
        catch (Exception e) {
            exceptionThrown = true;
        }
        
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = removeWindowsNewlines(convertToString(actualPattern));

        assertFalse("An unexpected exception was thrown when reading a valid input file.", exceptionThrown);
        assertTrue("readFile() returned false when operation should have been successful.", returnIsTrue);
        assertEquals("Value of contents did not match what was expected: ", expectedContents, actualContents);
    }
    
    
    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
    */
    @Test
    public void testSetGetPattern() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "Y.y.R\nb.e.E\nP.m.c\n";
        
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected: ", givenData, actualContents);
    }
    

    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * mirrorHorizontal() flips and adds each element to the left of itself. Only alphanumeric characters and punctuation are used.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
    */
    @Test
    public void testHorizontalMirroringSimple() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "Y.Y\nb.e.b\nP.m.P\n";
        String expectedData = "Y.YY.Y\nb.e.bb.e.b\nP.m.PP.m.P\n";
        
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.mirrorHorizontal();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after horizontal mirroring: ", expectedData, actualContents);
    }
    
    
    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * mirrorVertical() flips and adds the full list above itself. Only alphanumeric characters and punctuation are used.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
    */
    @Test
    public void testVerticalMirroringSimple() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "Y.Y\nb.e.b\nP.m.P\n";
        String expectedData = "P.m.P\nb.e.b\nY.Y\nY.Y\nb.e.b\nP.m.P\n";
            
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.mirrorVertical();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after vertical mirroring: ", expectedData, actualContents);
    }


    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * Both of the horizontal and vertical mirrors are applied. Only alphanumeric characters and punctuation are used.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
    */
    @Test
    public void testBothMirrors() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "Y.y.R\nb.e.E\nP.m.c\n";
        String expectedData = "c.m.PP.m.c\nE.e.bb.e.E\nR.y.YY.y.R\nR.y.YY.y.R\nE.e.bb.e.E\nc.m.PP.m.c\n";
        
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.mirrorBoth();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after both horizontal and vertical mirroring: ", expectedData, actualContents);
    }
    
    
    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * mirrorHorizontal() flips and adds each element to the left of itself. Symbols are included in the data.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
    */
    @Test
    public void testHorizontalMirroringSymbols() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "YΣy- R\nb√e√E\nPm$%c\n";
        String expectedData = "R -yΣYYΣy- R\nE√e√bb√e√E\nc%$mPPm$%c\n";
        
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.mirrorHorizontal();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after both horizontal mirroring: ", expectedData, actualContents);
    }
    
    
    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * mirrorVertical() flips and adds the full list above itself. Symbols are included in the data.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
    */
    @Test
    public void testVerticalMirroringSymbols() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "YΣy- R\nb√e√E\nPm$%c\n";
        String expectedData = "Pm$%c\nb√e√E\nYΣy- R\nYΣy- R\nb√e√E\nPm$%c\n";
            
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.mirrorVertical();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after vertical mirroring: ", expectedData, actualContents);
    }


    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * Both of the horizontal and vertical mirrors are applied. Symbols are included in the data.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
    */
    @Test
    public void testBothMirrorsSymbols() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "YΣy- R\nb√e√E\nPm$%c\n";
        String expectedData = "c%$mPPm$%c\nE√e√bb√e√E\nR -yΣYYΣy- R\nR -yΣYYΣy- R\nE√e√bb√e√E\nc%$mPPm$%c\n";
                
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.mirrorBoth();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after both horizontal and vertical mirroring: ", expectedData, actualContents);
    }


    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * There should be no change to the data if parseData() is called again after setting.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
     * Only alphanumeric characters and punctuation are used in the data.
    */
    @Test
    public void testParseDataSimple() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "Y.y.R\nb.e.E\nP.m.c\n";
                
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.parseData();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after parsing is applied: ", givenData, actualContents);
    }
    

    /*
     * StitchPattern(File) is called with a file which can be read (in working directory).
     * The file is not actually used. setPattern(String) is used to set and parse the data.
     * There should be no change to the data if parseData() is called again after setting.
     * getPattern() returns a ArrayList<ArrayList<Character>> of the contents of the data.
     * Symbols are included in the data.
    */
    @Test
    public void testParseDataSymbols() {
        
        // Test data - these values may be changed in actual tests 
        String givenData = "Y√y&R\nb#e@E\nP-m-c\n";
                
        // Create object, parse data
        StitchPattern pattern = new StitchPattern(new File("some_file_name.txt"));
        pattern.setPattern(givenData);
        pattern.parseData();
        ArrayList<ArrayList<Character>> actualPattern = pattern.getPattern();
        String actualContents = convertToString(actualPattern);

        assertEquals("Value of contents did not match what was expected after parsing is applied: .", givenData, actualContents);
    }
    

    
    
    /* ******************* HELPER METHODS ***************** */

    /*
    * Create a test file to read in (in working directory)
    */
    private void createTestFile() {
        File outFile = new File(TEST_FILE_NAME); 
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
            for (int i=0; i < EXAMPLE_DATA.length; i++) {
                out.write(EXAMPLE_DATA[i], 0, EXAMPLE_DATA[i].length());
                out.newLine();
            }
            out.close();
        }
        catch (Exception e) { } // Nothing meaningful to do with an error
    }

    private String convertToString(ArrayList<ArrayList<Character>> inputList){
        
        StringBuilder stringList = new StringBuilder();
                
        for (int row = 0; row < inputList.size(); row++){
            for (Character ch : inputList.get(row)){
                stringList.append(ch);
            }
            stringList.append('\n');
        }
                
        return stringList.toString();
    }
    
    /*
    * Clean up by removing the test file
    */
    @After
    public void removeTestFile() {
        File bye = new File(TEST_FILE_NAME);
        bye.delete();
    }
    
    /*
     * Remove Windows line endings if they are in the input for consistency with Linux
    */
    private static String removeWindowsNewlines(String theString) {
        theString = theString.replaceAll("[\\r]", "");
        return theString;
    }        
    
}

