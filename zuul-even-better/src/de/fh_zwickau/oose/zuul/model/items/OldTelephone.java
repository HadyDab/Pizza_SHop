package de.fh_zwickau.oose.zuul.model.items;

import de.fh_zwickau.oose.zuul.model.Player;

/**
 * A telephone that extends the abstract class item and implements the methods
 * of this class.
 * 
 * @author Hady, XardsLP
 *
 */
public class OldTelephone extends Item {


	/**
	 * 
	 */
	private static final long serialVersionUID = 24L;

	public OldTelephone(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This Item can be use to make a call
	 */
	@Override
	public boolean useItem(Player player, String string) {
		// TODO Auto-generated method stub
		return false;
	}

}
