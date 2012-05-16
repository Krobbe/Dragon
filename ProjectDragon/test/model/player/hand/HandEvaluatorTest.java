package model.player.hand;

import static org.junit.Assert.assertTrue;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;

import org.junit.Test;
/**
 * A test for HandEvaluator.
 * 
 * @author lisastenberg
 *
 */
public class HandEvaluatorTest {
	FullTHHand hand = new FullTHHand();
	
	
	@Test
	public void testIsFlush() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.NINE));

		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.FLUSH));
	}
	
	@Test
	public void testIsStraight() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		hand.addCard(new Card(Suit.HEARTS, Rank.TEN));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.STRAIGHT));
	}
	
	@Test
	public void testIsOnePair() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.ONE_PAIR));	
	}

	@Test
	public void testIsTwoPairs() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.HEARTS, Rank.KING));
		hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.TWO_PAIRS));	
	}
	
	@Test
	public void testIsThreeOfAKind() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.CLUBS, Rank.KING));
		hand.addCard(new Card(Suit.HEARTS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.THREE_OF_A_KIND));	
	}
	
	@Test
	public void testIsFullHouse() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS, Rank.JACK));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.FULL_HOUSE));	
	}
	
	@Test
	public void testIsFourOfAKind() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.SPADES, Rank.ACE));
		hand.addCard(new Card(Suit.HEARTS, Rank.ACE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.FOUR_OF_A_KIND));	
	}
	
	/**
	 * A test for the method isStraightFlush()
	 */
	@Test
	public void testIsRoyalFlush() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.TEN));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.ROYAL_FLUSH));
	}
	
	@Test
	public void testIsStraightFlush() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.TEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.NINE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.STRAIGHT_FLUSH));
	}
	
	@Test
	public void testCalculateHighCard() {
		hand.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.KING));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.QUEEN));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
		hand.addCard(new Card(Suit.CLUBS, Rank.NINE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.HIGH_CARD));
	}
}
