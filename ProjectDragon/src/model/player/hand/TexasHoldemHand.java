/**
 * 
 */
package model.player.hand;

import java.util.ArrayList;
import java.util.List;

import model.game.Card;

/**
 * A class representing a standard Texas Hold'em hand
 * 
 * @author Robin
 *
 */
public class TexasHoldemHand implements iHand {
	
	private List<Card> cards;
	private boolean isVisible;
	
	public TexasHoldemHand(boolean isVisible){
		 this.isVisible = isVisible;
		 cards = new ArrayList<Card>(7);
	}
	
	/**
	 * Throws away the hand's cards
	 */
	@Override
	public void discard() {
		cards.clear();
	}

	/**
	 * Returns the hand's cards
	 */
	@Override
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * Adds a card to the hand
	 * @param card The card to be added
	 */
	@Override
	public void addCard(Card card) {
		cards.add(card);
	}
	
	/**
	 * Sets the visability of the card
	 * @param isVisible True if visible
	 */
	public void setVisability(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	/**
	 * 
	 * @return if the hand is visible (true) or not (false)
	 */
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
