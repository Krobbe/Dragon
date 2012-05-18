package common.model.card;

/**
 * A interface that represents a Deck.
 * 
 * @author mattiashenriksson
 *
 */

public interface IDeck {
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle();
	
	/**
	 * 
	 * @return the card at the top of a deck
	 */
	public ICard popCard();
	
}
