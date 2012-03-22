/**
 * 
 */
package Player;

/**
 * A class that simulates a bet.
 * 
 * A bet has a value and a person (that placed the bet).
 * 
 * @author lisastenberg
 *
 */
public class Bet {
	private int value;
	//private Player owner;
	
	/**
	 * Creates a Bet with a value and an owner to the bet. 
	 * @param owner The player that placed the bet.
	 * @param value The value that the player bet.
	 */
	/*
	public Bet(Player owner, int value) {
		this.value = value;
		this.owner = owner;
	}
	*/
	
	/**
	 * 
	 * @return the value of the bet.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return the owner of the bet.
	 */
	/*
	public Player getOwner() {
		return owner;
	}
	*/
	
}