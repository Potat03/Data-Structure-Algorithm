/**
 *
 * @author Tan Wei Siang
 */
package dao;

import adt.*;
import java.io.*;

public class AssignmentTeamDAO {
        public void saveToFile(ListInterface list, int size, String filePath) {
        File file = new File(filePath);
        if (size != -1) {
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

    public ListInterface retrieveFromFile(String filePath) {
        File file = new File(filePath);
        ListInterface getDaodeList = null;
        try {
            file.createNewFile();
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            getDaodeList = (ListInterface) (oiStream.readObject());
            oiStream.close();
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return getDaodeList;
    }
 public void saveToFileSort(SortedListInterface list, int size, String filePath) {
        File file = new File(filePath);
        if (size != -1) {
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

    public SortedListInterface retrieveFromFileSort(String filePath) {
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
