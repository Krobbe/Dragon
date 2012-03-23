package test;

import static org.junit.Assert.*;

import model.game.Card;

import org.junit.Test;

public class CardTest {
	Card c = new Card(Card.Suite.SPADES, 2);
	Card d = new Card(Card.Suite.SPADES, 2);
	
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
		assertTrue(c.equals(d));
	}
	
	@Test
	public void testToString() {
		assertTrue(c.toString().equals("2 of SPADES"));
	}
}
