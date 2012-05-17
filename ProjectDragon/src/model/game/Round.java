package model.game;

import java.io.Serializable;

/**
 * A class that simulates a Round in a pokergame. 
 * 
 * A round ends with a player winning the pot. 
 * @author lisastenberg
 * @author mattiashenriksson
 */
public class Round implements Serializable{
	private BettingRound bettingRound;
	private Pot pot;
	private Pot preBettingPot;
	
	/**
	 * Creates a new Round.
	 */
	public Round() {
		pot = new Pot();
		bettingRound = new BettingRound();
		preBettingPot = new Pot();
	}
	
	/**
	 * @author mattiashenriksson
	 * @param pot The pot the new round should contain
	 * @param bettingRound The pot the new round should contain
	 */
	public Round(Pot pot, Pot preBettingPot, BettingRound bettingRound) {
		this.pot = pot;
		this.bettingRound = bettingRound;
		this.preBettingPot = preBettingPot;
	}
	
	/**
	 * 
	 * @return the bettingRound.
	 */
	public BettingRound getBettingRound() {
		return bettingRound;
	}

	/**
	 * 
	 * @return the pot.
	 */
	public Pot getPot() {
		return pot;
	}
	
	/**
	 * 
	 * @return the "pre betting pot"
	 */
	public Pot getPreBettingPot() {
		return preBettingPot;
	}
		
	
}
