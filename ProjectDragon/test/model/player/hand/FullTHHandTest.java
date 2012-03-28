package model.player.hand;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import model.game.Card;

import org.junit.Test;
/**
 * A test class for FullTHHand (Full Texas Hold'Em Hand)
 * @author lisastenberg
 *
 */

public class FullTHHandTest {
	FullTHHand hand = new FullTHHand();
	
	@Test
	public void testAddCard() {
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		Card c = hand.getCards().get(0);
		assertTrue(c.getRank() == Card.Rank.ACE && c.getSuit() == Card.Suit.CLUBS);
	}
	
	@Test
	public void testAddCards() {
		List<Card> list = new LinkedList<Card>();
		list.add(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
		list.add(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
		list.add(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
		System.out.println(list.toString());
		hand.addCards(list);
		System.out.println(hand.getCards().toString());
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		System.out.println(hand.getCards().toString());

		Card c = hand.getCards().get(0);
		assertTrue(c.getRank() == Card.Rank.ACE && c.getSuit() == Card.Suit.CLUBS);
	}

}
