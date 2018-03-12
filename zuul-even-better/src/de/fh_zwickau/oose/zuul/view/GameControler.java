package de.fh_zwickau.oose.zuul.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;

import de.fh_zwickau.oose.zuul.MainApp;
import de.fh_zwickau.oose.zuul.model.GameWorld;
import de.fh_zwickau.oose.zuul.model.Player;
import de.fh_zwickau.oose.zuul.model.Room;
import de.fh_zwickau.oose.zuul.model.NPC.NPC;
import de.fh_zwickau.oose.zuul.model.items.Item;
import de.fh_zwickau.oose.zuul.savadata.ResourceManager;
import de.fh_zwickau.oose.zuul.savadata.SaveData;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Game Controller, this class to control the GameOverview and react to players
 * input
 * 
 * @author HadyDab, XardsLP
 *
 */
public class GameControler {

	@FXML
	private TextArea textArea;

	@FXML
	private TextField textField;

	@FXML
	private Button east, north, south, west;

	@FXML
	private Button take, drop, use, look, showinventory;

	@FXML
	private Button quit, help, enter;

	@FXML
	private ImageView imageView, npcImageView;

	@FXML
	private ImageView positionView;

	@FXML
	private ImageView inventoryView;

	@FXML
	private ImageView roomitemsView;

	@FXML
	private TableView<Map.Entry<String, Item>> itemsInRoom;

	@FXML
	private TableColumn<Map.Entry<String, Item>, String> itemName;

	@FXML
	private TableView<Map.Entry<String, Item>> playerInventory;

	@FXML
	private TableColumn<Map.Entry<String, Item>, String> itemNameinPlayerInventory;

	@FXML
	private Text position;

	@FXML
	private Text tips;
	
	@FXML
	private Text corridor;
	
	@FXML
	private Text wc;
	
	@FXML
	private Text floor;
	
	@FXML
	private Text cellar;
	
	@FXML
	private Text stairs1; //cellar
	
	@FXML
	private Text stairs2; //floor
	
	@FXML
	private Text garden;
	
	@FXML
	private Text outside;
	
	@FXML
	private Text kitchen;
	
	@FXML
	private Text livingRoom;
	
	@FXML
	private Text bedRoom;
	
	@FXML
	private Text conservatory;
	
	@FXML
	private Text diningRoom;
	
	@FXML
	private Text bigHall;
	
	
	private MainApp mainApp;
	private Player player;
	private GameWorld world;

	public GameControler() {

	}

	/**
	 * Initiate this Controller, this method will be called in the main class
	 * @param world the gameworld the player is in 
	 * @param player the player playing the game
	 * @param roomName the room name the player starts from 
	 * @param lookingDirection the direction the player is facing
	 * @throws IOException if loading fails
	 */
	public void initControler(GameWorld world,Player player, String roomName, String lookingDirection) throws IOException {
		this.player = player;
		this.world = world;
		initiatGame(world,player, roomName, lookingDirection);
		listPlayerInventory();
	}

	/**
	 * When the enter button is press an a Command will be executed base on what the
	 * player key in
	 */
	public void handleEnter() {
		player.talkGUI(textField, textArea);
		lookForItemsinThisRoom();
		listPlayerInventory();
		textField.clear();
	}

	/**
	 * When the button north is press player move towards this direction
	 */
	public void handleNorthButton() {
		goTothisDirection("north");
		lookForItemsinThisRoom();
		listPlayerInventory();
		loadNpcImage();
		controllTips();

	}

	/**
	 * When the button south is press player move towards this direction
	 */
	public void handleSouthButton() {
		goTothisDirection("south");
		lookForItemsinThisRoom();
		listPlayerInventory();
		loadNpcImage();
		controllTips();
	}

	/**
	 * When the button west is press player move towards this direction
	 */
	public void handleWestButton() {
		goTothisDirection("west");
		lookForItemsinThisRoom();
		listPlayerInventory();
		loadNpcImage();
		controllTips();
	}

	/**
	 * When the button east is press player move towards this direction
	 */
	public void handleEastButton() {
		goTothisDirection("east");
		lookForItemsinThisRoom();
		listPlayerInventory();
		loadNpcImage();
		controllTips();
	}

	/**
	 * When the button talk is press player will be able to communicate with a Npc,
	 * if there is one in the room and player has summon the Npc. if that is not the
	 * case player will be inform.
	 */
	@FXML
	private void handleTalkButton() {
		Room room = player.getCurrentRoom();
		if (room.isThereNPC()) {
			NPC npc = room.getNPC();
			if (npc.isVisible()) {
				loadNpcImage();
				// npcImageView.setImage(new Image(npc.getImage(room.getShortDescription())));
				npc.DisplayOptions(textArea);
				npc.checktask(world);
				npcImageView.setImage(new Image(npc.getImage(room.getShortDescription())));
			} else {
				textArea.appendText("\nThere is a Ghost in this room but you have not summon it yet "
						+ " Use a Candle to summon this ghost \n");
			}

		} else {
			textArea.appendText("\nThere is no Npc here\n");
		}
	}

	/**
	 * When the button save is press the current state of the game will be saved
	 */
	@FXML
	private void handleSaveButton() {
		SaveData data = new SaveData();
		data.setPlayer(player);
		data.curentRoomName = player.getCurrentRoom().getRoomName();
		data.lastLookingDirection = player.getLastLookingDirection();
		data.setGameWorld(world);
		try {
			ResourceManager.save(data, this.player.getPlayersName() + ".save");
			textArea.appendText("\nGame has been saved\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * When the button load is press the last save state of the game will be loaded
	 */
	@FXML
	private void handleLoadButton() {
		try {
			SaveData data = (SaveData) ResourceManager.load(this.player.getPlayersName() + ".save");
			String roomName = data.curentRoomName;
			String lookingDirection = data.lastLookingDirection;
			Player player = data.getplayer();
			GameWorld world = data.getGameWorld();
			mainApp.loadGameOverView(world,player, roomName, lookingDirection);
		} catch (Exception e) {
			notifyError("Game could not load", "", "Game could not load");
		}
	}

	/**
	 * Player will pick up an item after selecting this item, if no item
	 * selected,player will be notify
	 */
	@FXML
	private void handleTakeButton() {
		int selectedIndex = itemsInRoom.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Item item = (Item) itemsInRoom.getItems().get(selectedIndex).getValue();
			String itemName = itemsInRoom.getItems().get(selectedIndex).getKey();
			player.addItemtoInventory(itemName, item);
			Room room = player.getCurrentRoom();
			room.deleteThisItem(itemName);
			lookForItemsinThisRoom();
			listPlayerInventory();

		} else {
			notifyError("No Selection", "", "You most Select an Item to able to take it ");
		}
	}

	/**
	 * When an item is selected and the button drop is press, the selected item will
	 * be drop to the current room
	 */
	@FXML
	private void handleDropButton() {
		int selectedIndex = playerInventory.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			String itemName = playerInventory.getItems().get(selectedIndex).getKey();
			player.dropItem(itemName);
			lookForItemsinThisRoom();
			listPlayerInventory();

		} else {
			notifyError("No Selection", "", "You most Select an Item to able to drop it ");
		}

	}

	/**
	 * When an item is selected and the button use is press, the selected item will
	 * be use and drop in the current room
	 */
	@FXML
	private void handleUseButton() {
		int selectedIndex = playerInventory.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			String itemName = playerInventory.getItems().get(selectedIndex).getKey();
			player.useItemGUI(itemName, "", textArea);
			player.dropItem(itemName);
			lookForItemsinThisRoom();
			listPlayerInventory();

		} else {
			notifyError("No Selection", "", "You most Select an Item to able to use it ");
		}
	}

	/**
	 * Game will quit
	 */
	@FXML
	private void handleQuitButton() {
		mainApp.loadStartPage();
	}

	/**
	 * This will load the mission view
	 */
	@FXML
	private void handleMissionsView() {
		mainApp.loadMissionsView();
	}

	/**
	 * This will load the Help View
	 */
	@FXML
	private void handleHelpView() {
		mainApp.loadHelpPage();
	}

	/**
	 * This method moves player to the giving direction
	 * 
	 * @param direction
	 *            in which player is to move
	 */
	private void goTothisDirection(String direction) {
		player.walkGUI(direction, textArea, imageView, positionView);
	}

	/**
	 * When Player press the button look the table will list all the key value of
	 * the current room items Method will Wrap current room items from map to an
	 * ObservableList and extract the key to the Column ItemName
	 */
	private void lookForItemsinThisRoom() {
		player.lookForItemsinThisRoomGui(itemName, itemsInRoom, textArea);
	}

	/**
	 * List all the items player has in his/her Inventory
	 */
	public void listPlayerInventory() {
		player.showInvenoryGUI(playerInventory, itemNameinPlayerInventory);
	}

	/**
	 * This Method initiate all the required class for playing the game
	 * 
	 * @throws IOException
	 */
	private void initiatGame(GameWorld world,Player player, String locationName, String facingDirection) throws IOException {
		
		world.buildGameWorld(player);
		Room room = world.getThisRoombyName(locationName);
		player.setCurrentRoom(room);

		String path = room.getImageFromDirection(facingDirection);
		URL imgURL = getClass().getClassLoader().getResource(path);
		BufferedImage bufferedImage;
		bufferedImage = ImageIO.read(imgURL);
		Image imageRoom = SwingFXUtils.toFXImage(bufferedImage, null);
		imageView.setImage(imageRoom);

		String pathPos = room.getPlayerPosition(facingDirection);
		URL imgURLPos = getClass().getClassLoader().getResource(pathPos);
		BufferedImage bufferedImagePos;
		bufferedImagePos = ImageIO.read(imgURLPos);
		Image imagePos = SwingFXUtils.toFXImage(bufferedImagePos, null);
		positionView.setImage(imagePos);

		lookForItemsinThisRoom();
		listPlayerInventory();
		textArea.appendText("\n"+printWelcome(player) + "\n");
		textArea.appendText("\n"+player.showExitOfCurreentRoom() + "\n");
		controllTips();
	}

	/**
	 * This methode will help load the Ghost Image
	 */
	private void loadNpcImage() {
		Room room = player.getCurrentRoom();
		if (room.isThereNPC()) {
			NPC npc = room.getNPC();
			if (npc.isVisible()) {
				String path = npc.getImage(room.getShortDescription());
				URL imgURL = getClass().getClassLoader().getResource(path);
				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(imgURL);
					Image imageNpc = SwingFXUtils.toFXImage(bufferedImage, null);
					npcImageView.setImage(imageNpc);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				npcImageView.setImage(null);
			}
		} else {
			npcImageView.setImage(null);
		}
	}

	/**
	 * This Method return the Welcome message when a player start playing the game
	 * 
	 * @return welcome
	 */
	private String printWelcome(Player player) {
		String welcome = "\n Hi Detective " + player.getPlayersName() + " \n\n"
				+ "\nYou have been invited by the family of late Mr. Hallmark to their great-grandfather's villa.\n"
				+ "\nThere has been a report by the neighbors of paranormal activities in the villa.\n"
				+ "\nThe family wants you to check the buildings for this paranormal activities and solve the problem.\n "
				+ "\nPress the [help] button for help. \n";
		return welcome;

	}

	/**
	 * The tips will inform player of rooms to pay extra attentions to
	 */
	private void controllTips() {
		Room room = player.getCurrentRoom();
		if (room.isThereNPC()) {
			tips.setText("Press the button " + "'" + "Talk" + "'" + ". I have a feeling there is something here");
		} else {
			tips.setText("Always check the tips ... might come in handy");
		}
	}

	/**
	 * Method will serve as Exception to notify player of errors
	 * 
	 * @param titel-
	 *            title of the warning
	 * @param Header-
	 *            Header of the warning
	 * @param content
	 *            - content of the warning
	 */
	private void notifyError(String titel, String Header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle(titel);
		alert.setHeaderText(Header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public Player getplayer() {
		return this.player;
	}

	/**
	 * @param mainApp
	 *            the mainApp to set
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
