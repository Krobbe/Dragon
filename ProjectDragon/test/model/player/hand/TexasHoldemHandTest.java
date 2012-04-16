/**
 * 
 */
package model.player.hand;

import static org.junit.Assert.*;
import java.util.LinkedList;
import model.game.Card;
import model.player.hand.TexasHoldemHand;
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
	 * Tests that the visibility is set to correct values by the constructors
	 * and methods
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testVisible() {
		iHand hand = new TexasHoldemHand();
		assertTrue(!hand.isVisible());
		assertTrue(new TexasHoldemHand(true).isVisible());
		assertTrue(!(new TexasHoldemHand(false).isVisible()));
		
		/*
		 *  The hand should be false by default (constructor with no parameter)
		 *  which is why the first parameter in setVisible is set to true to
		 *  thoroughly test the method
		 */
		hand.setVisible(true);
		assertTrue(hand.isVisible());
		
		hand.setVisible(false);
		assertTrue(!hand.isVisible());
	}
	
	/**
	 * Tests so that the class returns it's list with cards correctly
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testGetCards() {
		iHand hand = new TexasHoldemHand(true);
		assertTrue(hand.getCards().equals(new LinkedList<Card>()));
	}
	
	/**
	 * Tests the hand's functionality to add cards to it's hand
	 * @author lisastenberg
	 * @author robinandersson
	 */
	@Test
	public void testAddCard() {
		iHand hand = new TexasHoldemHand();
		Card card1 = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		hand.addCard(card1);
		
		assertTrue(hand.getCards().size() == 1);
		assertTrue(hand.getCards().get(0).equals(card1));
		
		Card card2 = new Card(Card.Suit.CLUBS, Card.Rank.KING);
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
		iHand hand1 = new TexasHoldemHand();
		iHand hand2 = new TexasHoldemHand(true); //Visibility should not matter
		
		Card card1 = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		Card card2 = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);
		
		hand1.addCard(card1);
		hand1.addCard(card2);
		hand2.addCard(card2);
		hand2.addCard(card1);
		
		// Reflexivity
		assertTrue(hand1.equals(hand1));
		
		// Symmetry
		assertTrue(hand1.equals(hand2));
		assertTrue(hand2.equals(hand1));
		
		iHand hand3 = new TexasHoldemHand(false);
		hand3.addCard(card1);
		hand3.addCard(card2);
		
		// Transitivity
		if(hand1.equals(hand2) && hand2.equals(hand3)) {
			assertTrue(hand1.equals(hand3));
		}
		
		// Some tests where the hands are not equal
		
		iHand hand4 = new TexasHoldemHand();
		
		assertTrue(!hand1.equals(hand4));
		assertTrue(!hand4.equals(hand1));
		
		hand4.addCard(card1);
		
		assertTrue(!hand1.equals(hand4)); 	// Tests the case where the hands
											// have one equal card 
		
		Card card3 = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
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
		iHand hand = new TexasHoldemHand();
		assertTrue(hand.hashCode() == 44);
	}
	
	@Test
	public void testToString() {
		iHand hand1 = new TexasHoldemHand(true);
		Card c = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		hand1.addCard(c);
		String s = hand1.toString();
		String expected = "Texas Hold'em Hand: [ACE of CLUBS]";
		assertTrue(s.equals(expected));
	}
}
