/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.*;
import java.util.Arrays;

public class Quiz2DefaultTest {
    String expectedCommonName = "Schlagobers";
    String expectedOfficialName = "SI-N3";
    String expectedPlanetType = "NEPTUNIAN";
    String expectedSun = "Sirius";
    float expectedDiameter = 49.25f;
    double[] expectedCoordinates = new double[] { 234.68, 29018.82, 500030.90 };

    public InhabitedPlanet testObject1 = new InhabitedPlanet(expectedCommonName, expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, "Neptunian");

    public InhabitedPlanet testObject2 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, "nEpTuniaN");

    @Test
    public void testLongestConstructorAndInheritedGetters() {
        String actualOfficialName = testObject1.getOfficialName();
        String actualCommonName = testObject1.getCommonName();
        String actualPlanetType = testObject1.getPlanetType();
        String actualSun = testObject1.getSun();
        float actualDiameter = testObject1.getDiameter();
        double[] actualCoordinates = testObject1.getCoordinates();

        assertEquals("Incorrect information stored/returned for official name", expectedOfficialName, actualOfficialName);
        assertEquals("Incorrect information stored/returned for common name", expectedCommonName, actualCommonName);
        assertEquals("Incorrect information stored/returned for planet type", expectedPlanetType, actualPlanetType);
        assertEquals("Incorrect information stored/returned for sun", expectedSun, actualSun);
        assertEquals("Incorrect information stored/returned for diameter", expectedDiameter, actualDiameter, 0.0f);
        assertEquals("Incorrect information stored/returned for X coordinate", expectedCoordinates[0], actualCoordinates[0], 0.001);
        assertEquals("Incorrect information stored/returned for Y coordinate", expectedCoordinates[1], actualCoordinates[1], 0.001);
        assertEquals("Incorrect information stored/returned for Z coordinate", expectedCoordinates[2], actualCoordinates[2], 0.001);
    }

    @Test
    public void testNoCommonNameConstructorAndInheritedGetters() {
        String actualOfficialName = testObject2.getOfficialName();
        String actualPlanetType = testObject2.getPlanetType();
        String actualSun = testObject2.getSun();
        float actualDiameter = testObject2.getDiameter();
        double[] actualCoordinates = testObject2.getCoordinates();
        
        assertEquals("Incorrect information stored/returned for official name", expectedOfficialName, actualOfficialName);
        assertEquals("Incorrect information stored/returned for planet type", expectedPlanetType, actualPlanetType);
        assertEquals("Incorrect information stored/returned for sun", expectedSun, actualSun);
        assertEquals("Incorrect information stored/returned for diameter", expectedDiameter, actualDiameter, 0.0f);
        assertEquals("Incorrect information stored/returned for X coordinate", expectedCoordinates[0], actualCoordinates[0], 0.001);     
        assertEquals("Incorrect information stored/returned for Y coordinate", expectedCoordinates[1], actualCoordinates[1], 0.001);     
        assertEquals("Incorrect information stored/returned for Z coordinate", expectedCoordinates[2], actualCoordinates[2], 0.001); 
    }

    @Test
    public void testGetIndividualCoordinates() {
        double actualX = testObject2.getXCoordinate();
        double actualY = testObject2.getYCoordinate();
        double actualZ = testObject2.getZCoordinate();
        assertEquals("Incorrect value returned by getXCoordinate()", expectedCoordinates[0], actualX, 0.001);
        assertEquals("Incorrect value returned by getYCoordinate()", expectedCoordinates[1], actualY, 0.001);
        assertEquals("Incorrect value returned by getZCoordinate()", expectedCoordinates[2], actualZ, 0.001);
    }

    @Test
    public void testGetNameWithCommonName() {
        String actualName = testObject1.getName();
        String expectedName = "SI-N3 (\"Schlagobers\")";
        assertEquals("Incorrect return value for getName()", expectedName, actualName); 
    }

    @Test
    public void testGetNameNoCommonName() {
        String actualName = testObject2.getName();
        assertEquals("Incorrect return value for getName()", expectedOfficialName, actualName);
    }

    @Test
    public void testSetters() {
        var newExpectedPlanetType = "ROCKY";
        var testObject3 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, newExpectedPlanetType);
        String newExpectedCommonName = "Latte";
        testObject3.setCommonName(newExpectedCommonName);
        String actualCommonName = testObject3.getCommonName();
        assertEquals("Error with setCommonName", newExpectedCommonName, actualCommonName);
    }

    @Test
    public void testInhabitedPlanetInheritsFromPlanet() {
        assertTrue("InhabitedPlanet does not inherit from Planet", 
            (testObject1 instanceof Planet));
    }

    @Test
    public void testInhabitedPlanetImplementsLocationFormatting() {
        assertTrue("InhabitedPlanet does not implement LocationFormatting",
          (LocationFormatting.class.isAssignableFrom(testObject1.getClass())));
    }

    @Test
    public void testSetLessThanFiveCities() {
        var newExpectedPlanetType = "TERRESTRIAL";
        var testObject3 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, newExpectedPlanetType);
        String city = "New New New York";
        testObject3.addOrReplaceMajorCity(city);
        String[] actualMajorCities = testObject3.getMajorCities();
        try {
            assertEquals("Incorrect first array element for major cities with only one city", city, actualMajorCities[0]);
            assertNull("Incorrect second array element for major cities with only one city", actualMajorCities[1]);
            assertNull("Incorrect third array element for major cities with only one city", actualMajorCities[2]);
            assertNull("Incorrect fourth array element for major cities with only one city", actualMajorCities[3]);
            assertNull("Incorrect fifth array element for major cities with only one city", actualMajorCities[4]);
        }
        catch (Exception e) { }
        assertEquals("getMajorCities() returns an array of incorrect length", 5, actualMajorCities.length);
    }

    @Test
    public void testSetMoreThanFiveCities() {
        var newExpectedPlanetType = "ICE GIANT";
        var testObject3 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, newExpectedPlanetType);
        String[] cities = { "New Calgary", "Neu Berlin", "Nouvelle Paris", "Uusi Turku", "Nueva Tenochtitlan", "Essence", "Novo Belo Horizonte"};
        for (int i=0; i < cities.length; i++) {
            testObject3.addOrReplaceMajorCity(cities[i]);
        }
        String[] expectedCities = Arrays.copyOfRange(cities, 2, cities.length);
        String[] actualMajorCities = testObject3.getMajorCities();
        try {
            for (int i=0; i < actualMajorCities.length; i++) {
                String err = String.format("Incorrect value at element %d for major cities, more than five", i);
                assertEquals(err, expectedCities[i], actualMajorCities[i]);
            }
        }
        catch (Exception e) { }
        assertEquals("getMajorCities() returns an array of incorrect length", expectedCities.length, actualMajorCities.length);
    }

    @Test
    public void testGetMajorCitiesAsStringLessThanFive() {
        var newExpectedPlanetType = "GAS GIANT";
        var testObject3 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, newExpectedPlanetType);
        testObject3.addOrReplaceMajorCity("New Calgary");
        testObject3.addOrReplaceMajorCity("Neu Berlin");
        String expectedString = "New Calgary, Neu Berlin";
        String actualString = testObject3.getMajorCitiesAsString();
        assertEquals("getMajorCitiesAsString() does not return the correct format", expectedString, actualString);
    }

    @Test
    public void testGetMajorCitiesAsStringMoreThanFive() {
        var newExpectedPlanetType = "GAS GIANT";
        var testObject3 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, newExpectedPlanetType);
        String[] cities = { "New Calgary", "Neu Berlin", "Nouvelle Paris", "Uusi Turku", "Nueva Tenochtitlan", "Essence", "Novo Belo Horizonte"}; 
        for (int i=0; i < cities.length; i++) {
            testObject3.addOrReplaceMajorCity(cities[i]);
        }
        String expectedString = "Nouvelle Paris, Uusi Turku, Nueva Tenochtitlan, Essence, Novo Belo Horizonte";
        String actualString = testObject3.getMajorCitiesAsString();
        assertEquals("getMajorCitiesAsString() does not return the correct format", expectedString, actualString);
    }

    @Test
    public void testApproximateFormattingGetMajorCitiesAsString() {
        var newExpectedPlanetType = "TERRESTRIAL";
        var testObject3 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, newExpectedPlanetType);
        testObject3.addOrReplaceMajorCity("New Calgary");
        testObject3.addOrReplaceMajorCity("Neu Berlin");
        String actualString = testObject3.getMajorCitiesAsString();
        actualString = actualString.replaceAll("[ ,\\[\\]]", "");
        String expectedString = "NewCalgaryNeuBerlin";
        assertEquals("getMajorCitiesAsString() does not return approximately correct value", expectedString, actualString);
    }

    @Test
    public void testPlanetTypeException() {
        boolean testResult = false;
        String invalidPlanetType = "GAS-GIANT";
        try {
            var testObject3 = new InhabitedPlanet(expectedOfficialName, expectedSun, expectedCoordinates[0], expectedCoordinates[1], expectedCoordinates[2], expectedDiameter, invalidPlanetType);
        }
        catch (IllegalArgumentException e) {
            testResult = true;
        }
        catch (Exception e) { }
        assertTrue("Invalid planet type did not throw an IllegalArgumentException", testResult);
    }



    @Test
    public void testFormatOutput() {
        String key = "an arbitrary key";
        String value = "a random value";
        String expected = "AN ARBITRARY KEY = a random value";
        String actual = testObject1.formatOutput(key, value);
        assertEquals("StandardFormatting formatOutput is incorrect", expected, actual);
    }

}
