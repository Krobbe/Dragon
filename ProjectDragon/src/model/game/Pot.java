package model.game;

import java.io.Serializable;

/**
 * A class that simulates a pot
 * @author lisastenberg
 * @author forssenm
 * @author mattiashenriksson
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
	 * @author mattiashenriksson
	 * @param value The initial money the pot should contain.
	 */
	public Pot(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return the value of the pot (in reality: how much's in the pot).
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @author mattiashenriksson
	 * @param value the value the pots value should be set to
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Add x to the pot.
	 * @param x	the 
	 */
	//TODO setValue ist?
	public void addToPot(int x) {
		value = value + x;
	}
	
	/**
	 * @author mattiashenriksson
	 * @param x The amount removed from the pot.
	 */
	public void removeFromPot(int x) {
		value -= x;
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
	
	/**
	 * Equals-method for a card
	 * @author forssenm
	 * @param o is the object you will compare with
	 * @return true if both objects have the same value (value of the pot)
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		else if(this == o) {
			return true;
		}
		else if (o.getClass() != this.getClass()) {
			return false;
		}
		else {
			Pot pot = (Pot)o;
			return (this.value == pot.value);
		}
	}
	
	/**
	 * toString method for the Pot class
	 * @author forssenm
	 * @return returns a string containing the value of the pot
	 */
	@Override
	public String toString() {
		String result = Integer.toString(this.value);
		return result;
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}
	
}
