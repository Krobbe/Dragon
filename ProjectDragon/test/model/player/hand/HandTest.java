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
 * Test for the Hand class.
 * @author robinandersson
 * @author lisastenberg
 */
public class HandTest {


	
	/**
	 * Tests so that the class returns it's list with cards correctly
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testGetCards() {
		IHand hand = new Hand();
		assertTrue(hand.getCards().equals(new LinkedList<Card>()));
	}
	
	/**
	 * Tests the hands functionality to add cards to it's hand
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testAddCard() {
		IHand hand = new Hand();
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
	
	@Test
	public void testDiscard() {
		IHand hand = new Hand();
		Card card1 = new Card(Suit.CLUBS, Rank.ACE);
		hand.addCard(card1);
		hand.discard();
		assertTrue(hand.getCards().equals(new LinkedList<Card>()));
	}
	
	/**
	 * Tests the toString() method
	 * @author lisastenberg
	 */
	@Test
	public void testToString() {
		IHand hand1 = new Hand();
		hand1.addCard(new Card(Suit.CLUBS, Rank.ACE));
		String s = hand1.toString();
		String expected = "Hand: [ACE of CLUBS]";
		assertTrue(s.equals(expected));
	}
	
	/**
	 * Tests the hands equals method
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testEquals() {
		IHand hand1 = new Hand();
		IHand hand2 = new Hand(); 
		
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
		
		IHand hand3 = new Hand();
		hand3.addCard(card1);
		hand3.addCard(card2);
		
		// Transitivity
		if(hand1.equals(hand2) && hand2.equals(hand3)) {
			assertTrue(hand1.equals(hand3));
		}
		
		// Some tests where the hands are not equal
		
		IHand hand4 = new Hand();
		
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
		IHand hand = new Hand();
		assertTrue(hand.hashCode() == 43);
	}
}
