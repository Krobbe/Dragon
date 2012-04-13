package model.game;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents a dealer. 
 * @author lisastenberg 
 * @author forssenm
 *
 */

public class Dealer implements iDealer {
	private Deck d;
	
	public Dealer() {
		d = new Deck();
	}
	
	/**
	 * 
	 * @return the top card of the deck.
	 */
	public Card popCard() {
		return d.popCard();
	}
	
	/**
	 * 
	 * @return the three top cards of the deck.
	 */
	public List<Card> getFlop() {
		Card tmp1 = d.popCard();
		Card tmp2 = d.popCard();
		Card tmp3 = d.popCard();
		
		List<Card> flop = new LinkedList<Card>();
		flop.add(tmp1);
		flop.add(tmp2);
		flop.add(tmp3);
		
		return flop;
	}
	
	/**
	 * @author mattiashenriksson
	 * @return the top card of the deck
	 */
	public Card getRiver() {
		return d.popCard();
	}
}
