package Game;
/**
 * A class that simulates a pot
 * @author lisastenberg
 *
 */

public class Pot {
	private int pot;
	
	/**
	 * Constructs a pot that contains no money.
	 */
	public Pot() {
		pot = 0;
	}

	/**
	 * 
	 * @return the value of the pot (in reality: how much's in the pot).
	 */
	public int getPot() {
		return pot;
	}

	/**
	 * Add x to the pot.
	 * @param x	the 
	 */
	public void addToPot(int x) {
		pot = pot + x;
	}
	
	/**
	 * Clears the pot. 
	 * 
	 * For example when a person wins a round, that person gets what's in the pot
	 * and the pot is set to 0.
	 */
	public void emptyPot() {
		pot = 0;
	}
	
}