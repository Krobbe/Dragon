package model.game;

import java.util.List;

import model.player.iPlayer;

/**
 * A class that simulates a Round in a pokergame. 
 * 
 * A round ends with a player winning the pot. 
 * @author lisastenberg
 *
 */
public class Round {
	private BettingRound bettingRound;
	private Pot pot;
	
	/**
	 * Creates a new Round.
	 */
	public Round() {
		pot = new Pot();
		bettingRound = new BettingRound();
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
}
