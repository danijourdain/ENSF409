/** 
@author     Danielle Jourdain <a href="mailto:firstname.lastname@ucalgary.ca">firstname.lastname@ucalgary.ca</a>
@version    1.3
@since      1.0
*/

import java.util.Arrays;

public class SimpleArrays {
    //This is ahte instance variable that will be used for all future methods
    private String[] stringArray = new String[4];

    /**
     * This main tests several different instances of SimpleArrays and the associated methods
     * @param args Ignored command line arguments
     */
    public static void main(String[] args) {
        //main funciton tests several different ways of initializing and manipulating SimpleArrays objects
        SimpleArrays myArray1 = new SimpleArrays();
        String foundResult1 = myArray1.arrayConcat();
        System.out.println(foundResult1);
 
        SimpleArrays myArray2 = new SimpleArrays();
        String foundResult2 = myArray2.arrayConcat(2);
        System.out.println(foundResult2);
 
        SimpleArrays myArray3 = new SimpleArrays("Hi you");
        String foundResult3 = myArray3.arrayConcat();
        System.out.println(foundResult3);
 
        SimpleArrays myArray4 = new SimpleArrays("Hi you");
        String foundResult4 = myArray4.arrayConcat(2);
        System.out.println(foundResult4);
 
        SimpleArrays myArray5 = new SimpleArrays("Hi you");
        String foundResult5 = myArray5.arrayCrop(0, 2);
        System.out.println(foundResult5);
 
        SimpleArrays myArray6 = new SimpleArrays("Hi you");
        String foundResult6 = myArray6.arrayCrop(3, 2);
        System.out.println(foundResult6);
 
        SimpleArrays myArray7 = new SimpleArrays("Hi you");
        String foundResult7 = myArray7.arrayCrop(0, 6);
        System.out.println(foundResult7);
 
        SimpleArrays myArray8 = new SimpleArrays("Hi you");
        String foundResult8 = myArray8.arrayCrop(3, 3);
        System.out.println(foundResult8);

        SimpleArrays myArray9 = new SimpleArrays("Message");
        String foundResult9 = myArray9.arrayCrop(-2, 3);
        System.out.println(foundResult9);

        SimpleArrays myArray10 = new SimpleArrays("Testing");
        String foundResult10 = myArray10.arrayConcat(3);
        System.out.println(foundResult10);

        SimpleArrays myArray11 = new SimpleArrays("Hello");
        String foundResult11 = myArray11.arrayConcat(0);
        System.out.println(foundResult11);
   }
    
   /**
    * This is one of the constructors. It takes a string and uses it to populate the instance variable stringArray
    * @param arrayMessage Message that will be used to populate all of the array indices
    */
   public SimpleArrays(String arrayMessage) {
       //using Arrays.fill, stringArray is populated with the string stored in arrayMessage
       Arrays.fill(stringArray, arrayMessage);
   }

   /**
    * This is the default constructor. Since no string is provided it will populate
        stringArray with the default message "Hello, world"
    */
   public SimpleArrays() {
       //using Arrays.fill stringArray is populated with a default message "Hello, world"
       Arrays.fill(stringArray, "Hello, world");
   }

   /**
    * Returns a string composed of array elements separated by "#" from arrayIndex to the end of the array.
    * @param arrayIndex Starting index for the string creation
    * @return
    */
   public String arrayConcat(int arrayIndex) {
       if(arrayIndex >= stringArray.length || arrayIndex < 0) {
           //before continuing, check if arrayIndex is valid
           //if not, throw an exception
           throw new IndexOutOfBoundsException("Index provided is out of bounds"); 
       }

       if(arrayIndex == stringArray.length - 1) {
           //since arrayConcat and arrayCrop have different behavior when the indices are the same, handle that special case here
           return stringArray[arrayIndex];
       }
       
       //if arrayIndex is valid, use arrayCrop to get the desired string
       return arrayCrop(arrayIndex, stringArray.length - 1);
   }

   /**
    * Returns a string composed of array elements separated by "#" from beginning to end of the array.
    * @return
    */
   public String arrayConcat() {
       //a default case of arrayConcat that uses 0 as the default value.
       //uses arrayCrop to get the desired string
       return arrayCrop(0, stringArray.length - 1);
   }

   /**
    * Returns a string composed of array elements separated by "#" from startIndex to endIndex
    * @param startIndex Starting index for the string creation
    * @param endIndex Ending index for the string creation
    * @return
    */
   public String arrayCrop(int startIndex, int endIndex) {
       if(startIndex == endIndex) {
           //check if the indices are the same before continuing
           return "Match";
       }
       
       if(startIndex > endIndex) {
           //if startIndex is larger than endIndex, swap the two values
           int tempIndex = startIndex;
           startIndex = endIndex;
           endIndex = tempIndex;
       }
       
       if(startIndex < 0 || endIndex >= stringArray.length) {
           //if either index is out of bounds, return "Fail"
           return "Fail";
       }

       String resultString = "";
       for(int i = startIndex; i <= endIndex; i++) {
           //add the string in stringArray[i] to resultString
           resultString += stringArray[i];
           if(i < endIndex) {
               //if i is not at the last element, add a "#" to separate indices
               resultString += "#";
           }
       }

       return resultString;
   }
}