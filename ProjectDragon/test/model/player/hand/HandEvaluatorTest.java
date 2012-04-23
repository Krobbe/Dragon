package model.player.hand;

import static org.junit.Assert.*;

import model.card.Card;

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
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));

		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.FLUSH));
	}
	
	@Test
	public void testIsStraight() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.STRAIGHT));
	}
	
	@Test
	public void testIsOnePair() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.ONE_PAIR));	
	}

	@Test
	public void testIsTwoPairs() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.TWO_PAIRS));	
	}
	
	@Test
	public void testIsThreeOfAKind() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.THREE_OF_A_KIND));	
	}
	
	@Test
	public void testIsFullHouse() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.FULL_HOUSE));	
	}
	
	@Test
	public void testIsFourOfAKind() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.SPADES, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.FOUR_OF_A_KIND));	
	}
	
	/**
	 * A test for the method isStraightFlush()
	 */
	@Test
	public void testIsRoyalFlush() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.TEN));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.ROYAL_FLUSH));
	}
	
	@Test
	public void testIsStraightFlush() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.TEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.STRAIGHT_FLUSH));
	}
	
	@Test
	public void testCalculateHighCard() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
		
		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.HIGH_CARD));
	}
}
