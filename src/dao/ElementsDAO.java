/**
 * @author Nicholas Yap Jia Wey
 */
package dao;

import adt.ListInterface;
import adt.SortedListInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ElementsDAO<T extends Comparable<T>> {

    public void saveToFile(SortedListInterface<T> list, String fileName) {
        File file = new File(fileName);
        if (list.size() != 0) {
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

    public SortedListInterface<T> retrieveAllFromFile(String fileName) {
        File file = new File(fileName);
        SortedListInterface<T> getDaodeList = null;
        try {
            file.createNewFile();
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            getDaodeList = (SortedListInterface<T>) (oiStream.readObject());
            oiStream.close();
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return getDaodeList;
    }
}
