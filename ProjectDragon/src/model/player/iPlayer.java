package model.player;

import model.game.Card;
import model.player.hand.iHand;

/**
 * An interface that represents a player.
 * @author lisastenberg
 * @author mattiashenriksson
 */
public interface iPlayer {

	/**
	 * @author lisastenberg
	 * @return the hand of the player.
	 */
	public iHand getHand();
	
	/**
	 * @author lisastenberg
	 * A method that can set a player active or unactive. 
	 * @param b true if you want to set the player active. 
	 */
	public void setActive(boolean b);
	
	/**
	 * @author lisastenberg
	 * @return the balance of the player.
	 */
	public Balance getBalance();
	
	/**
	 * @author mattiashenriksson
	 * @return A boolean telling if the player is active.
	 */
	public boolean isActive();
	
	/**
	 * @author mattiashenriksson
	 * @return The name of the player.
	 */
	public String getName();
	
	/**
	 * Gives the player a card which he can add to his hand
	 * @author mattiashenriksson
	 */
	public void addCard(Card c);
	
	/**
	 * Removes amount from iPlayer's Balance
	 * @param amount The amount to remove
	 * @author mattiashenriksson
	 */
	public void removeFromBalance(int amount);
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public String toString();
}
