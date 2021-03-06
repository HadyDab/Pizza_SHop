package de.fh_zwickau.oose.zuul.model.items;

import java.util.HashMap;
import java.util.Map;
import de.fh_zwickau.oose.zuul.model.Player;
import de.fh_zwickau.oose.zuul.model.Room;

/**
 * A key that extends the abstract class item and implements the methods of this
 * class.
 * 
 * @author HadyDab, XardsLP
 * 
 */
public class Key extends Item {


	/**
	 * 
	 */
	private static final long serialVersionUID = 24L;

	public Key(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This Item opens a lock Door If the door can be unlocked than give information
	 * to the player. If the door can't be unlocked than give to information to the
	 * player.
	 */
	@Override
	public boolean useItem(Player player, String string) {

		Map<String, Room> exits = new HashMap<>();
		Room currentRoom = player.getCurrentRoom();

		exits = currentRoom.getAllExits();

		for (Room room : exits.values()) {
			if (room.isDoorLock() || room.getKeyName().equals(this.getName())) {
				room.setLock(false);
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

}
