package model.player;

import model.game.Card;
import model.player.hand.TexasHoldemHand;
import model.player.hand.iHand;

/**
 * This class contains common methods and variables for all classes implementing
 * iPlayer.
 * 
 * @author mattiashenriksson
 * 
 */
public class Player implements iPlayer {
	
	private iHand hand;
	private boolean active = false;
	private String name;
	private Balance balance;
	private boolean stillInGame;
	private int ownCurrentBet = -1; //TODO döpa -1 till typ "NO_BET" ? 
								   //TODO ska -1 sättas i konstruktorn?
	private boolean doneFirstTurn;
	
	public Player() {
		this(new TexasHoldemHand(false),"Default",
				new Balance());
	}
	
	//TODO: Ska inte player ha ett account? 
	public Player(iHand hand, String name, Balance balance) {
		this.hand = hand;
		this.name = name;
		this.balance = balance;
		stillInGame = true;
		doneFirstTurn = false;
	}

	@Override
	public iHand getHand() {
		return hand;
	}

	@Override
	public void setActive(boolean b) {
		active = b;
	}
	
	@Override
	public void setStillInGame(boolean b) {
		stillInGame = b;
	}

	@Override
	public Balance getBalance() {
		return balance;
	}

	@Override
	public boolean isActive() {
		return active;
	}
	
	@Override
	public boolean isStillInGame() {
		return stillInGame;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addCard(Card c) {
		hand.addCard(c);
	}
	
	@Override
	public int getOwnCurrentBet() {
		return ownCurrentBet;
	}
	
	@Override
	public void setOwnCurrentBet(int value) {
		ownCurrentBet = value;
	}

	/**
	 * Equals method for the User class. A user A is equal to a user
	 * B if and only if A = B.
	 * 
	 * @author forssenm
	 * @param Object to compare with
	 * @return returns true if they are the same object
	 */
	@Override
	public boolean equals(Object o) {
		return (this == o);
	}

	/**
	 * Tostring method for the Player class
	 * @author forssenm
	 * @author mattiashenriksson
	 * @return returns a string containing the name, balance, hand and if the
	 *         user is active or not
	 */
	@Override
	public String toString() {
		String result = ("Name: " + getName() + " , " + "Balance: "
				+ getBalance() + " , " + "Active: " + isActive() + " , "
				+ "Hand: " + getHand().toString()) + " , " + "Own current bet: "
				+ getOwnCurrentBet() + " , "+ "Done first bet?: " + getDoneFirstBet();
		return result;
	}

	// Since we at the current state aren't planning on using any hashtables
	// this code was added
	// for the cause of good practice
	public int hashCode() {
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}

	/**
	 * Compare a player against another player by their names. 
	 */
	@Override
	public int compareTo(iPlayer p) {
		return this.getName().compareTo(p.getName());
	}

	@Override
	public void setDoneFirstTurn(boolean value) {
		doneFirstTurn = value;
		
	}

	@Override
	public boolean getDoneFirstBet() {
		return doneFirstTurn;
	}
}
