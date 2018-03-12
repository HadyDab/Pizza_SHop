package de.fh_zwickau.oose.zuul.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fh_zwickau.oose.zuul.model.NPC.Ghost;
import de.fh_zwickau.oose.zuul.model.NPC.NPC;
import de.fh_zwickau.oose.zuul.model.items.Candle;
import de.fh_zwickau.oose.zuul.model.items.Item;
import de.fh_zwickau.oose.zuul.model.items.Key;
import de.fh_zwickau.oose.zuul.model.items.OldTelephone;

/**
 * Class GameWorld - the World in an adventure game.
 *
 * This class is part of the "Ghost House" application. "Ghost House" is a very
 * simple, text based adventure game.
 *
 * A "GameWorld" represents the entire World of the Game. It stores a reference
 * to the whole Game elements for example the rooms, player and items
 * 
 * @author HadyDab, XardsLP
 * 
 */
public class GameWorld implements Serializable {
	
	private static final long serialVersionUID = 28L;

	private String name; // Name of a Building
	private  List<Room> rooms = new ArrayList<>(); // list of rooms in this Building
	private  Map<String, Room> roomsWithName = new HashMap<>(); // name of the rooms

	private  Room outSide, kitchen, livingRoom, diningRoom, sleepingRoom, bigHall, corridor, WC, conservatory,
			cellarStairs, floorStairs, garden;

	private  NPC ghost;

	/**
	 * Constructor for the Class GameWorld The GameWorld have a List with all Rooms
	 * into it.
	 * 
	 * @param name
	 *            the name of the GameWorld
	 */
	public GameWorld(String name) {
		this.name = name;
	}

	/**
	 * Getter for the name
	 * 
	 * @return name the name of the GameWorld.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the List of Rooms
	 * 
	 * @return the rooms
	 */
	public  List<Room> getRooms() {
		return rooms;
	}

	public  void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	/**
	 * Add Rooms to a Building.
	 * 
	 * @param room
	 *            the room to be added
	 */
	public void addRoom(Room room) {
		this.rooms.add(room);
	}

	/**
	 * Link Rooms with Names and add them to a Map
	 * 
	 * @param name of the room
	 * @param room object to be save
	 */
	public  void addRoomsWithName(String name, Room room) {
		this.roomsWithName.put(name, room);
	}

	/**
	 * Getter that returns a room by given the room name
	 * 
	 * @param name of the room to return
	 * @return the room with this associated name
	 */
	public  Room getThisRoombyName(String name) {
		if (this.roomsWithName.containsKey(name)) {
			return this.roomsWithName.get(name);
		} else {
			return null;
		}
	}



	
	/**
	 *  Set the npc to with his name and options
	 */
	public  void setNPCToGameWorld() {
		// Ghost in the gameWorld
		ghost = new Ghost("Hannaliya Hallmark");
		ghost.addOptions("1", "[1] How can i help release you free");
		ghost.addOptions("2", "[2] Have completed your task");
		ghost.addOptionAnswer("1",
				"The last thing i remember was using the oldkey and i was trying to get to the livingroom.");
		ghost.addOptionAnswer("2", "How is that possible am still here Please help me.");

	}
	
	
	/**
	 * Save all the rooms with there associated names
	 */
	public  void setRoomsWithName() {
		addRoomsWithName(garden.getRoomName(), garden);
		addRoomsWithName(outSide.getRoomName(), outSide);
		addRoomsWithName(kitchen.getRoomName(), kitchen);
		addRoomsWithName(livingRoom.getRoomName(), livingRoom);
		addRoomsWithName(diningRoom.getRoomName(), diningRoom);
		addRoomsWithName(sleepingRoom.getRoomName(), sleepingRoom);
		addRoomsWithName(bigHall.getRoomName(), bigHall);
		addRoomsWithName(corridor.getRoomName(), corridor);
		addRoomsWithName(WC.getRoomName(), WC);
		addRoomsWithName(conservatory.getRoomName(), conservatory);
		addRoomsWithName(cellarStairs.getRoomName(), cellarStairs);
		addRoomsWithName(floorStairs.getRoomName(), floorStairs);

	}
	
	/**
	 *  Create rooms and their associated names
	 */
	public  void createRoomsAndSetTheirExits() {
		addRoom(garden = new Room("in the garden"));
		addRoom(outSide = new Room("at the main entrance of the Building"));
		addRoom(kitchen = new Room("in the kitchen"));
		addRoom(livingRoom = new Room("in the living room "));
		addRoom(diningRoom = new Room("in the dining room"));
		addRoom(sleepingRoom = new Room("in the small hall"));
		addRoom(bigHall = new Room("in the big hall at the top is a chandelier"));
		addRoom(corridor = new Room("in the corridor"));
		addRoom(WC = new Room("now in the WC"));
		addRoom(conservatory = new Room("in the conservatory"));
		addRoom(cellarStairs = new Room("on the stair down to the cellar"));
		addRoom(floorStairs = new Room("on the stair lower floor"));

		// Set Names for this room

		garden.setRoomName("garden");
		outSide.setRoomName("outside");
		kitchen.setRoomName("kitchen");
		livingRoom.setRoomName("livingRoom");
		diningRoom.setRoomName("dinningRoom");
		sleepingRoom.setRoomName("sleepingRoom");
		bigHall.setRoomName("bigHall");
		corridor.setRoomName("corridor");
		WC.setRoomName("WC");
		conservatory.setRoomName("conservatory");
		cellarStairs.setRoomName("cellerStairs");
		floorStairs.setRoomName("floorStairs");
		
		setRoomsWithName();
		
	}
  
	

	
	/**
	 *  Set exits for all the rooms
	 */
	public void setRoomsandExitWithItems() {
		outSide.setExit("north", garden);

		garden.setExit("south", outSide);
		garden.setExit("east", corridor);

		corridor.setExit("west", garden);
		corridor.setExit("north", WC);
		corridor.setExit("south", bigHall);

		WC.setNPC(ghost);
		WC.setExit("south", corridor);
		WC.setExit("east", diningRoom);

		diningRoom.setExit("west", WC);
		diningRoom.setExit("south", kitchen);
		diningRoom.setExit("east", livingRoom);

		livingRoom.setLock(true);
		livingRoom.setKeyname("oldKey");
		livingRoom.setExit("west", diningRoom);
		livingRoom.setExit("south", sleepingRoom);

		sleepingRoom.setExit("north", livingRoom);
		sleepingRoom.setExit("south", conservatory);

		conservatory.setExit("north", sleepingRoom);

		kitchen.setExit("north", diningRoom);
		kitchen.setExit("south", cellarStairs);
		kitchen.setExit("west", bigHall);
		kitchen.addItemstoRoom("telephone", new OldTelephone("telephone"));

		cellarStairs.setExit("north", kitchen);
		cellarStairs.addItemstoRoom("candle", new Candle("candle"));

		bigHall.setExit("east", kitchen);
		bigHall.setExit("north", corridor);
		bigHall.setExit("west", floorStairs);

		floorStairs.setExit("east", bigHall);
		floorStairs.addItemstoRoom("oldKey", new Key("oldKey"));
	}
	
	
	
	/**
	 * Set Images for the rooms
	 */
	public  void setImagesforRoom() {

		ghost.addImage(WC.getShortDescription(), "resources/images/ghost2.png");

		// set the pictures for the room and the position

		outSide.setRoomImageWithLookingDirection("south", "resources/images/outside.jpg");
		outSide.setPlayerPosition("south", "resources/positions/Outside.png");

		garden.setRoomImageWithLookingDirection("north", "resources/images/garden.jpg");
		garden.setPlayerPosition("north", "resources/positions/Garden.png");

		garden.setRoomImageWithLookingDirection("west", "resources/images/garden.jpg");
		garden.setPlayerPosition("west", "resources/positions/Garden.png");

		corridor.setRoomImageWithLookingDirection("east", "resources/images/flur.jpg");
		corridor.setPlayerPosition("east", "resources/positions/Corridor.png");

		corridor.setRoomImageWithLookingDirection("south", "resources/images/flur3.jpg");
		corridor.setPlayerPosition("south", "resources/positions/Corridor.png");

		corridor.setRoomImageWithLookingDirection("north", "resources/images/flur2.jpg");
		corridor.setPlayerPosition("north", "resources/positions/Corridor.png");

		WC.setRoomImageWithLookingDirection("north", "resources/images/WC.jpg");
		WC.setPlayerPosition("north", "resources/positions/Wc.png");

		WC.setRoomImageWithLookingDirection("west", "resources/images/WC2.jpg");
		WC.setPlayerPosition("west", "resources/positions/Wc.png");

		diningRoom.setRoomImageWithLookingDirection("east", "resources/images/Esszimmer3.jpg");
		diningRoom.setPlayerPosition("east", "resources/positions/DiningRoom.png");

		diningRoom.setRoomImageWithLookingDirection("north", "resources/images/Esszimmer2.jpg");
		diningRoom.setPlayerPosition("north", "resources/positions/DiningRoom.png");

		diningRoom.setRoomImageWithLookingDirection("west", "resources/images/Esszimmer.jpg");
		diningRoom.setPlayerPosition("west", "resources/positions/DiningRoom.png");

		livingRoom.setRoomImageWithLookingDirection("east", "resources/images/Wohnzimmer2.jpg");
		livingRoom.setPlayerPosition("east", "resources/positions/LivingRoom.png");

		livingRoom.setRoomImageWithLookingDirection("north", "resources/images/Wohnzimmer.jpg");
		livingRoom.setPlayerPosition("north", "resources/positions/LivingRoom.png");

		sleepingRoom.setRoomImageWithLookingDirection("south", "resources/images/Schlafzimmer.jpg");
		sleepingRoom.setPlayerPosition("south", "resources/positions/BedRoom.png");

		sleepingRoom.setRoomImageWithLookingDirection("north", "resources/images/Schlafzimmer2.jpg");
		sleepingRoom.setPlayerPosition("north", "resources/positions/BedRoom.png");

		conservatory.setRoomImageWithLookingDirection("south", "resources/images/Conservatory.jpg");
		conservatory.setPlayerPosition("south", "resources/positions/Conservatory.png");

		kitchen.setRoomImageWithLookingDirection("south", "resources/images/Kueche3.jpg");
		kitchen.setPlayerPosition("south", "resources/positions/Kitchen.png");

		kitchen.setRoomImageWithLookingDirection("north", "resources/images/Kueche.jpg");
		kitchen.setPlayerPosition("north", "resources/positions/Kitchen.png");

		kitchen.setRoomImageWithLookingDirection("east", "resources/images/Kueche2.jpg");
		kitchen.setPlayerPosition("east", "resources/positions/Kitchen.png");

		cellarStairs.setRoomImageWithLookingDirection("south", "resources/images/TrappeKeller.jpg");
		cellarStairs.setPlayerPosition("south", "resources/positions/CellarStairs.png");

		bigHall.setRoomImageWithLookingDirection("south", "resources/images/GrosseSaal.jpg");
		bigHall.setPlayerPosition("south", "resources/positions/BigHall.png");

		bigHall.setRoomImageWithLookingDirection("west", "resources/images/GrosseSaal2.jpg");
		bigHall.setPlayerPosition("west", "resources/positions/BigHall.png");

		bigHall.setRoomImageWithLookingDirection("east", "resources/images/GrosseSaal3.jpg");
		bigHall.setPlayerPosition("east", "resources/positions/BigHall.png");

		floorStairs.setRoomImageWithLookingDirection("west", "resources/images/TrappeEtage.jpg");
		floorStairs.setPlayerPosition("west", "resources/positions/FloorStairs.png");
	}

	
	
	
	/**
	 *  This method helps check if item in a room has already been picked up my the player.
	 *  This will help in loading a save game.
	 * @param player object that return the inventory
	 */
	public void checkIfPlayerHasThisItems(Player player) {
		for(Room room: rooms) {
			List<Item> items = new ArrayList<>(player.getInventory().values());	
			for(Item item :items) {
				if(player.getInventory().containsValue(item)) {
					room.deleteThisItem(item.getName());
				}
			}
		}	
		
	}
	
	
	/**
	 * Build a GameWorld with rooms,exits,items,npc and images .
	 * 
	 * @param player
	 *            player playing the game
	 */
	public  void buildGameWorld(Player player) {
		setNPCToGameWorld();
		createRoomsAndSetTheirExits();
		setRoomsandExitWithItems();
		setImagesforRoom();
		checkIfPlayerHasThisItems(player);

	}

}
