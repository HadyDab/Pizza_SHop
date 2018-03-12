package de.fh_zwickau.oose.zuul.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;

import java.util.Map.Entry;

import de.fh_zwickau.oose.zuul.model.NPC.NPC;
import de.fh_zwickau.oose.zuul.model.items.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * This class represents players in the game. A player has a current location.
 * and can interact with other npc 
 * 
 * @author HadyDab, XardsLP
 * 
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 22L;

	private Room currentRoom;
	private Map<String, Item> inventory;
	private String lastLookingDirection;
	private String playersName;

	/**
	 * Constructor for class player
	 */
	public Player() {
		currentRoom = null;
		this.lastLookingDirection = "";
		this.inventory = new HashMap<>();
		this.playersName = "";
	}

	/**
	 * Return the current room the player is in
	 * 
	 * @return currentRoom the room object to be return
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Set the current Location for the player.
	 * 
	 * @param room
	 *            the room to be set
	 */
	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}

	/**
	 * Get the player's name
	 * 
	 * @return the playersName to be return
	 */
	public String getPlayersName() {
		return playersName;
	}

	/**
	 * Set a name for the player
	 * 
	 * @param playersName
	 *            the playersName to set
	 */
	public void setPlayersName(String playersName) {
		this.playersName = playersName;
	}

	/**
	 * This return the last looking direction the player is facing,
	 * which can be either "north","south","west" or "east"
	 * @return the lastLookingDirection
	 */
	public String getLastLookingDirection() {
		return lastLookingDirection;
	}

	/**
	 * Set the last looking direction the player is facing
	 * @param lastLookingDirection
	 *            the lastLookingDirection to set
	 */
	public void setLastLookingDirection(String lastLookingDirection) {
		this.lastLookingDirection = lastLookingDirection;
	}

	/**
	 * Store items pick into player's inventory
	 * 
	 * @param name
	 *            the name of the item to store
	 * @param item
	 *            the item object to store
	 */
	public void addItemtoInventory(String name, Item item) {
		inventory.put(name, item);
	}

	/**
	 * A simple getter Method to return all Items stored in player's Inventory
	 * 
	 * @return inventory - the player's Inventory
	 */
	public Map<String, Item> getInventory() {
		return inventory;
	}

	/**
	 * Take a specified item from this room if this item exist, else inform the
	 * player there is no such item in this Room. Example 'take key' will check if
	 * an item name Key can be found in the current Room, if that is the case, this
	 * item will be picked and stored in player's Inventory, else player will be
	 * inform there is no such item in the current room.
	 * 
	 * @param itemName
	 *            - the name of the item to be taken.
	 */
	public void takeItem(String itemName) {

		Item item = currentRoom.giveThisItem(itemName);
		addItemtoInventory(itemName, item);
		System.out.println("The item " + itemName + " has been added to your Inventory");
		currentRoom.deleteThisItem(itemName);
	}

	/**
	 * Drop an item from player's Inventory in the current location where the player
	 * is. If the item is not in player's inventory , inform the player. Example
	 * "drop key" will check if player has an item name Key, if that is the case
	 * this item will be drop in the current Location, else player will be inform of
	 * not having such an item.
	 * 
	 * @param itemName
	 *            the name of the item
	 */
	public void dropItem(String itemName) {
		if (inventory.containsKey(itemName)) {
			Item item = inventory.get(itemName);
			currentRoom.addItemstoRoom(itemName, item);
			inventory.remove(itemName);
		} else {
			// nothing yet
		}

	}

	/**
	 * Talk to a npc,if there is one in the current room
	 * 
	 * @param input
	 *            - players input options
	 * @param output
	 *            - npc output options
	 */
	public void talkGUI(TextField input, TextArea output) {
		Room room = this.currentRoom;
		if (room.isThereNPC()) {
			NPC npc = room.getNPC();
			npc.handleOption(input, output);
		} else {
			output.appendText("\n There is no npc to talk to \n");
		}
	}

	/**
	 * Show the image of the direction player is facing
	 * 
	 * @param direction
	 *            - the direction in which the player is facing
	 * @return return file location of the image
	 */
	public String showlookingDirection(String direction) {
		return currentRoom.getImageFromDirection(direction);
	}

	/**
	 * Show the exit of the current room
	 * 
	 * @return exits - all the exit connecting to the room
	 */
	public String showExitOfCurreentRoom() {
		return currentRoom.getLongDescription();
	}

	/**
	 * Try to walk in a given direction.If there is a door this will change the
	 * player's location.
	 * 
	 * @param direction-
	 *            direction to work towards
	 * @param output-
	 *            inform player of the current location
	 * @param roomview-
	 *            view the room image, the player is currently in
	 * @param posview
	 *            - show the position of the player
	 */
	public void walkGUI(String direction, TextArea output, ImageView roomview, ImageView posview) {
		// Try to leave current room.
		this.setLastLookingDirection(direction);
		Optional<Room> whereToGo = currentRoom.getExit(direction);
		Room nextRoom;
		if (!whereToGo.isPresent()) {
			output.appendText("\n There is no exit to this direction \n");
		} else {
			nextRoom = whereToGo.get();
			if (nextRoom.isDoorLock()) {
				output.appendText("This Door is locked find a Key to open it \n");
			} else {
				setCurrentRoom(nextRoom);
				output.appendText("\n" + showExitOfCurreentRoom() + "\n");

				String path = nextRoom.getImageFromDirection(direction);
				String positionpath = nextRoom.getPlayerPosition(direction);

				URL imgURL = getClass().getClassLoader().getResource(path);
				URL imgURLPosition = getClass().getClassLoader().getResource(positionpath);

				BufferedImage bufferedImage;
				BufferedImage bufferedImagePos;
				try {
					bufferedImage = ImageIO.read(imgURL);
					bufferedImagePos = ImageIO.read(imgURLPosition);
					Image imageRoom = SwingFXUtils.toFXImage(bufferedImage, null);
					Image imagePos = SwingFXUtils.toFXImage(bufferedImagePos, null);
					roomview.setImage(imageRoom);
					posview.setImage(imagePos);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * Use a specified item from player's Inventory if this item exist, if that is
	 * not the case inform the Player that he/she does not have such item in their
	 * Inventory. the item name is given by Player and the purpose of usage. Example
	 * 'use key north' will use an Item name Key to open a Door in the given
	 * direction
	 * 
	 * @param itemName-
	 *            name of the item to be use
	 * @param usage-
	 *            description of usage if any
	 * @param output-
	 *            inform player of the usage
	 */
	public void useItemGUI(String itemName, String usage, TextArea output) {
		if (inventory.containsKey(itemName)) {
			Item item = inventory.get(itemName);
			if (item.useItem(this, usage)) {
				output.appendText("\n The item " + itemName + " has been used succesfully\n");
			} else {
				output.appendText("\n The item " + itemName + " can not be used here \n");
			}
		} else {
			// nothing yet
		}
	}

	/**
	 * List all items in player inventory on the table.
	 * 
	 * @param playerInventory,
	 *            list of items in inventory
	 * @param itemNameinPlayerInventory,name
	 *            of items to be displayed
	 */
	public void showInvenoryGUI(TableView<Map.Entry<String, Item>> playerInventory,
			TableColumn<Map.Entry<String, Item>, String> itemNameinPlayerInventory) {

		itemNameinPlayerInventory.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Item>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<String, Item>, String> p) {
						return new SimpleStringProperty(p.getValue().getKey());
					}
				});
		ObservableList<Entry<String, Item>> inventory = FXCollections
				.observableArrayList(this.getInventory().entrySet());
		playerInventory.setItems(inventory);
	}

	/**
	 * Look for Items in the room the player is , and display this item in the table
	 * 
	 * @param itemName,
	 *            name of items to be displayed
	 * @param itemsInRoom,
	 *            list of items in the room
	 * @param output,
	 *            inform player is there is no item in the room
	 */
	public void lookForItemsinThisRoomGui(TableColumn<Map.Entry<String, Item>, String> itemName,
			TableView<Map.Entry<String, Item>> itemsInRoom, TextArea output) {

		itemName.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Item>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entry<String, Item>, String> p) {
						return new SimpleStringProperty(p.getValue().getKey());
					}
				});

		Room room = this.getCurrentRoom();

		ObservableList<Entry<String, Item>> items = FXCollections.observableArrayList(room.getAllItems().entrySet());
		if (!items.isEmpty()) {
			itemsInRoom.setItems(items);
		} else {
			items = null;
			itemsInRoom.setItems(items);
		}
	}

}