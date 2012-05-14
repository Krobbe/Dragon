package model.player;

import model.card.ICard;
import model.player.hand.IHand;



/**
 * An interface that represents a player.
 * @author lisastenberg
 * @author mattiashenriksson
 * @author robinandersson
 */
public interface IPlayer extends Comparable<IPlayer> {

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
	//TODO: används denna nu?
	public boolean isStillInGame();
	
	/**
	 * Shows the name of the player
	 * @author mattiashenriksson
	 * @return The name of the player.
	 */
	public String getName();
	
	//TODO ska vi ha kvar denna metod ? law of demeters
	/**
	 * Gives the player a card which is added to the player's hand
	 * @author mattiashenriksson
	 */
	public void addCard(ICard c);

	
	/**
	 * @author mattiashenriksson
	 * @return The value of the last bet the player placed during the current 
	 * betting round. Returns -1 if the player hasn't yet placed a bet.
	 */
	//TODO bättre returnera Bet?
	public int getOwnCurrentBet();
	
	/**
	 * Sets the value of the last bet the player placed? 
	 * //TODO sets lastBetValue ist?
	 * @author mattiashenriksson
	 */
	public void setOwnCurrentBet(int value);
	
	// TODO Explain what this method does. What is doneFirstTurn?
	/**
	 * Sets the varible doneFirstTurn.
	 * @param value
	 */
	public void setDoneFirstTurn(boolean value);
	
	/**
	 * 
	 * @return the variable doneFirstTurn.
	 */
	public boolean getDoneFirstBet();
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public String toString();
}
