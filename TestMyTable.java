/**
 * 
 */


import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.util.Random;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;


/**
 * @author Vlad
 *
 */
/**
 * @author Vlad
 *
 */
public class TestMyTable {
	
	private static String[][] myTable = {{"1", "2", "3"}, {"4", "5", "6"}};
	private static String[][] myNewTable = null;
	
	/**
	 * @param obj
	 * @return
	 */
	public static String serialize(Object obj) {
		return writeToFile(obj);
	}
	
	/**
	 * @param obj
	 * @return
	 */
	public static String writeToFile(Object obj) {
		if (obj == null)
			return null;
		String fileName = "./my-table.txt"; 
		try {
			FileOutputStream fos = new FileOutputStream(new File(fileName));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(obj);
			oos.close();
			fos.close();
		}
		catch (IOException iox) {
			iox.getStackTrace();
		}
		return fileName;
	}


	/**
	 * @param fileName
	 * @return
	 */
	public static String[][] deserialize(String fileName) {
		return readFromFile(fileName);
	}
	
	/**
	 * @param fileName
	 * @return
	 */
	public static String[][] readFromFile(String fileName) {
		if (fileName == null)
			return null;
		String[][] s2 = null;
		try {
			FileInputStream fis = new FileInputStream(new File(fileName));
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
	
	
	/**
	 * @param table
	 * @return
	 */
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
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
		String myFile = TestMyTable.serialize(myTable);
		myNewTable = TestMyTable.deserialize(myFile);
    }

	
	@Test
	public void testNumberOfRows() {
			assertTrue(myTable.length == myNewTable.length);
	}
	
	@Test
	public void testNumberOfCols() {
			assertTrue(myTable[0].length == myNewTable[0].length);
	}
	
	@Test
	public void testRandomCell() {
			Random random = new Random();
			int row = random.nextInt(2);
			int col = random.nextInt(2);
			System.out.println("Random row: "+row+"--- Random col: "+col);
			assertTrue(myTable[row][col].equals(myNewTable[row][col]));
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("MyTable Before Serialization");
		System.out.println(table2printout(myTable));

		String myFile = TestMyTable.serialize(myTable);
		myNewTable = TestMyTable.deserialize(myFile);

		System.out.println("\nMyTable After Deserialization");
		System.out.println(table2printout(myNewTable));
	}

}
