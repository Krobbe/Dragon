package common.model.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.model.card.ICard;
/**
 * A test for Dealer.
 * 
 * @author lisastenberg
 *
 */
public class TexasHoldemDealerTest {
	@Test
	public void testPopCard() {
		TexasHoldemDealer d = new TexasHoldemDealer();
		assertTrue(d.popCard() instanceof ICard);
		//Not necessary to test more since deck is already tested in another 
		//class.
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
