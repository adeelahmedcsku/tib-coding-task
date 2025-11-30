package eu.tib.controller;


import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

// Note: Feel free to code here, and add custom methods if required.

public class Operations {

    private Operations(){}

    /*
    If for some reason the application is not able to read the JSON file from the resource directory,
    place the JSON file on a custom path and remove/modify the first two lines of the following method as needed.
    */

    public static JSONArray readJsonFile(String filePath) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());
        JSONParser parser = new JSONParser();
        try {
            return  (JSONArray) parser.parse(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
