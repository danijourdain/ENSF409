/*
Copyright Ann Barcomb and Emily Marasco, 2022
Licensed under GPL v3
See LICENSE.txt for more information.
*/

package edu.ucalgary.ensf409;

class SingleUseMethodException extends Exception {
    public SingleUseMethodException() {
        super("Method can only be called once per instantiation");
    }
}