package common.model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck implements IDeck {
	List<ICard> cards = new ArrayList<ICard>();
	
	/**
	 * Creates a new deck.
	 */
	public Deck() {
		createDeck();
		shuffle();
	}
	
	@Override
	public ICard popCard() {
		return cards.remove(0);
	}
	
	@Override
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * A help method that creates all the cards in a standard deck and adds 
	 * them to the list "cards".
	 */
	private void createDeck() {
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 4; j++) {
				Suit suite = Suit.class.getEnumConstants()[j];
				Rank rank = Rank.class.getEnumConstants()[i];
				cards.add(new Card(suite, rank));
			}
		}
	}
	
	/**
	 * toString method for the Deck class
	 * @author forssenm
	 * @return returns a string containing all of the cards that are in the deck
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(ICard c : this.cards) {
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
			IDeck deck = (IDeck)o;
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

