package Player;
/**
 * A class for balance. 
 * 
 * @author lisastenberg
 *
 */

public class Balance {
	private int balance;
	
	public Balance() {
		this(0);
	}
	
	public Balance(int bal) {
		balance = bal;
	}
	
	/**
	 * 
	 * @return the value of the balance.
	 */
	public int getBalance() {
		return balance;
	}
	
	/**
	 * Add x to the balance.
	 * @param x	the value you want to add to the balance.
	 */
	public void addToBalance(int x) {
		balance = balance + x;
	}
	
	/**
	 * Remove x from balance. 
	 * @param x the value you want to remove from the balance. 
	 */
	public void removeFromBalance(int x) {
		balance = balance - x;
	}
}