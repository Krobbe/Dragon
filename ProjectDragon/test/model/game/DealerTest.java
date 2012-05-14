package model.game;

import static org.junit.Assert.*;

import java.util.List;

import model.card.Card;

import org.junit.Test;
/**
 * A test for Dealer.
 * @author lisastenberg
 *
 */
public class DealerTest {

	@Test
	public void testGetFlop() {
		TexasHoldemDealer d = new TexasHoldemDealer();
		List<Card> list = d.getFlop();
		assertTrue(list.size() == 3);
	}
	
	@Test
	public void testGetRiver() {
		TexasHoldemDealer d = new TexasHoldemDealer();
		Card c = d.getRiver();
		assertTrue(c.getClass() == Card.class);
	}
	
	@Test
	public void testToString() {
		TexasHoldemDealer d = new TexasHoldemDealer();
		for(int i = 0; i < 52; i++) {
			d.popCard();
		}
		assertTrue(d.toString().equals("Dealer with deck: \n"));
	}
	
	@Test
	public void testEquals() {
		TexasHoldemDealer d = new TexasHoldemDealer();
		
		//Only possible to test reflexivity here. Since the decks are shuffled
		//randomly it would be unnecessary work for us to find two decks that 
		//are similar.
		assertTrue(d.equals(d));
	}
}
