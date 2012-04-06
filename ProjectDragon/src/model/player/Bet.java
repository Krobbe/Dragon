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
 * @author mattiashenriksson
 */
public class Bet {
	private int value;
	private iPlayer owner;
	
	/**
	 * Creates a bet with a "default" player and the value 0
	 * @author mattiashenriksson
	 */
	public Bet() {
		this.owner = new User();
		this.value = 0;
	}
	
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
	
	/**
	 * Equals method for the Bet class.
	 * A bet A compared to another bet B is only equal if A = B.
	 * @author forssenm
	 * @param Object to compare with
	 * @return returns true if they are the same object
	 */
	@Override
	public boolean equals(Object o) {
		return (this == o);
	}
	
	/**
	 * toString method for the Bet class
	 * @author forssenm
	 * @return returns a string conting the value of the bet
	 */
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
	
	/**
	 * @author mattiashenriksson
	 */
	@Override
	public Bet clone() {
		return new Bet(owner, value);
	}
}
