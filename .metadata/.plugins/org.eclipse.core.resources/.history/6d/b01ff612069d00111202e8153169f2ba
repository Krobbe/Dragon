/**
 * 
 */
package model.player.hand;

import java.util.List;

import model.card.Card;
import model.card.iCard;

/**
 * A class representing a standard Texas Hold'em hand
 * 
 * @author robinandersson
 *
 */
public class TexasHoldemHand implements iHand {
	
	private Hand hand;
	
	/**
	 * author lisastenberg
	 */
	public TexasHoldemHand() {
		this(false);
	}
	
	public TexasHoldemHand(boolean isVisible){
		 hand = new Hand(isVisible);
	}
	
	/**
	 * Throws away the hand's cards
	 */
	@Override
	public void discard() {
		hand.discard();
	}

	/**
	 * Returns the hand's cards
	 */
	@Override
	public List<iCard> getCards() {
		return hand.getCards();
	}

	/**
	 * Adds a card to the hand
	 * @param card The card to be added
	 */
	@Override
	public void addCard(iCard card) {
		hand.addCard(card);
	}
	
	/**
	 * Sets the visibility of the card
	 * @param isVisible True if visible
	 */
	public void setVisible(boolean isVisible) {
		hand.setVisible(isVisible);
	}
	
	/**
	 * Returns the visibility of the Hand - if a player is able to see the hand
	 * 
	 * @return if the hand is visible (true) or not (false)
	 */
	public boolean isVisible() {
		return hand.isVisible();
	}
	
	/**
	 * ToString method for the texasholdem-class.
	 * @author mattiashenriksson
	 * @author lisastenberg
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Texas Hold'em ");
		stringBuilder.append(hand.toString());
		return stringBuilder.toString();
	}	
	
	/**
	 * Determines if this hand is equal to another hand. Visibility does not
	 * matter
	 * 
	 * @return Two hands are equal if they are the same object or if they have
	 * the same cards, the order of the cards doesn't matter
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
		
		/*
		 * Compares the size of this and the foreign TexasHoldemHand
		 */
		else if (this.getCards().size() !=
						((TexasHoldemHand) o).getCards().size()){
			return false;
		}
		
		/*
		 * If the classes are the same the cards in each hand is compared
		 */
		else {
			
			/* 
			 * TODO Improve equals method in TexasHoldemHand. Perhaps duplicate
			 * the lists and remove occurrences from both lists if a match is
			 * found in order to remove the odd chance (should not occur though)
			 * of a duplicate card flagging for equals more than once. It would
			 * also be faster because every entry in the second list wouldn't be
			 * compared each time
			*/
			
			List<iCard> otherCards = ((TexasHoldemHand) o).getCards();
			
			//Every card in this hand is compared to the other Hand's cards
			for(iCard card : this.getCards()){
				
				boolean sameCards = false;
				
				// The loop ends if a match is found or if no match exists
				int i = 0;
				while(i < otherCards.size() && !sameCards){
					
					// The boolean is set to true if 
					if(card.equals(otherCards.get(i))){
						sameCards = true;
					}
					i++;
				}
				
				/* If no match is found for this hand's cards then the two hands
				 * are not equal
				 */
				if(!sameCards){
					return false;
				}
			}
			return true;
		}
	}
	
	/* 
	 * Since we at the current state aren't planning on using any hashtables
	 * this code was added for the cause of good practice
	 */
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 44; // any arbitrary constant will do
	}
	
}
