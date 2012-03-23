package model.game;
/**
 * A class that simulates a pot
 * @author lisastenberg
 *
 */

public class Pot {
	private int value;
	
	/**
	 * Constructs a pot that contains no money.
	 */
	public Pot() {
		value = 0;
	}

	/**
	 * 
	 * @return the value of the pot (in reality: how much's in the pot).
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Add x to the pot.
	 * @param x	the 
	 */
	public void addToPot(int x) {
		value = value + x;
	}
	
	/**
	 * Clears the pot. 
	 * 
	 * For example when a person wins a round, that person gets what's in the pot
	 * and the pot is set to 0.
	 */
	public void emptyPot() {
		value = 0;
	}
	
}
