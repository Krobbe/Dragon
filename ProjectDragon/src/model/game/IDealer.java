package model.game;


import model.card.ICard;


/**
 * An interface that represents a Dealer. 
 * 
 * A dealer have at least one deck, but can also have several decks. 
 * @author lisastenberg
 *
 */
public interface IDealer {

	/**
	 * @return the top card of the Deck(s).
	 */
	public ICard popCard();
	
	/**
	 * Creates a new full and shuffled deck for the dealer.
	 * @author mattiashenriksson
	 */
	public void newDeck();
}
