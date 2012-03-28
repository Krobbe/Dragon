/**
 * 
 */
package model.player;

import java.util.ArrayList;
import java.util.List;

import model.game.Card;

/**
 * @author Robin
 *
 */
public class Hand implements iHand {
	
	private List<Card> cards = new ArrayList<Card>(7);
	
	/**
	 * Throws away the hand's cards
	 */
	@Override
	public void discard() {
		cards = new ArrayList<Card>(7);
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

}
