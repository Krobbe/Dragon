package model.game;

import java.io.Serializable;

import model.player.Bet;
import model.player.Player;

/**
 * A class that simulates a betting-round.
 * 
 * A betting-round ends when all active players have either checked or called the current bet.
 * @author lisastenberg
 * @author forssenm
 * @author mattiashenriksson
 */
public class BettingRound implements Serializable{
	private Bet currentBet;
	
	/**
	 * Creates a new betting-round
	 * @author lisastenberg
	 * @author mattiashenriksson
	 */
	public BettingRound() {
		currentBet = new Bet(new Player(), 0);
	}
	
	public BettingRound(Bet b) {
		currentBet = b;
	}
	
	/**
	 * 
	 * @return the current bet.
	 */
	public Bet getCurrentBet() {
		return currentBet;
	}
	
	/**
	 * Set the current bet to b.
	 * @param b	the bet you want to set the current bet to.
	 */
	public void setCurrentBet(Bet b) {
		currentBet = b;
	}
	
	/**
	 * Equals method for the BettingRound class
	 * @author forssenm
	 * @param o is the object you will compare with
	 * @return true if o is the same object or if o has the same currentBet
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
			BettingRound br = (BettingRound)o;
			return (this.currentBet.equals(br.currentBet));
		}
	}
	
	/**
	 * toString method for the BettingRound class
	 * @author forssenm
	 * @return a string with the form "Current bet is ..."
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Current bet is " + this.currentBet);
		return result.toString();
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}
	
}
