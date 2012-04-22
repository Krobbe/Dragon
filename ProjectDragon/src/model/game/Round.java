package model.game;

/**
 * A class that simulates a Round in a pokergame. 
 * 
 * A round ends with a player winning the pot. 
 * @author lisastenberg
 * @author mattiashenriksson
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
	 * @author mattiashenriksson
	 * @param pot The pot the new round should contain
	 * @param bettingRound The pot the new round should contain
	 */
	public Round(Pot pot, BettingRound bettingRound) {
		this.pot = pot;
		this.bettingRound = bettingRound;
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
