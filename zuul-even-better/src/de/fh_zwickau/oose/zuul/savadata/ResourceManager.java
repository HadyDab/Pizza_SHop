/**
 * 
 */
package de.fh_zwickau.oose.zuul.savadata;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * ResourcesManger Class saves and load the state of the game
 * 
 * @author HadyDab
 *
 */
public class ResourceManager {

	/**
	 * Save the resource of a serilization data
	 * 
	 * @param data-
	 *            the Object to be save
	 * @param filename-
	 *            the name of the save file
	 * @throws Exception if save was not successful
	 */
	public static void save(Serializable data, String filename) throws Exception {

		try (ObjectOutputStream oose = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
			oose.writeObject(data);
		}
	}

	/**
	 * Load the saved resources of a serializable data 
	 * 
	 * @param filename
	 *            - name of the saved file
	 * @return Object - the object to be load
	 * @throws Exception if file can not be found
	 */
	public static Object load(String filename) throws Exception {

		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {

			return ois.readObject();
		}

	}

}
