package de.fh_zwickau.oose.zuul.model.items;

import java.io.Serializable;

import de.fh_zwickau.oose.zuul.model.Player;

/**
 * Class Item - a item in an adventure game.
 *
 * This class is part of the "Ghost House" application. "Ghost House" is a very
 * simple, text based adventure game.
 * 
 * Items can be pick , drop and use.
 * 
 * @author HadyDab, XardsLP
 * 
 */
public abstract class Item implements Serializable {
	private static final long serialVersionUID = 24L;

	private String name;

	/**
	 * The Constructor of the item with a description of the item.
	 * 
	 * @param name of this item
	 */
	public Item(String name) {
		this.name = name;
	}

	/**
	 * Get the name of this item
	 * 
	 * @return the name of the item
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set a name for this item
	 * 
	 * @param name-
	 *            the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The method to use the item.
	 * 
	 * @param player-
	 *            the player using the item
	 * @param usage
	 *            - purpose of use if any
	 * @return true if successful else return false
	 */
	public abstract boolean useItem(Player player, String usage);

}
