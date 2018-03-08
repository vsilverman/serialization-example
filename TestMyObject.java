/**
 * 
 */
package Serialization;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * @author Vlad
 *
 */
public class TestMyObject {
	
	public static void serialize(Object obj) {
		writeToFile(obj);
	}
	
	public static void writeToFile(Object obj) {
		try {
			FileOutputStream fos = new FileOutputStream(new File("./my-obj.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(obj);
			oos.close();
			fos.close();
		}
		catch (IOException iox) {
			iox.getStackTrace();
		}
	}


	public static MyObject deserialize() {
		return readFromFile();
	}
	
	public static MyObject readFromFile() {
		MyObject mo2 = null;
		try {
			FileInputStream fis = new FileInputStream(new File("./my-obj.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			mo2 = (MyObject)ois.readObject();
			ois.close();
			fis.close();
			
		}
		catch (IOException iox) {
			iox.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mo2;
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyObject mo = new MyObject(321, "My Name");
		System.out.println("MyObject Before Serialization");
		System.out.println("id: " + mo.getId());
		System.out.println("name: " + mo.getName());

		TestMyObject.serialize(mo);
		MyObject newmo = TestMyObject.deserialize();

		System.out.println("\nMyObject After Deserialization");
		System.out.println("id: " + newmo.getId());
		System.out.println("name: " + newmo.getName());

	}

}
