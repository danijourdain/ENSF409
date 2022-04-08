package edu.ucalgary.ensf409;

import java.util.*;
import java.io.*;

public class BibReader2 {
    private File file;
    private String contents;
    private HashMap<String, String> attributes;

    public BibReader2(File file) {
        this.file = file;
        this.attributes = new HashMap<String, String>();
    }

    public boolean readFile() {
        if(this.file.isFile() == false) {
            return false;
        }

        try {
            String fileName = this.file.getName();
            BufferedReader read = new BufferedReader(new FileReader(fileName));
            String line;
            StringBuilder input = new StringBuilder();

            while((line = read.readLine()) != null) {
                input.append(line + "\n");
            }
            this.contents = input.toString();
            read.close();
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String newData) {
        this.contents = newData;
    }

    public boolean parseContents() {
        String[] split = this.contents.split("\n");

        for(int i = 0; i < split.length - 1; i++) {
            String[] elements = split[i].split("=");

            if(elements.length != 2) {
                continue;
            }

            String key = elements[0].trim().toLowerCase();
            String item = elements[1].substring(0, elements[1].length() - 1);
            item = item.replaceAll("\\{", "");
            item = item.replaceAll("\\}", "");

            if(!(this.attributes.containsKey(key))) {
                this.attributes.put(key, item.trim());
            }
        }

        if(this.attributes == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public String getAttribute(String attribute) {
        String result = this.attributes.get(attribute.toLowerCase());

        if(result == null) {
            result = "";
        }

        return result;
    }

    public HashMap<String, String> getAttributes() {
        return this.attributes;
    }


}
