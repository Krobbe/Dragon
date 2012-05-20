package common.model.player;

import common.model.player.hand.IHand;


/**
 * A class that simulates the local player.
 * @author lisastenberg
 * @author mattiashenriksson
 */
public class User implements IPlayer {
	
	Player player;

	public User() {
		this(new Player());
	}
	
	public User(Player player) {
		this.player = player;
	}

	@Override
	public IHand getHand() {
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
	public int getOwnCurrentBet() {
		return player.getOwnCurrentBet();
	}

	@Override
	public void setOwnCurrentBet(int value) {
		player.setOwnCurrentBet(value);
	}
	
	/**
	 * Equals method for the User class
	 * @author forssenm
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
	 * Tostring method for the User class
	 * @author forssenm
	 * @author mattiashenriksson
	 * @return returns a string in the form of "Name: Charles , Balance: 1200"
	 */
	@Override
	public String toString() {
		return player.toString();
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}

	@Override
	public void setDoneFirstTurn(boolean value) {
		player.setDoneFirstTurn(value);
		
	}

	@Override
	public boolean hasDoneFirstTurn() {
		return player.hasDoneFirstTurn();
	}

	@Override
	public boolean isAllIn() {
		return player.isAllIn();
	}

	@Override
	public void makeBet(int value) {
		player.makeBet(value);
	}

	@Override
	public int compareTo(IPlayer o) {
		return this.getName().compareTo(o.getName());
	}

}
