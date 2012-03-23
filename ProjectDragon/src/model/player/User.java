package model.player;

/**
 * A class that simulates the local player.
 * @author lisastenberg
 *
 */
public class User implements iPlayer{
	private Balance balance;
	private iHand hand;
	private boolean isActive;

	public User() {
		this(new Balance());
	}
	
	public User(Balance bal) {
		balance = bal;
		isActive = true;
		//TODO: hand = new UserHand;
	}

	@Override
	public iHand getHand() {
		return hand;
	}

	@Override
	public void setActive(boolean b) {
		isActive = b;
	}
	
	@Override
	public Balance getBalance() {
		return balance;
	}
}
