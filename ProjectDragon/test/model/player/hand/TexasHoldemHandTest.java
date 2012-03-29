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
 * Test for TexasHoldemHand
 * 
 * @author lisastenberg
 *
 */
public class TexasHoldemHandTest {

	//TODO Write tests for TexasHoldemTest
	/**
	 * Test both isVisible() and setVisible(boolean)
	 */
	@Test
	public void testVisible() {
		iHand hand = new TexasHoldemHand(true);
		assertTrue(hand.isVisible());
		
		hand.setVisible(false);
		assertTrue(!hand.isVisible());
	}
	
	@Test
	public void testGetCards() {
		iHand hand = new TexasHoldemHand(true);
		assertTrue(hand.getCards().equals(new LinkedList<Card>()));
	}
	
	@Test
	public void testAddCard() {
		iHand hand = new TexasHoldemHand(true);
		Card c = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		hand.addCard(c);
		assertTrue(hand.getCards().get(0).equals(c));
	}
	
	@Test
	public void testEquals() {
		iHand hand1 = new TexasHoldemHand(true);
		iHand hand2 = new TexasHoldemHand(true);
		Card c = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		Card d = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);
		hand1.addCard(c);
		hand1.addCard(d);
		hand2.addCard(d);
		hand2.addCard(c);
		// Reflexivity
		assertTrue(hand1.equals(hand1));
		
		// Symmetry
		assertTrue(hand1.equals(hand2));
		assertTrue(hand2.equals(hand1));
		
		iHand hand3 = new TexasHoldemHand(true);
		hand3.addCard(c);
		hand3.addCard(d);
		// Transitivity
		if(hand1.equals(hand2) && hand2.equals(hand3)) {
			assertTrue(hand1.equals(hand3));
		}
	}
	
	@Test
	public void testToString() {
		iHand hand1 = new TexasHoldemHand(true);
		Card c = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		String s = hand1.toString();
		String expected = "Texas Hold'em hand: \nACE of CLUBS\n";
	}
}
