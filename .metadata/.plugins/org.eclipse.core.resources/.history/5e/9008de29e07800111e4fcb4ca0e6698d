/**
 * 
 */
package model.player;

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
	private iPlayer owner;
	
	/**
	 * Creates a Bet with a value and an owner to the bet. 
	 * @param owner The player that placed the bet.
	 * @param value The value that the player bet.
	 */
	public Bet(iPlayer owner, int value) {
		this.value = value;
		this.owner = owner;
	}
	
	/**
	 * @author lisastenberg
	 * @return the value of the bet.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @author lisastenberg
	 * @return the owner of the bet.
	 */
	public iPlayer getOwner() {
		return owner;
	}
	
	@Override
	public boolean equals(Object o) {
		return (this == o);
	}
	
	@Override
	public String toString() {
		String result = Integer.toString(value);
		return result;
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}
}
