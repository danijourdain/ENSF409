/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.*;

public class Quiz2ExampleTest {
    int expectedTaxRoll = 40381;
    String expectedStreet = "48th St NW";
    int expectedNumber = 1228;
    String expectedAnnex = "Apt 12";
    String givenPostCode = "T3B1R4";
    String expectedPostCode = "T3B 1R4";
    String expectedDescription = "duplex";

    public ResidentialProperty testObject1 = new ResidentialProperty(expectedDescription, expectedTaxRoll, expectedStreet, expectedNumber, givenPostCode, expectedAnnex);
    public ResidentialProperty testObject2 = new ResidentialProperty(expectedDescription, expectedTaxRoll, expectedStreet, expectedNumber, givenPostCode);

    @Test
    public void testApproximateFormattingWithAnnex() {
        String expectedOutput = "descriptionduplexaddress122848thstnwapt12postcodet3b1r4";
        String actualOutput = testObject1.formatOutput();
        actualOutput = actualOutput.toLowerCase();
        actualOutput = actualOutput.replaceAll("[^\\w]", "");
        assertEquals("formatOutput in ResidentialProperty with annex, alphanumeric characters only, is incorrect", expectedOutput, actualOutput);
    }

    @Test
    public void testApproximateFormattingWithoutAnnex() {
        String expectedOutput = "descriptionduplexaddress122848thstnwpostcodet3b1r4";
        String actualOutput = testObject2.formatOutput();
        actualOutput = actualOutput.toLowerCase();
        actualOutput = actualOutput.replaceAll("[^\\w]", "");
        assertEquals("formatOutput in ResidentialProperty without annex, alphanumeric characters only, is incorrect", expectedOutput, actualOutput);
    }

    @Test
    public void testExactFormattingWithAnnex() {
        String expectedOutput = "Description:\tduplex\nAddress:\t1228 48th St NW Apt 12\nPostcode:\tT3B 1R4";
        String actualOutput = testObject1.formatOutput();
        assertEquals("formatOutput in ResidentialProperty with annex is incorrect", expectedOutput, actualOutput);
    }

    @Test
    public void testExactFormattingWithoutAnnex() {
        String expectedOutput = "Description:\tduplex\nAddress:\t1228 48th St NW\nPostcode:\tT3B 1R4";
        String actualOutput = testObject2.formatOutput();
        assertEquals("formatOutput in ResidentialProperty with annex is incorrect", expectedOutput, actualOutput);
    }

    @Test
    public void testGetDescription() {
        String actualDescription = testObject1.getDescription();
        assertEquals("Incorrect information stored/returned for description", expectedDescription, actualDescription); 
    }

    @Test
    public void testSetDescription() {
        var testObject3 = new ResidentialProperty(expectedDescription, expectedTaxRoll, expectedStreet, expectedNumber, givenPostCode);
        String newExpectedDescription = "apartment";
        testObject3.setDescription(newExpectedDescription);
        String actualDescription = testObject3.getDescription();
        assertEquals("Error with setDescription", newExpectedDescription, actualDescription);
    }
 
    @Test
    public void testSixArgumentInheritedConstructorAndInheritedGetters() {
        int actualTaxRoll = testObject1.getTaxRollNumber(); 
        int actualNumber = testObject1.getBuildingNumber();
        String actualAnnex = testObject1.getBuildingAnnex();
        String actualStreetName = testObject1.getStreetName();
        String actualPostCode = testObject1.getPostCode();

        assertEquals("Incorrect information stored/returned for tax roll", expectedTaxRoll, actualTaxRoll);
        assertEquals("Incorrect information stored/returned for building number", expectedNumber, actualNumber);
        assertEquals("Incorrect information stored/returned for annex", expectedAnnex, actualAnnex);
        assertEquals("Incorrect information stored/returned for street name", expectedStreet, actualStreetName);
        assertEquals("Incorrect information stored/returned for post code", expectedPostCode, actualPostCode);
    }

    @Test
    public void testFiveArgumentInheritedConstructorAndInheritedGetters() {
        int actualTaxRoll = testObject2.getTaxRollNumber(); 
        int actualNumber = testObject2.getBuildingNumber();
        String actualStreetName = testObject2.getStreetName();
        String actualPostCode = testObject1.getPostCode();

        assertEquals("Incorrect information stored/returned for tax roll", expectedTaxRoll, actualTaxRoll);
        assertEquals("Incorrect information stored/returned for building number", expectedNumber, actualNumber);
        assertEquals("Incorrect information stored/returned for street name", expectedStreet, actualStreetName);
        assertEquals("Incorrect information stored/returned for post code", expectedPostCode, actualPostCode);
    }

    @Test
    public void testInheritedSetters() {
        var testObject3 = new ResidentialProperty(expectedDescription, expectedTaxRoll, expectedStreet, expectedNumber, givenPostCode);
        var newExpectedStreet = "22nd Ave NW";
        var newExpectedNumber = 10;
        var newExpectedAnnex = "B";
        var newExpectedPostCode = "R2E 6U9";
        testObject3.setStreetName(newExpectedStreet);
        testObject3.setBuildingNumber(newExpectedNumber);
        testObject3.setBuildingAnnex(newExpectedAnnex);
        testObject3.setPostCode("R2E6U9");
        int actualNumber = testObject3.getBuildingNumber();
        String actualAnnex = testObject3.getBuildingAnnex();
        String actualStreetName = testObject3.getStreetName();
        String actualPostCode = testObject3.getPostCode();

        assertEquals("Error in setter for building number", newExpectedNumber, actualNumber);
        assertEquals("Error in setter for annex", newExpectedAnnex, actualAnnex);
        assertEquals("Error in setter for street name", newExpectedStreet, actualStreetName);
        assertEquals("Error in setter for post code", newExpectedPostCode, actualPostCode);
    }

    @Test
    public void testPostCodeException() {
        boolean testResult = false;
        String invalidPostCode = "ABC DEF1";
        try {
            var testObject3 = new ResidentialProperty(expectedDescription, expectedTaxRoll, expectedStreet, expectedNumber, invalidPostCode, expectedAnnex);     
        }
        catch (IllegalArgumentException e) {
            testResult = true;
        }
        catch (Exception e) { }
        assertTrue("Invalid post code did not throw an IllegalArgumentException", testResult);
    }

    @Test
    public void testResidentialPropertyInheritsFromCalgaryProperty() {
        assertTrue("ResidentialProperty does not inherit from CalgaryProperty", 
            (testObject1 instanceof CalgaryProperty));
    }

    @Test
    public void testResidentialPropertyImplementsStandardFormatting() {
        assertTrue("ResidentialProperty does not implement StandardFormatting",
          (StandardFormatting.class.isAssignableFrom(testObject1.getClass())));
    }

    @Test
    public void testFormatOutputShortKey() {
        String key = "short1";
        String value = "a value";
        String expected = key + ":\t\t" + value;
        String actual = testObject1.formatOutput(key, value);
        assertEquals("StandardFormatting formatOutput is incorrect with key of length 6", expected, actual);
    }

    @Test
    public void testFormatOutputMediumKey() {
        String key = "12345678123456";
        String value = "a value";
        String expected = key + ":\t" + value;
        String actual = testObject1.formatOutput(key, value);
        assertEquals("StandardFormatting formatOutput is incorrect with key of length 14", expected, actual);
    }

    @Test
    public void testFormatOutputLongKey() {
        String key = "this is a very long key well over 14 characters";
        String value = "a value";
        String expected = key + ": " + value;
        String actual = testObject1.formatOutput(key, value);
        assertEquals("StandardFormatting formatOutput is incorrect with long key", expected, actual);
    }
}
