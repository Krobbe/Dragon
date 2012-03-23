package model.player;

import model.game.Card;

/**
 * A class for balance. 
 * 
 * @author lisastenberg
 *
 */

public class Balance {
	private int value;
	
	public Balance() {
		this(0);
	}
	
	public Balance(int val) {
		value = val;
	}
	
	/**
	 * @author lisastenberg
	 * @return the value of the balance.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @author lisastenberg
	 * Add x to the balance.
	 * @param x	the value you want to add to the balance.
	 */
	public void addToBalance(int x) {
		value = value + x;
	}
	
	/**
	 * @author lisastenberg
	 * Remove x from balance. 
	 * @param x the value you want to remove from the balance. 
	 */
	public void removeFromBalance(int x) {
		if(x > value) {
			throw new IllegalArgumentException("You do not have enough money to remove " + x  + " from your balance");
		}
		value = value - x;
	}
	
	/**
	 * Equals-method for a balance
	 * @author lisastenberg
	 * @param o is the object you will compare with
	 * @return true if balance is the same.
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		else if (o.getClass() != this.getClass()) {
			return false;
		}
		else {
			Balance tmp = (Balance)o;
			return this.value == tmp.value;
		}
	}
}
