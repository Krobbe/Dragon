/**
 * 
 */
package model.player.hand;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import model.card.*;

/**
 * @author robinandersson
 * A class that the hand classes for different games can delegate similar
 * functionality to
 */
public class Hand implements IHand, Serializable {

	private List<ICard> cards;
	
	
	public Hand(){
		cards = new LinkedList<ICard>();
	}
	
	@Override
	public void discard() {
		cards.clear();
	}

	@Override
	public List<ICard> getCards() {
		return cards;
	}

	@Override
	public void addCard(ICard card) {
		cards.add(card);
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Hand: ");
		stringBuilder.append(cards.toString());
		return stringBuilder.toString();
	}	
	
	/**
	 * Determines if this hand is equal to another hand.
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
		 * Compares the size of this and the foreign hand so that it is not
		 * needed later in the tests
		 */
		else if (this.getCards().size() != ((Hand) o).getCards().size()){
			return false;
		}
		
		/*
		 * If the classes are similar up to this point the cards in each hand
		 * is compared
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
			
			List<ICard> otherCards = ((Hand) o).getCards();
			
			//Every card in this hand is compared to the other Hand's cards
			for(ICard card : this.getCards()){
				
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
				
				/* If no match is found for one of this hand's cards then the
				 * two hands are not equal
				 */
				if(!sameCards){
					return false;
				}
				
			} // for(Card card : this.getCards())
			
			/* 
			 * If every card in this hand found a match then the hand's are
			 * deemed equal
			 */
			return true;
		}
		
	} // equals
	
	/* 
	 * Since we at the current state aren't planning on using any hashtables
	 * this code was added for the cause of good practice
	 */
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 43; // any arbitrary constant will do
	}
	
}
