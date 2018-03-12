/**
 * 
 */
package de.fh_zwickau.oose.zuul.model.NPC;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import de.fh_zwickau.oose.zuul.model.GameWorld;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Class NPC - a npc in an adventure game.
 *
 * This class is part of the "Ghost House" application. "Ghost House" is a very
 * simple, text based adventure game.
 * 
 * The NPC can interact with player
 * 
 * @author HadyDab, XardsLP
 *
 */
public abstract class NPC implements Serializable {
	private static final long serialVersionUID = 25L;

	private boolean isVisible;
	private Map<String, String> options;
	private Map<String, String> optionsAnswers;
	private Map<String, String> image;

	private String name;

	/**
	 * Constructor of the NPC
	 * 
	 * @param name of the npc
	 */
	public NPC(String name) {
		this.name = name;
		this.image = new HashMap<>();
		this.options = new HashMap<>();
		this.optionsAnswers = new HashMap<>();
	}

	/**
	 * Getter of the name of the NPC
	 * 
	 * @return the name of the npc
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method to add options of questions available to the NPC
	 * 
	 * @param optionsNumber of the questions
	 * @param questions question associated to this option
	 */
	public void addOptions(String optionsNumber, String questions) {
		this.options.put(optionsNumber, questions);
	}

	/**
	 * Method to add answers for available questions of the npc
	 * 
	 * @param questionsNumber the question number
	 * @param answer associated to the question
	 */
	public void addOptionAnswer(String questionsNumber, String answer) {
		this.optionsAnswers.put(questionsNumber, answer);
	}

	/**
	 * Setter to add a image to a NPC
	 * 
	 * @param imageName of the file
	 * @param imageLocation loaction of the image file
	 */
	public void addImage(String imageName, String imageLocation) {
		this.image.put(imageName, imageLocation);
	}

	/**
	 * Getter for return the imageName of the NPC
	 * 
	 * @param imageName of the file 
	 * @return the image file of the npc
	 */
	public String getImage(String imageName) {
		return this.image.get(imageName);
	}

	/**
	 * Getter that return a boolean when the npc is visible.
	 * 
	 * @return the visibility value
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/**
	 * Set the visibility of the npc
	 * 
	 * @param isVisible
	 *            the boolean value to set
	 */
	public void setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * @return the options
	 */
	public Map<String, String> getOptions() {
		return options;
	}

	/**
	 * @return the optionsAnswers
	 */
	public Map<String, String> getOptionsAnswers() {
		return optionsAnswers;
	}

	/**
	 * Check the task given to the player 
	 * @param world in which the player is in
	 */
	public abstract void checktask(GameWorld world);

	/**
	 * Display the options of the npc to the player
	 * 
	 * @param output the options available by this npc
	 */
	public abstract void DisplayOptions(TextArea output);

	/**
	 * Handle the input of the player
	 * 
	 * @param input-
	 *            player input word
	 * @param output-
	 *            npc response
	 */
	public abstract void handleOption(TextField input, TextArea output);

}
