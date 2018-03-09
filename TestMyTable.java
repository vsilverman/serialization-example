/**
 * 
 */


import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.util.Base64;
import java.util.Base64.Encoder;


/**
 * @author Vlad
 *
 */
public class TestMyTable {
	
	public static void serialize(Object obj) {
		writeToFile(obj);
	}
	
	public static void writeToFile(Object obj) {
		try {
			FileOutputStream fos = new FileOutputStream(new File("./my-table.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(obj);
			oos.close();
			fos.close();
		}
		catch (IOException iox) {
			iox.getStackTrace();
		}
	}


	public static String[][] deserialize() {
		return readFromFile();
	}
	
	public static String[][] readFromFile() {
		String[][] s2 = null;
		try {
			FileInputStream fis = new FileInputStream(new File("./my-table.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			s2 = (String[][])ois.readObject();
			ois.close();
			fis.close();
		}
		catch (IOException iox) {
			iox.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s2;
	}
	
	
	public static String table2printout(String[][] table) {
		String printout = "";
		for (int row= 0; row < table.length; row++) {
			for (int col=0; col<table[0].length; col++) {
				printout += table[row][col] + " ";
			}
//			for (String cell : table[row]) { 	// this code
//				printout += cell + " ";			// also
//			}									// works
			printout += "\n";
		}
		return printout;
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] myTable = {{"1", "2", "3"}, {"4", "5", "6"}};
		
		System.out.println("MyTable Before Serialization");
		System.out.println(table2printout(myTable));

		TestMyTable.serialize(myTable);
		String[][] myNewTable = TestMyTable.deserialize();

		System.out.println("\nMyTable After Deserialization");
		System.out.println(table2printout(myNewTable));

	}

}
