/**
 * 
 */
package model.player.hand;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import model.card.Card;
import model.card.Rank;
import model.card.Suit;

import org.junit.Test;

/**
 * Test for TexasHoldemHand.
 * 
 * This class does not need to be properly tested if the Hand class is tested
 * correctly. This because TexasHoldemHand doesn't have any methods of its own.
 * 
 * @author lisastenberg
 * @author robinandersson
 *
 */
public class TexasHoldemHandTest {

	
	/**
	 * Tests so that the class returns it's list with cards correctly
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testGetCards() {
		IHand hand = new TexasHoldemHand();
		assertTrue(hand.getCards().equals(new LinkedList<Card>()));
	}
	
	/**
	 * Tests the hand's functionality to add cards to it's hand
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testAddCard() {
		IHand hand = new TexasHoldemHand();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		hand.addCard(card1);
		
		assertTrue(hand.getCards().size() == 1);
		assertTrue(hand.getCards().get(0).equals(card1));
		
		Card card2 = new Card(Suit.CLUBS, Rank.KING);
		hand.addCard(card2);
		
		assertTrue(hand.getCards().size() == 2);
		assertTrue(hand.getCards().get(0).equals(card1));
		assertTrue(hand.getCards().get(1).equals(card2));
	}
	
	/**
	 * Tests the hand's equals method
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testEquals() {
		IHand hand1 = new TexasHoldemHand();
		IHand hand2 = new TexasHoldemHand();
		
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		Card card2 = new Card(Suit.CLUBS, Rank.EIGHT);
		
		hand1.addCard(card1);
		hand1.addCard(card2);
		hand2.addCard(card2);
		hand2.addCard(card1);
		
		// Reflexivity
		assertTrue(hand1.equals(hand1));
		
		// Symmetry
		assertTrue(hand1.equals(hand2));
		assertTrue(hand2.equals(hand1));
		
		IHand hand3 = new TexasHoldemHand();
		hand3.addCard(card1);
		hand3.addCard(card2);
		
		// Transitivity
		if(hand1.equals(hand2) && hand2.equals(hand3)) {
			assertTrue(hand1.equals(hand3));
		}
		
		// Some tests where the hands are not equal
		
		IHand hand4 = new TexasHoldemHand();
		
		assertTrue(!hand1.equals(hand4));
		assertTrue(!hand4.equals(hand1));
		
		hand4.addCard(card1);
		
		assertTrue(!hand1.equals(hand4)); 	// Tests the case where the hands
											// have one equal card 
		
		Card card3 = new Card(Suit.CLUBS, Rank.ACE);
		hand4.addCard(card3);
		
		assertTrue(!hand1.equals(hand4));	// Tests the case where the hands
											// have one equal card and are the
											// same size
	}
	
	/**
	 * Tests the hashCode method for good practice, the hashCode method is not
	 * fully implemented
	 */
	@Test
	public void hashTest(){
		IHand hand = new TexasHoldemHand();
		assertTrue(hand.hashCode() == 44);
	}
	
	@Test
	public void testToString() {
		IHand hand1 = new TexasHoldemHand();
		Card c = new Card(Suit.CLUBS, Rank.ACE);
		hand1.addCard(c);
		String s = hand1.toString();
		String expected = "Texas Hold'em Hand: [ACE of CLUBS]";
		assertTrue(s.equals(expected));
	}
}
