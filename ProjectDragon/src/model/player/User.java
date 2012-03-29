package model.player;

import model.game.Card;
import model.player.hand.TexasHoldemHand;
import model.player.hand.iHand;

/**
 * A class that simulates the local player.
 * @author lisastenberg
 * @author mattiashenriksson
 */
public class User implements iPlayer {
	
	Player player;

	public User() {
		this(new Player(new TexasHoldemHand(false), "Default", new Balance()));
	}
	
	public User(Player player) {
		this.player = player;
	}

	@Override
	public iHand getHand() {
		return player.getHand();
	}

	@Override
	public void setActive(boolean b) {
		player.setActive(b);
	}
	
	@Override
	public void setStillInGame(boolean b) {
		this.player.setStillInGame(b);
		
	}
	
	@Override
	public Balance getBalance() {
		return player.getBalance();
	}

	@Override
	public boolean isActive() {
		return player.isActive();
	}
	
	@Override
	public boolean isStillInGame() {
		return this.player.isStillInGame();
	}

	@Override
	public String getName() {
		return player.getName();
	}
	
	@Override
	public void addCard(Card c) {
		player.addCard(c);
		
	}
	
	@Override
	public void removeFromBalance(int amount) {
		player.removeFromBalance(amount);
	}
	
	/**
	 * Equals method for the User class
	 * @author forssenm
	 * @param Object to compare with
	 * @return returns true if they are the same object
	 */
	@Override
	public boolean equals(Object o) {
		return (this == o);
	}
	
	/**
	 * Tostring method for the User class
	 * @author forssenm
	 * @return returns a string in the form of "Name: Charles , Balance: 1200"
	 */
	@Override
	public String toString() {
		String result = ("Name: " + getName() + " , " + "Balance: " + getBalance());
		return result;
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}

}
