package model.game;

/**
 * An interface that represents a Dealer. 
 * 
 * A dealer have at least one deck, but can also have several decks. 
 * @author lisastenberg
 *
 */
public interface iDealer {

	/**
	 * @return the top card of the Deck(s).
	 */
	public Card popCard();
}
