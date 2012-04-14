package model.game;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
/**
 * A test for Dealer.
 * @author lisastenberg
 *
 */
public class DealerTest {

	@Test
	public void testGetFlop() {
		Dealer d = new Dealer();
		List<Card> list = d.getFlop();
		assertTrue(list.size() == 3);
	}
	
	@Test
	public void testGetRiver() {
		Dealer d = new Dealer();
		Card c = d.getRiver();
		assertTrue(c.getClass() == Card.class);
	}
	
	@Test
	public void testToString() {
		Dealer d = new Dealer();
		for(int i = 0; i < 52; i++) {
			d.popCard();
		}
		assertTrue(d.toString().equals("Dealer with deck: \n"));
	}
	
	@Test
	public void testEquals() {
		Dealer d = new Dealer();
		
		//Only possible to test reflexivity here. Since the decks are shuffled
		//randomly it would be unnecessary work for us to find two decks that 
		//are similar.
		assertTrue(d.equals(d));
	}
}