package model.game;


/**
 * A class that represents a Card. 
 * 
 * A card has a suite and a value, where value is between 1 and 13. 
 * Ace has the value 1.
 * 
 * @author lisastenberg
 * @author forssenm
 *
 */

public class Card {
	public enum Suite {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}
	
	public enum Rank {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING; 
	}
	
	private Rank rank;
	private Suite suite;
	
	public Card(Suite suite, Rank rank) {
		this.rank = rank;
		this.suite = suite;
	}
	
	/**
	 * @author lisastenberg
	 * @return the value of the card.
	 */
	public Rank getRank() {
		return rank;
	}
	
	/**
	 * @author lisastenberg
	 * @return the suite of the card.
	 */
	public Suite getSuite() {
		return suite;
	}
	
	/**
	 * @author lisastenberg
	 * Compare two card's rank. 
	 * @param card the card you want to compare with. 
	 * @return > 0 if this.rank is higher than card.rank
	 */
	public int compareTo(Card card) {
		return this.getRank().compareTo(card.getRank());
	}
	
	/**
	 * Equals-method for a card
	 * @author forssenm
	 * @param o is the object you will compare with
	 * @return true if suite and value are the same for both cards
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		else if (o.getClass() != this.getClass()) {
			return false;
		}
		else {
			Card card = (Card)o;
			return (this.suite == card.suite && this.rank == card.rank);
		}
	}
	
	/**
	 * @author forssenm
	 * toString method for the card class
	 * @return returns a string in the form of "3 of spades"
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.rank + " of " + this.suite);
		return result.toString();
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}
}
