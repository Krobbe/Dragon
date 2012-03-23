package game;

import static org.junit.Assert.*;

import model.game.Card;

import org.junit.Test;
/**
 * A test for Card.
 * @author lisastenberg
 *
 */
public class CardTest {
	Card c = new Card(Card.Suite.SPADES, 2);
	Card d = new Card(Card.Suite.SPADES, 2);
	Card e = new Card(Card.Suite.SPADES, 2);
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadIndex() {
		Card card = new Card(Card.Suite.SPADES, 14);
	}
	
	@Test
	public void testGetValue() {
		c.getValue();
		assertTrue(c.getValue() == 2);
	}
	
	@Test
	public void testSuite() {
		assertTrue(c.getSuite().equals(Card.Suite.SPADES));
	}	

	@Test
	public void testEquals() {
		assertTrue(c.equals(c));
		assertTrue(c.equals(d));
		if(d.equals(e)) {
			assertTrue(c.equals(e));
		}
	}
	
	@Test
	public void testToString() {
		assertTrue(c.toString().equals("2 of SPADES"));
	}
}
