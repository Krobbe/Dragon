package common.model.player;

import java.io.Serializable;
import java.util.Comparator;

import common.model.player.hand.IHand;




/**
 * An interface that represents a player.
 * @author lisastenberg
 * @author mattiashenriksson
 * @author robinandersson
 */
public interface IPlayer extends Comparable<IPlayer>, Serializable  {

	/**
	 * @author lisastenberg
	 * @return the hand of the player.
	 */
	public IHand getHand();
	
	/**
	 * A method that can set the player active or unactive. An active player is 
	 * one who has chips left and has not yet folded.
	 * @author lisastenberg
	 * @param b true if you want to set the player active. 
	 */
	public void setActive(boolean b);
	
	/**
	 * Sets if the player is still in the game (has chips left).
	 * @author robinandersson
	 * @param b true if the player should be in the game, occurs at rebuys for
	 * example. False is the player has lost the game. 
	 */
	public void setStillInGame(boolean b);
	
	/**
	 * @author lisastenberg
	 * @return the balance of the player.
	 */
	public Balance getBalance();
	
	/**
	 * Shows if the player has folded or is still in competition for the pot.
	 * @author mattiashenriksson
	 * @return True if the player is still in competition, false if he has
	 * folded.
	 */
	public boolean isActive();
	
	/**
	 * Shows if the player is still in the game (has chips left).
	 * @author robinandersson
	 * @return True if the player is still in the game. False is the player has
	 * lost or quit the game. 
	 */
	public boolean isStillInGame();
	
	/**
	 * Shows the name of the player
	 * @author mattiashenriksson
	 * @return The name of the player.
	 */
	public String getName();
	
	/**
	 * @author mattiashenriksson
	 * @return The value of the last bet the player placed during the current 
	 * betting round. Returns -1 if the player hasn't yet placed a bet.
	 */
	//TODO bättre returnera Bet?
	public int getOwnCurrentBet();
	
	/**
	 * Sets the value of the last bet the player placed
	 * @author mattiashenriksson
	 */
	public void setOwnCurrentBet(int value);
	
	/**
	 * Sets if the player has done its first turn or not. Set true if the player
	 * has done its first turn.
	 * 
	 * @param b	The boolean
	 */
	public void setDoneFirstTurn(boolean b);
	
	/**
	 * Return true if the player has done its first turn.
	 * 
	 * @return the variable doneFirstTurn.
	 */
	public boolean hasDoneFirstTurn();
	
	/**
	 * 
	 * @return a boolean telling if the player is all-in (has a balance of 0)
	 */
	public boolean isAllIn();
	
	/**
	 * Posts a bet from the player
	 * @author mattiashenriksson
	 * @param value The value on the bet that should be posted
	 */
	public void makeBet(int value);
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public String toString();
}
