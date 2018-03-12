package de.fh_zwickau.oose.zuul.model.items;

import de.fh_zwickau.oose.zuul.model.Player;
import de.fh_zwickau.oose.zuul.model.Room;
import de.fh_zwickau.oose.zuul.model.NPC.NPC;

/**
 * A candle that extends the abstract class item and implements the methods of
 * this class.
 * 
 * @author HadyDab, XardsLP
 * 
 */
public class Candle extends Item {


	/**
	 * 
	 */
	private static final long serialVersionUID = 24L;

	public Candle(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * A Candle item, which can be use to summon Ghost.
	 */
	@Override
	public boolean useItem(Player player, String usage) {
		Room room = player.getCurrentRoom();
		if (room.isThereNPC()) {
			NPC npc = room.getNPC();
			npc.setIsVisible(true);
			return true;
		} else {
			return false;
		}

	}

}