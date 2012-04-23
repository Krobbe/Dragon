package model.game;

import static org.junit.Assert.*;

import model.card.Card;
import model.card.Deck;

import org.junit.Test;

/**
 * A test for Deck.
 * @author lisastenberg
 *
 */
public class DeckTest {

	@Test
	public void testPopCard() {
		Deck d = new Deck();
		Card c1, c2;
		boolean check = true;
		
		for(int i = 0; i < 52; i = i+2) {
			c1 = d.popCard();
			c2 = d.popCard();
			check = !c1.equals(c2);
		}
		
		assertTrue(check);
	}
	
	@Test
	public void testShuffle() {
		//There is a minimal chance that this test will fail. That is if the
		//deck is shuffled and has the exact same order (of cards) as before.
		//But as said, it's minimal.
		Deck d = new Deck();
		String s1 = d.toString();
		d.shuffle();
		String s2 = d.toString();
		assertTrue(!s1.equals(s2));
	}
	
	@Test
	public void testToString() {
		Deck d = new Deck();
		for(int i = 0; i < 51; i++) {
			d.popCard();
		}
		String s = d.toString();
		Card c = d.popCard();
		String tmp = c.toString() + "\n";
		assertTrue(tmp.equals(s));
	}
	
	@Test
	public void testEquals() {
		Deck d = new Deck();
		
		//Only possible to test reflexivity here. Since the decks are shuffled
		//randomly it would be unnecessary work for us to find two decks that 
		//are similar.
		assertTrue(d.equals(d));
	}

}
