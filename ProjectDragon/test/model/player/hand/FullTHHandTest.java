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
	
	@Test
	public void testAddCard() {
		FullTHHand hand = new FullTHHand();
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		Card c = hand.getCards().get(0);
		assertTrue(c.getRank() == Card.Rank.ACE && c.getSuit() == Card.Suit.CLUBS);
	}
	
	@Test
	public void testAddCardsFromList() {
		FullTHHand hand = new FullTHHand();
		List<Card> list = new LinkedList<Card>();
		list.add(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
		list.add(new Card(Card.Suit.CLUBS, Card.Rank.TEN));
		list.add(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
		hand.addCards(list);

		Card c = hand.getCards().get(0);
		assertTrue(c.getRank() == Card.Rank.TEN && c.getSuit() == Card.Suit.CLUBS);
	}
	
	@Test
	public void testAddCardsFromHand() {
		FullTHHand fhand = new FullTHHand();
		iHand hand = new TexasHoldemHand(true);
		hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
		
		fhand.addCards(hand);
		Card c = fhand.getCards().get(0);
		assertTrue(c.getRank() == Card.Rank.EIGHT && c.getSuit() == Card.Suit.CLUBS);
	}

}
