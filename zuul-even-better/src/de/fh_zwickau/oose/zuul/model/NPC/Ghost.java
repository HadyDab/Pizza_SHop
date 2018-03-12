package de.fh_zwickau.oose.zuul.model.NPC;

import java.util.Iterator;

import de.fh_zwickau.oose.zuul.model.GameTask;
import de.fh_zwickau.oose.zuul.model.GameWorld;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * A Ghost that extends the abstract class NPC and implements the methods of
 * this class.
 * 
 * @author Hady, XardsLP
 *
 */
public class Ghost extends NPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 25L;
	private GameTask gametask = new GameTask("task1");

	public Ghost(String name) {
		super(name);
	}

	/**
	 *  Display the options of questions available by talking to this npc
	 */
	@Override
	public void DisplayOptions(TextArea output) {
		output.appendText("\n Hi my name is " + this.getName() + " how may i help you \n");
		for (Iterator<String> i = this.getOptions().values().iterator(); i.hasNext();) {
			output.appendText("\n" + i.next() + " \n ");
		}

	}

	/**
	 *  Handle questions key in my player 
	 */
	@Override
	public void handleOption(TextField input, TextArea output) {

		if (this.getOptionsAnswers().containsKey(input.getText())) {
			output.appendText("\n" + this.getOptionsAnswers().get(input.getText()) + "\n");
		} else {
			output.appendText("\n Wrong Option! please choose the right options available \n");
		}

	}

	
	/**
	 *  Check if the task given to the player has been completed
	 */
	@Override
	public void checktask(GameWorld world) {
		if (gametask.checkItemInRoom(world,"diningRoom", "oldKey")) {
			this.addOptionAnswer("1", "Task has already been completed");
			this.addOptionAnswer("2", "Thank you! You completed the game.");
		}
	}

}
