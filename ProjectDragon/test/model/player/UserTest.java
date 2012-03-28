package model.player;

/**
 * A test of the class "User"
 * @author mattiashenriksson
 */

import static org.junit.Assert.*;

import model.game.Card;
import model.player.hand.TexasHoldemHand;

import org.junit.Test;

public class UserTest {

	@Test
	public void testSetActive() {
		Player p = new Player(new TexasHoldemHand(true), "Mattias", 
				new Balance());
		User u = new User(p);
		u.setActive(true);
		assertTrue(u.isActive());
		u.setActive(false);
		assertTrue(!u.isActive());
	}
	
	@Test
	public void testAddCard() {
		TexasHoldemHand thh = new TexasHoldemHand(true);
		Player p = new Player(thh, "Mattias", new Balance());
		User u = new User(p);
		Card c = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		u.addCard(c);
		assertTrue(thh.getCards().get(0).equals(c));	
	}

}
