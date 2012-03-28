package model.player.hand;


import java.util.List;

import model.game.Card;


/**
 * An interface for a hand in a poker game. 
 * @author lisastenberg
 *
 */
public interface iHand {

	/**
	 * @author lisastenberg
	 * Removes all cards from a hand.
	 */
	public void discard();
	
	/**
	 * @author lisastenberg
	 * @return a List of the cards in the hand.
	 */
	public List<Card> getCards();
	
	/**
	 * @author lisastenberg
	 * Add a new card to the hand.
	 * @param c the card you want to add.
	 */
	public void addCard(Card c);
	
	/**
	 * @author lisastenberg
	 * Makes the cards visible to other players.
	 * @param b a boolean.
	 */
	public void setVisible(boolean b);
	
	/**
	 * @author mattiashenriksson
	 * @return A boolean that tells if the hand is currently visible
	 */
	public boolean isVisible();
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public String toString();
}
