package edu.ucalgary.ensf409;

import java.io.*;
import java.util.*;

public class BibReader {
    File file;
    String fileContents;
    HashMap<String, String> attributes;

    public BibReader(File inputFile) {
        this.file = inputFile;
        this.attributes = new HashMap<>();
    }

    public boolean readFile() {
        if(this.file.isFile() == false) {
            return false;
        }

        try {
            BufferedReader read = new BufferedReader(new FileReader(this.file));
            StringBuffer contents = new StringBuffer();
            String line;

            while((line = read.readLine()) != null) {
                contents.append(line + "\n");
            }

            this.fileContents = contents.toString();
            read.close();
        }
        catch (IOException e) {
            return false;
        }

        return true;
    }

    public String getContents() {
        return this.fileContents;
    }

    public void setContents(String contents) {
        this.fileContents = contents;
    }

    public String getAttribute(String attribute) {
        String cleanedUp = attribute.trim().toLowerCase();
        String result = this.attributes.get(cleanedUp);

        if(result == null) {
            result = "";
        }

        return result;
    }

    public HashMap<String, String> getAttributes() {
        return this.attributes;
    }

    public boolean parseContents() {
        String[] splitByLine = this.fileContents.split("\n");
        
        for(String line: splitByLine) {
            String[] separated = line.split("=");
            if(separated.length != 2) {
                continue;
            }

            String key = separated[0].trim().toLowerCase();
            String data = separated[1].replaceAll("\\{", "");
            data = data.replaceAll("\\},", "");
            data = data.replaceAll("}", "");

            
            if(this.attributes.get(key) != null) {
                continue;
            }
            this.attributes.put(key, data.trim());
        }

        if(this.attributes == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
