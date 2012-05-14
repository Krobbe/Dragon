package model.player;

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
	 * To string method for the Balance class
	 * @author forssenm
	 * @return returns a string containing the current value
	 */
	public String toString() {
		String result = Integer.toString(value);
		return result;
	}
	
	/**
	 * Equals-method for a balance
	 * @author lisastenberg
	 * @param o is the object you will compare with
	 * @return true if balance is the same.
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
			Balance tmp = (Balance)o;
			return this.value == tmp.value;
		}
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}
}
