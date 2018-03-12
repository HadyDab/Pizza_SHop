/**
 * 
 */
package de.fh_zwickau.oose.zuul.savadata;
import java.io.Serializable;
import de.fh_zwickau.oose.zuul.model.GameWorld;
import de.fh_zwickau.oose.zuul.model.Player;

/**
 * The Class SaveData stores the save state of the game
 * @author HadyDab
 *
 */
public class SaveData implements Serializable {

	private static final long serialVersionUID = 20L;

	public String curentRoomName;    

	public String lastLookingDirection;

	public Player player;
	
	public GameWorld world;

	
	
	public SaveData() {

	}

	
	/**
	 *  Set the current state of the player
	 * @param player to be set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Get the last saved state of the player
	 * @return the last saved player state
	 */
	public Player getplayer() {
		return this.player;
	}

	
	/**
	 * Set the current state of the gameworld
	 * @param world to be set
	 */
	public void setGameWorld(GameWorld world) {
		this.world = world;
	}
	
	/**
	 *  Get the last saved state of the gameworld
	 * @return the last saved gameworld
	 */
	public GameWorld getGameWorld() {
		return this.world;
	}


}
