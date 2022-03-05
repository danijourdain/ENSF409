/*
Copyright Ann Barcomb and Emily Marasco, 2022
All rights reserved. This code may not be published or shared.
Sharing or posting this code is an academic integrity violation.
*/

package edu.ucalgary.ensf409;

// Format with key and whitespace, then value
class StandardFormatting {
    static String formatOutput(String key, String value) {
        String text;
        if (key.length() <= 14 && key.length() > 6) {
            text = key + ":\t" + value;
        }

        else if (key.length() <= 6) {
            text = key + ":\t\t" + value;
        }

        else {
            text = key + ": " + value;
        }

        return text;
    }
}
