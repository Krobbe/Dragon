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
	Card c = new Card(Card.Suite.SPADES, Card.Rank.TWO);
	Card d = new Card(Card.Suite.SPADES, Card.Rank.TWO);
	Card e = new Card(Card.Suite.SPADES, Card.Rank.TWO);
	
	@Test
	public void testGetValue() {
		c.getRank();
		assertTrue(c.getRank().equals(Card.Rank.TWO));
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
		assertTrue(c.toString().equals("TWO of SPADES"));
	}
}
