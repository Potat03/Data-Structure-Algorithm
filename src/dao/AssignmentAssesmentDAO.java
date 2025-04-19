/*
 * @author Loh Thiam Wei 
 * @ModuleHandle AssignmentAssessment  * 
 * This class belongs to Assignment Assessment Subsystem  * 
 */
package dao;

import java.io.*;
// To get file path from json file
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AssignmentAssesmentDAO {

    private String getFilePath(String className) {
        String filePath = className + "FilePath";
        JSONParser parser = new JSONParser();
        try {
            // Parse the JSON file
            Object obj = parser.parse(new FileReader("config.json"));
            JSONObject config = (JSONObject) obj;
            // Get the file path from the configuration
            return (String) config.get(filePath);
        } catch (IOException | ParseException e) {
            return null; // Handle the error appropriately
        }
    }

    public boolean saveToFile(Object list, String className) {
        File file = new File(getFilePath(className));
        try {
            file.createNewFile();
            try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
                ooStream.writeObject(list);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile Error");
            return false;
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public Object retrieveFromFile(String className) {
        File file = new File(getFilePath(className));
        Object retrievedObject = null;
        try {
            file.createNewFile();
            try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
                retrievedObject = (Object) (oiStream.readObject());
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("\nCannot read from file.");
            System.out.println(ex.getMessage());
        }
        System.out.println("\nClass not found.");
        return retrievedObject;
    }

}
