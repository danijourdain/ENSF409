package edu.ucalgary.ensf409;

import java.io.*;
import java.util.*;

public class StitchPattern {
    private File file;
    private String fileContents;
    private ArrayList<ArrayList<Character>> pattern;
    
    public StitchPattern(File inputFile) {
        this.file = inputFile;
        //constructor, sets default values
    }

    public boolean readFile() {
        if(this.file.isFile() == false) {
            return false;
            //return false if the file doesn't exist
        }

        try {
            //try to read the file
            BufferedReader read = new BufferedReader(new FileReader(this.file));
            String line;
            StringBuilder input = new StringBuilder();

            while((line = read.readLine()) != null) {
                input.append(line + "\n");
            }

            this.fileContents = input.toString();
            parseData();
            read.close();
        }
        catch (IOException e) {
            return false;
            //return false if there was an exception thrown.
        }

        return true;
        //return true if it was successful
    }

    public ArrayList<ArrayList<Character>> getPattern() {
        return this.pattern;
        //get pattern
    }

    public void setPattern(String contents) {
        this.fileContents = contents;
        parseData(); 
        //set contents and parse the new data
    }

    public void mirrorHorizontal() {
        for(int i = 0; i < this.pattern.size(); i++) {
            ArrayList<Character> mirrored = new ArrayList<>();

            int size = this.pattern.get(i).size();
            for(int j = size - 1; j >= 0; j--) {
                mirrored.add(this.pattern.get(i).get(j));
                //add the reversed elements
            }
            for(int j = 0; j < size; j++) {
                mirrored.add(this.pattern.get(i).get(j));
                //add the in-order elements
            }

            this.pattern.set(i, mirrored);
        }
    }

    public void mirrorVertical() {
        ArrayList<ArrayList<Character>> temp = new ArrayList<>();

        for(int i = this.pattern.size() - 1; i >= 0; i--) {
            temp.add(this.pattern.get(i));
            //add the reversed elements
        }
        for(int i = 0; i < this.pattern.size(); i++) {
            temp.add(this.pattern.get(i));
            //add the in-order elements
        }

        this.pattern = temp;
    }

    public void mirrorBoth() {
        mirrorHorizontal();
        mirrorVertical();
        //mirror the contents first horizontally then vertically
    }

    public void parseData() {
        this.pattern = new ArrayList<>();
        String[] splitByLine = this.fileContents.split("\n");
        //split the plain file contents at each newline character

        for(int i = 0; i < splitByLine.length; i++) {
            ArrayList<Character> temp = new ArrayList<>();
            for(int j = 0; j < splitByLine[i].length(); j++) {
                temp.add(splitByLine[i].charAt(j));
                //add each character to a temp ArrayList of characters
            }
            this.pattern.add(temp);
            //add each line to the overall pattern
        }
    }
}