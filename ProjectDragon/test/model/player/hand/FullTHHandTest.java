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
	public void testGetCards() {
		FullTHHand hand = new FullTHHand();
		assertTrue(hand.getCards().equals(new LinkedList<Card>()));
	}
	
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

	@Test
	public void testDiscard() {
		FullTHHand fhand = new FullTHHand();
		fhand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
		fhand.discard();
		assertTrue(fhand.getCards().equals(new LinkedList<Card>()));
	}
	
	@Test
	public void testIsVisible() {
		FullTHHand fhand = new FullTHHand();
		assertTrue(fhand.isVisible());
	}
	
	@Test
	public void testSetVisible() {
		FullTHHand fhand = new FullTHHand();
		fhand.setVisible(false);
		assertTrue(!fhand.isVisible());
		fhand.setVisible(true);
		assertTrue(fhand.isVisible());
	}
	
	@Test
	public void testEquals() {
		FullTHHand hand1 = new FullTHHand();
		hand1.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		// Reflexivity
		assertTrue(hand1.equals(hand1));
		
		FullTHHand hand2 = new FullTHHand();
		hand2.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		// Symmetry
		assertTrue(hand1.equals(hand2));
		assertTrue(hand2.equals(hand1));
		
		FullTHHand hand3 = new FullTHHand();
		hand3.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		// Transitivity
		if(hand1.equals(hand2) && hand2.equals(hand3)) {
			assertTrue(hand1.equals(hand3));
		}
	}
	
	@Test
	public void testToString() {
		FullTHHand hand1 = new FullTHHand();
		hand1.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		String s = hand1.toString();
		String expected = "Full hand: \nACE of CLUBS\n";
		assertTrue(s.equals(expected));
	}
}
