package model.player.hand;

import static org.junit.Assert.*;
import org.junit.Test;
import model.game.Card;
/**
 * A test for HandValue
 * @author lisastenberg
 *
 */
public class HandValueTest {
	FullTHHand hand = new FullTHHand();
	HandValue h;

	@Test
	public void testGetType() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
		
		h = new HandValue(hand);
		assertTrue(h.getType().equals(HandValueType.FLUSH));
	}
	
	@Test
	public void testGetDescription() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
		
		h = new HandValue(hand);
		assertTrue(h.getDescription().equals("Flush"));
	}
	
	@Test
	public void testGetValue() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
		
		h = new HandValue(hand);
		assertTrue(h.getValue() == 2225178);
	}
	
	@Test
	public void testEquals() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
		
		h = new HandValue(hand);
		//Reflexivity
		assertTrue(h.equals(h));
		//Symmetry
		HandValue h2 = new HandValue(hand);
		assertTrue(h.equals(h2));
		assertTrue(h2.equals(h));
		//Transitivity
		HandValue h3 = new HandValue(hand);
		if(h2.equals(h3)) {
			assertTrue(h.equals(h3));
		}
	}
	
	@Test
	public void testToString() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
		
		h = new HandValue(hand);
		String tmp = h.toString();
		String expected = "Flush (2225178)";
		assertTrue(tmp.equals(expected));
	}
	
	@Test
	public void testCompareTo() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.NINE));
		
		h = new HandValue(hand);
		HandValue h2 = new HandValue(hand);
		assertTrue(h.compareTo(h2) == 0);
		
		FullTHHand hand2 = new FullTHHand();
		hand2.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand2.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand2.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand2.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand2.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.TWO));
		
		HandValue h3 = new HandValue(hand2);
		assertTrue(h.compareTo(h3) < 0);
	}
}
