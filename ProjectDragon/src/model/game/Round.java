package model.game;

import java.util.List;

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
	 * @return A list of the players who has won the current round
	 */
	public List<Player> decideWinners() {
		//TODO poker hand eveluator?
		return null;
	}
	
	
}
