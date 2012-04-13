package model.game;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * A test for CardComparator.
 * 
 * @author lisastenberg
 *
 */
public class CardComparatorTest {

	@Test
	public void testCompare() {
		Card c1 = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);
		Card c2 = new Card(Card.Suit.CLUBS, Card.Rank.FIVE);
		CardComparator cc = new CardComparator();
		int tmp = cc.compare(c1,c2);
		assertTrue(tmp < 0);
	}

}
