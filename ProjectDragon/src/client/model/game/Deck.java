package client.model.game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import client.model.game.Card.Rank;
import client.model.game.Card.Suit;

/**
 * A class that represents a Deck.
 * 
 * @author lisastenberg
 *
 */

public class Deck {
	List<Card> cards = new ArrayList<Card>();
	
	/**
	 * Creates a new deck.
	 */
	public Deck() {
		createSuite();
		shuffle();
	}
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * A help method that creates a suite with all 13 cards in the deck.
	 * @param suite
	 */
	private void createSuite() {
		for(int i = 0; i <= 12; i++) {
			for(int j = 0; j < 4; j++) {
				Suit suite = Suit.class.getEnumConstants()[j];
				Rank rank = Rank.class.getEnumConstants()[i];
				cards.add(new Card(suite, rank));
			}
		}
	}
	
	public Card popCard() {
		return cards.remove(0);
	}
	
	/**
	 * toString method for the Deck class
	 * @author forssenm
	 * @return returns a string containing all of the cards that are in the deck
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(Card c : this.cards) {
			result.append(c.toString() + "\n");
		}
		return result.toString();
	}
	
	/**
	 * Equals-method for a deck
	 * @author forssenm
	 * @param o is the object you will compare with
	 * @return true if both decks contain the same cards
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		else if(this == o) {
			return true;
		}
		else if (o.getClass() != this.getClass()) {
			return false;
		}
		else {
			Deck deck = (Deck)o;
			while(deck.popCard() != null && this.popCard() != null) {
				if(!this.popCard().equals(deck.popCard())) {
					return false;
				}
			}
			return true;
		}
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}
}
