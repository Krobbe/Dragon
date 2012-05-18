package model.game;

import model.card.Deck;
import model.card.ICard;
import model.card.IDeck;

/**
 * A class that represents a dealer. 
 * 
 * @author lisastenberg 
 * @author forssenm
 * @author mattiashenriksson
 *
 */

public class TexasHoldemDealer implements IDealer {
	private IDeck d;
	
	public TexasHoldemDealer() {
		d = new Deck();
	}
	
	/**
	 * 
	 * @return the top card of the deck.
	 */
	public ICard popCard() {
		return d.popCard();
	}
	
	/**
	 * Creates a new full and shuffled deck for the dealer
	 * @author mattiashenriksson
	 */
	public void newDeck() {
		d = new Deck();
	}
	
	/**
	 * toString method for the Dealer class
	 * @author lisastenberg
	 * @return returns a string containing all of the cards that are in the deck
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Dealer with deck: \n");
		result.append(d.toString());
		return result.toString();
	}
	
	/**
	 * Equals-method for a dealer
	 * @author lisastenberg
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
			TexasHoldemDealer dealer = (TexasHoldemDealer)o;
			while(dealer.popCard() != null && this.popCard() != null) {
				if(!this.popCard().equals(dealer.popCard())) {
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
