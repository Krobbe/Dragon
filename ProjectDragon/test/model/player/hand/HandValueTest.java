package model.player.hand;

import static org.junit.Assert.*;
import org.junit.Test;
import model.game.Card;

public class HandValueTest {
	FullTHHand hand = new FullTHHand();
	
	
	@Test
	public void testFindFlush() {
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.ACE));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.KING));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN));
		hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.JACK));
		hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));

		HandEvaluator h = new HandEvaluator(hand);
		assertTrue(h.getType().equals(HandValueType.FLUSH));
	}

}
