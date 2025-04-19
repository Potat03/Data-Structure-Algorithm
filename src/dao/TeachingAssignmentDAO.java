/*
 * @author Loo Wee Kiat
 */
package dao;

import adt.SortedListInterface;
import java.io.*;

public class TeachingAssignmentDAO {

    public void saveToFile(SortedListInterface list, int size, String filePath) {
        File file = new File(filePath);
        if (size != 0) {
            try {
                file.createNewFile();
                ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
                ooStream.writeObject(list);
                ooStream.close();
            } catch (FileNotFoundException ex) {
                System.out.println("\nFile Error");
            } catch (IOException ex) {
                System.out.println("\nCannot save to file");
                System.out.println(ex);
            }
        }else{
            file.delete();
        }
    }

    public SortedListInterface retrieveFromFile(String filePath) {
        File file = new File(filePath);
        SortedListInterface getDaoList = null;
        try {
            file.createNewFile();
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            getDaoList = (SortedListInterface) (oiStream.readObject());
            oiStream.close();
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return getDaoList;
    }

}
