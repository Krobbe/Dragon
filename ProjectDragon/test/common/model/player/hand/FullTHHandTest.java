package common.model.player.hand;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import common.model.card.Card;
import common.model.card.ICard;
import common.model.card.Rank;
import common.model.card.Suit;
import common.model.player.hand.FullTHHand;
import common.model.player.hand.IHand;
import common.model.player.hand.TexasHoldemHand;
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
		hand.addCard(new Card(Suit.CLUBS, Rank.ACE));
		ICard c = hand.getCards().get(0);
		assertTrue(c.getRank() == Rank.ACE && c.getSuit() == Suit.CLUBS);
	}
	
	@Test
	public void testAddCardsFromList() {
		FullTHHand hand = new FullTHHand();
		List<ICard> list = new LinkedList<ICard>();
		list.add(new Card(Suit.CLUBS, Rank.EIGHT));
		list.add(new Card(Suit.CLUBS, Rank.TEN));
		list.add(new Card(Suit.CLUBS, Rank.NINE));
		hand.addCards(list);

		ICard c = hand.getCards().get(0);
		assertTrue(c.getRank() == Rank.TEN && c.getSuit() == Suit.CLUBS);
	}
	
	@Test
	public void testAddCardsFromHand() {
		FullTHHand fhand = new FullTHHand();
		IHand hand = new TexasHoldemHand();
		hand.addCard(new Card(Suit.CLUBS, Rank.EIGHT));
		
		fhand.addCards(hand);
		ICard c = fhand.getCards().get(0);
		assertTrue(c.getRank() == Rank.EIGHT && c.getSuit() == Suit.CLUBS);
	}

	@Test
	public void testDiscard() {
		FullTHHand fhand = new FullTHHand();
		fhand.addCard(new Card(Suit.CLUBS, Rank.EIGHT));
		fhand.discard();
		assertTrue(fhand.getCards().equals(new LinkedList<Card>()));
	}
	
	@Test
	public void testEquals() {
		FullTHHand hand1 = new FullTHHand();
		hand1.addCard(new Card(Suit.CLUBS, Rank.ACE));
		// Reflexivity
		assertTrue(hand1.equals(hand1));
		
		FullTHHand hand2 = new FullTHHand();
		hand2.addCard(new Card(Suit.CLUBS, Rank.ACE));
		// Symmetry
		assertTrue(hand1.equals(hand2));
		assertTrue(hand2.equals(hand1));
		
		FullTHHand hand3 = new FullTHHand();
		hand3.addCard(new Card(Suit.CLUBS, Rank.ACE));
		// Transitivity
		if(hand1.equals(hand2) && hand2.equals(hand3)) {
			assertTrue(hand1.equals(hand3));
		}
	}
	
	@Test
	public void testToString() {
		FullTHHand hand1 = new FullTHHand();
		hand1.addCard(new Card(Suit.CLUBS, Rank.ACE));
		String s = hand1.toString();
		String expected = "Full hand: [ACE of CLUBS]";
		assertTrue(s.equals(expected));
	}
}
