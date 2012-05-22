package common.model.card;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * A test for Card.
 * @author lisastenberg
 *
 */
public class CardTest {
	Card c = new Card(Suit.SPADES, Rank.TWO);
	Card d = new Card(Suit.SPADES, Rank.TWO);
	Card e = new Card(Suit.SPADES, Rank.TWO);

	
	@Test
	public void testGetValue() {
		c.getRank();
		assertTrue(c.getRank().equals(Rank.TWO));
	}
	
	@Test
	public void testGetSuite() {
		assertTrue(c.getSuit().equals(Suit.SPADES));
	}	

	@Test
	public void testEquals() {
		assertFalse(c.equals(null));
		//Reflexivity
		assertTrue(c.equals(c));
		//Symmetry
		assertTrue(c.equals(d));
		assertTrue(d.equals(c));
		//Transitivity
		if(d.equals(e)) {
			assertTrue(c.equals(e));
		}
	}
	
	@Test
	public void testToString() {
		assertTrue(c.toString().equals("TWO of SPADES"));
	}
	
	@Test
	public void testCompareTo() {
		assertTrue(c.compareTo(d) == 0);
	}
}
