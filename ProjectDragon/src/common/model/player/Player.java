package common.model.player;

import common.model.player.hand.IHand;
import common.model.player.hand.TexasHoldemHand;

/**
 * This class contains common methods and variables for all classes implementing
 * iPlayer.
 * 
 * @author mattiashenriksson
 * 
 */
public class Player implements IPlayer {
	
	private IHand hand;
	private boolean active = true;
	private String name;
	private Balance balance;
	private boolean stillInGame;
	private int ownCurrentBet = 0;
	private boolean doneFirstTurn;
	
	public Player() {
		this(new TexasHoldemHand(),"Default",
				new Balance());
	}
	
	public Player(IHand hand, String name, Balance balance) {
		this.hand = hand;
		this.name = name;
		this.balance = balance;
		stillInGame = false;
		doneFirstTurn = false;
	}

	@Override
	public IHand getHand() {
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
	public int getOwnCurrentBet() {
		return ownCurrentBet;
	}
	
	@Override
	public void setOwnCurrentBet(int value) {
		ownCurrentBet = value;
	}

	@Override
	public void setDoneFirstTurn(boolean value) {
		doneFirstTurn = value;
	}

	@Override
	public boolean hasDoneFirstTurn() {
		return doneFirstTurn;
	}

	@Override
	public boolean isAllIn() {
		return balance.getValue() == 0;
	}

	@Override
	public void makeBet(int value) {
		balance.removeFromBalance(value);
		setOwnCurrentBet(ownCurrentBet + value);
	}

	@Override
	public int compareTo(IPlayer o) {
		return this.getName().compareTo(o.getName());
	}
	
	// TODO Is this a correct equals method? (Symmetry etc.)
	/**
	 * Equals method for the Player class. Two objects are deemed equal if their
	 * user-names match 
	 * 
	 * @author robinandersson
	 * @param Object to compare with
	 * @return returns true if they are the same object
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} else if(this == o) {
			return true;
		} else if(!(o instanceof IPlayer)){
			return false;
		} else {
			IPlayer tmp = (IPlayer)o;
			return tmp.getName().equals(this.getName());
		}
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
				+ getOwnCurrentBet() + " , "+ "Done first bet?: " + hasDoneFirstTurn();
		return result;
	}

	// Since we at the current state aren't planning on using any hashtables
	// this code was added
	// for the cause of good practice
	public int hashCode() {
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}
	
	
}
