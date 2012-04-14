package client.model.player.hand;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import client.model.game.Card;
import client.model.game.CardComparator;
/**
 * A class that simulates a full Texas Hold'em Hand. 
 * 
 * FullTHHand contains 7 cards (a players cards and the cards laying on the
 * table). All cards in the hand are sorted by rank, with the card with the
 * highest rank first. 
 * 
 * @author lisastenberg
 *
 */
public class FullTHHand implements iHand {
	private List<Card> cards = new LinkedList<Card>();
	private boolean isVisible = true;
	
	public FullTHHand() {
		
	}
	
	public FullTHHand(List<Card> cards) {
		addCards(cards);
	}
	
	@Override
	public void discard() {
		cards.clear();
	}

	@Override
	public List<Card> getCards() {
		return cards;
	}

	@Override
	public void addCard(Card c) {
		cards.add(c);
		Collections.sort(cards, new CardComparator());
	}
	
	/**
	 * Add cards to the list of cards from the list c.
	 * @param c the list with card you want to add.
	 */
	public void addCards(List<Card> c) {
		cards.addAll(c);
		Collections.sort(cards, new CardComparator());
	}
	
	/**
	 * Add cards to the list of cards from hand.
	 * @param hand the hand you want to add.
	 */
	public void addCards(iHand hand) {
		addCards(hand.getCards());
	}

	@Override
	public void setVisible(boolean b) {
		isVisible = b;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		else if (o.getClass() != this.getClass()) {
			return false;
		}
		else {
			FullTHHand tmp = (FullTHHand)o;
			return (this.getCards().equals(tmp.getCards()));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Full hand: ");
		result.append(cards.toString());
		return result.toString();
	}
	
	//Since we at the current state aren't planning on using any hashtables 
	//this code was added for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}

}
