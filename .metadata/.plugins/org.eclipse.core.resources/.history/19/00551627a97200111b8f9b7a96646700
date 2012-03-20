package Game;

import java.util.NoSuchElementException;

/**
 * A class that represents a Card. 
 * 
 * A card has a suite and a value, where value is between 1 and 13. 
 * Ace has the value 1.
 * 
 * @author lisastenberg
 *
 */

public class Card {
	public enum Suite {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}
	
	private int value;
	private Suite suite;
	
	public Card(Suite suite, int value) {
		if(!validValue(value)) {
			throw new NoSuchElementException("The value must be between 1 and 13.");
		}
		this.value = value;
		this.suite = suite;
	}
	
	/**
	 * The value is valid if it's between 1 and 13.
	 * 
	 * @param value
	 * @return true if the value is valid
	 */
	private boolean validValue(int value) {
		return value >= 1 && value <= 13;
	}
	
	/**
	 * 
	 * @return the value of the card.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return the suite of the card.
	 */
	public Suite getSuite() {
		return suite;
	}
}
