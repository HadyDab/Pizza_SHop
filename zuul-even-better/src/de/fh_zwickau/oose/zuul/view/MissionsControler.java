package de.fh_zwickau.oose.zuul.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Missions Controler class to controll the MissionsView.fxml data
 * 
 * @author Hady, XardsLP
 *
 */
public class MissionsControler {

	@FXML
	private TextArea description;

	private Stage missionStage;

	public MissionsControler() {

	}

	/**
	 * Initiate this Controller, this method will be called in the main class
	 */
	public void initControler() {

	}

	/**
	 * Quit the missions view and go back to the game view
	 */
	@FXML
	public void handleMissionsQuit() {
		missionStage.close();
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param missionStage the stage to be set 
	 */
	public void setDialogStage(Stage missionStage) {
		this.missionStage = missionStage;
	}

}
