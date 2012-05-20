package common.model.card;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import common.model.card.Card;
import common.model.card.CardComparator;
import common.model.card.Rank;
import common.model.card.Suit;
/**
 * A test for CardComparator.
 * 
 * @author lisastenberg
 *
 */
public class CardComparatorTest {

	@Test
	public void testCompare() {
		Card c1 = new Card(Suit.CLUBS, Rank.EIGHT);
		Card c2 = new Card(Suit.CLUBS, Rank.FIVE);
		CardComparator cc = new CardComparator();
		int tmp = cc.compare(c1,c2);
		assertTrue(tmp < 0);
	}

}
