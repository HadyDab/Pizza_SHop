package de.fh_zwickau.oose.zuul.model;

import java.io.Serializable;

/**
 * Class for the Game Tasks of the player
 * 
 * @author HadyDab, XardsLP
 * 
 */
public class GameTask implements Serializable {

	private static final long serialVersionUID = 26L;

	private String name;

	/**
	 * Constructor of the GameTask
	 * 
	 * @param name
	 *            name of the task
	 */
	public GameTask(String name) {
		this.name = name;
	}

	/**
	 * Getter to return the name of the Task
	 * 
	 * @return name the name to return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter to set a name of a Task
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Check if item is in this room.
	 * 
	 * @param world
	 *            the world in which the player is
	 * @param name
	 *            the room name in which the item should be
	 * @param itemName
	 *            the itemName to be check
	 * @return true if item found else return false
	 */
	public boolean checkItemInRoom(GameWorld world,String name, String itemName) {
		Room room = world.getThisRoombyName(name);
		if (room.checkForItem(itemName)) {
			return true;
		} else {
			return false;
		}
	}
}
