package de.fh_zwickau.oose.zuul.model;

import java.util.Set;

import de.fh_zwickau.oose.zuul.model.NPC.NPC;
import de.fh_zwickau.oose.zuul.model.items.Item;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Ghost House" application. "Ghost House" is a very
 * simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. For each existing exit, the room stores a reference
 * to the neighboring room.
 *
 * @author HadyDab, XardsLP
 * 
 */
public class Room implements Serializable {
	private static final long serialVersionUID = 23L;

	private String description;
	private HashMap<String, Room> exits; // stores exits of this room.
	private Map<String, Item> items; // stores items of this room.
	private boolean isLocked = false;
	private String keyname;
	private NPC npc;
	private Map<String, String> images;
	private Map<String, String> positions;
	private String roomName;

	/**
	 * Create a room described 'description'. Initially, it has no exits.
	 * 'description' is something like 'in a kitchen' or 'in an open court yard'.
	 * Initialize a empty HashMap for the items. Later items can be add to the room.
	 * @param description of this room
	 */
	public Room(String description) {
		this.description = description;
		this.exits = new HashMap<String, Room>();
		this.items = new HashMap<>();
		this.images = new HashMap<String, String>();
		this.positions = new HashMap<String, String>();
		this.keyname = "";
		this.roomName = "";
	}

	/**
	 * Second Constructor for creating Rooms with locked Door.
	 * 
	 * @param description
	 *            the description of the room
	 * @param lock
	 *            the boolean status of the room
	 */
	public Room(String description, boolean lock) {
		this.description = description;
		this.isLocked = lock;
		exits = new HashMap<String, Room>();
		items = new HashMap<>();
		this.images = new HashMap<String, String>();
		this.positions = new HashMap<String, String>();
		this.keyname = "";
	}

	/**
	 * Define an exit from this room.
	 * 
	 * @param direction
	 *            the direction of the neighbor rooms
	 * @param neighbor
	 *            the neighbor of the current room
	 */
	public void setExit(String direction, Room neighbor) {
		exits.put(direction, neighbor);
	}

	/**
	 * Set lock to a particular Room.
	 * 
	 * @param isLock
	 *            the boolean isLock
	 */
	public void setLock(boolean isLock) {
		this.isLocked = isLock;
	}

	/**
	 * A simple getter Method to check if the Door to a Room is Locked.
	 * 
	 * @return isLocked
	 */
	public boolean isDoorLock() {
		return isLocked;
	}

	/**
	 * Set the key for this door.
	 * 
	 * @param keyname
	 *            name of the key that lock or unlock the room.
	 */
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	/**
	 * Get the name of the key that was used to lock this door
	 * 
	 * @return the name of the key
	 */
	public String getKeyName() {
		return this.keyname;
	}

	/**
	 * Add or Drop this item to this Room.
	 * 
	 * @param name
	 *            the name of the item
	 * @param item
	 *            the item object to be dropped or added
	 */
	public void addItemstoRoom(String name, Item item) {
		items.put(name, item);
	}

	/**
	 * Return the description of the room (the description that was defined in the
	 * constructor).
	 * 
	 * @return description the description of the room
	 */
	public String getShortDescription() {
		return description;
	}

	/**
	 * Get the name of this room.
	 * 
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * Set a name for this room, this is just the name and not the description.
	 * 
	 * @param roomName
	 *            the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * A simple getter Method to return all list of Item in this Room.
	 * 
	 * @return items list of items
	 */
	public Map<String, Item> getAllItems() {
		return items;
	}

	/**
	 * Return a long description of this room, in the example form: 'You are in the
	 * kitchen.' 'Exits: north west'
	 * 
	 * @return description the description of the room with the information of the
	 *         exits
	 */
	public String getLongDescription() {
		return "You are " + description + ".\n" + getExitString();
	}

	/**
	 * Return a string describing the room's exits, for example 'Exits: north west'.
	 * 
	 * @return returnString the String of exits
	 */
	private String getExitString() {
		String returnString = "Exits:";
		Set<String> keys = exits.keySet();
		for (Iterator<String> iter = keys.iterator(); iter.hasNext();)
			returnString += " " + iter.next();
		return returnString;
	}

	/**
	 * Return the room that is reached if we go from this room in direction
	 * 'direction'. If there is no room in that direction, return null.
	 * @param direction connecting to the room
	 * @return room the player is trying to reach
	 */
	public Optional<Room> getExit(String direction) {
		Optional<Room> optionalRoom = Optional.ofNullable(exits.get(direction));
		return optionalRoom;
	}

	/**
	 * Give this item to the player if the item is in the room
	 * 
	 * @param itemName
	 *            name of the item to be given
	 * @return return this item if its in this room else return null
	 */
	public Item giveThisItem(String itemName) {
		if (items.containsKey(itemName)) {
			return items.get(itemName);
		} else {
			return null;
		}
	}

	/**
	 * Delete this item if the item is in the room
	 * 
	 * @param itemName
	 *            name of the item to be deleted
	 */
	public void deleteThisItem(String itemName) {
		if (items.containsKey(itemName)) {
			items.remove(itemName);
		} else {
		}
	}

	/**
	 * Check if there is a NPC in a room , return true if that is the case otherwise
	 * return false
	 * 
	 * @return true if a Ghost is in this Room
	 */
	public boolean isThereNPC() {
		if (this.getNPC() != null) {
			return true;
		}
		return false;
	}

	/**
	 * @return the ghost
	 */
	public NPC getNPC() {
		return npc;
	}

	/**
	 * Set a Npc in the current room
	 * 
	 * @param npc-
	 *            the npc to set
	 */
	public void setNPC(NPC npc) {
		this.npc = npc;
	}

	/**
	 * Set images to a room with their looking direction
	 * 
	 * @param direction
	 *            direction direction player is going towards
	 * @param fileLocation
	 *            location of the image file
	 */
	public void setRoomImageWithLookingDirection(String direction, String fileLocation) {
		images.put(direction, fileLocation);

	}

	/**
	 * Set the position images to a room with the looking direction of the player
	 * 
	 * @param direction
	 *            direction player is going towards
	 * @param fileLocation
	 *            location of the image file
	 */
	public void setPlayerPosition(String direction, String fileLocation) {
		positions.put(direction, fileLocation);
	}

	/**
	 * Get image of the room base on Looking Direction
	 * 
	 * @param direction-
	 *            the player looking direction
	 * @return image- the image file location
	 */
	public String getImageFromDirection(String direction) {
		String image = images.get(direction);
		return image;
	}

	/**
	 * Get the player position image
	 * 
	 * @param direction-
	 *            direction which player is facing
	 * @return position- the image file location
	 */
	public String getPlayerPosition(String direction) {
		String position = positions.get(direction);
		return position;
	}

	/**
	 * Get all the exits connected to this room
	 * 
	 * @return the exits of all the rooms connected
	 */
	public HashMap<String, Room> getAllExits() {
		return exits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((exits == null) ? 0 : exits.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Room))
			return false;
		Room other = (Room) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (exits == null) {
			if (other.exits != null)
				return false;
		} else if (!exits.equals(other.exits))
			return false;
		return true;
	}

	public boolean checkForItem(String itemName) {
		if (items.containsKey(itemName)) {
			return true;
		} else {
			return false;
		}
	}

}
