package de.fh_zwickau.oose.zuul.view;

import de.fh_zwickau.oose.zuul.MainApp;
import de.fh_zwickau.oose.zuul.model.GameWorld;
import de.fh_zwickau.oose.zuul.model.Player;
import de.fh_zwickau.oose.zuul.savadata.ResourceManager;
import de.fh_zwickau.oose.zuul.savadata.SaveData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * Start Controller this class control the StartOverview
 * 
 * @author HadyDab, XardsLP
 *
 */
public class StartControler {

	@FXML
	private TextField textField;

	private MainApp mainApp;

	public StartControler() {
	}
	
		
	/**
	 *  Start a new game with stating position set outside 
	 */
	@FXML
	private void handleStartNewButton() {
		Player player = new Player();
		GameWorld world = new GameWorld("World");
		if (textField.getText().trim().isEmpty()) {
			notifyError("Player's Name", "Player's Name required", "Please enter a Name");
		} else {
			String playersName = textField.getText();
			player.setPlayersName(playersName);
			mainApp.loadGameOverView(world,player, "outside", "south");
		}
	}

	
	
	/**
	 *  Load a saved game if player as already save a process
	 */
	@FXML
	private void handleLoadGamebutton() {
		if (textField.getText().trim().isEmpty()) {
			notifyError("Player's Name", "Player's Name required", "Please enter a Name");
		} else {

			try {
				String playerfilename = textField.getText();
				SaveData data = (SaveData) ResourceManager.load(playerfilename + ".save");
				String roomName = data.curentRoomName;
				String lookingDirection = data.lastLookingDirection;
				Player player = data.getplayer();
				GameWorld world = data.getGameWorld();
				mainApp.loadGameOverView(world,player, roomName, lookingDirection);
			} catch (Exception e) {
				e.getMessage();
				notifyError("No save file", "", "Saved game can not be found");
			}

		}

	}

	/**
	 * Method will serve as Exception to notify player of errors
	 * 
	 * @param titel
	 * @param Header
	 * @param content
	 */
	private void notifyError(String titel, String Header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle(titel);
		alert.setHeaderText(Header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	/**
	 * @param mainApp
	 *            the mainApp to set
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
