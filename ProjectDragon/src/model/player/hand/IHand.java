package model.player.hand;

import java.io.Serializable;
import java.util.List;

import common.model.card.ICard;


/**
 * An interface for a hand in a poker game. 
 * @author lisastenberg
 * @author robinandersson
 *
 */
public interface IHand {

	/**
	 * Removes all cards from a hand.
	 * @author lisastenberg
	 */
	public void discard();
	
	/**
	 * @author lisastenberg
	 * @author robinandersson
	 * @return a List of the hand's cards.
	 */
	public List<ICard> getCards();
	
	/**
	 * Add a new card to the hand.
	 * @author lisastenberg
	 * @author robinandersson
	 * @param card The card you want to add.
	 */
	public void addCard(ICard card);
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public String toString();
}
