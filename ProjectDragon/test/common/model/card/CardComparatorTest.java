package common.model.card;

import static org.junit.Assert.assertTrue;

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
		Card c1 = new Card(Suit.CLUBS, Rank.EIGHT);
		Card c2 = new Card(Suit.CLUBS, Rank.FIVE);
		CardComparator cc = new CardComparator();
		int tmp = cc.compare(c1,c2);
		assertTrue(tmp < 0);
	}

}
