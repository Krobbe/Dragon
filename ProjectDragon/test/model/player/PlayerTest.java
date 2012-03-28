package model.player;

import static org.junit.Assert.*;

import model.game.Card;
import model.player.hand.TexasHoldemHand;

import org.junit.Test;

/**
 * A test of the class "Player".
 * @author mattiashenriksson
 *
 */
public class PlayerTest {

	@Test
	public void testSetActive() {
		Player p = new Player(new TexasHoldemHand(true), "Mattias", 
				new Balance());
		p.setActive(true);
		assertTrue(p.isActive());
		p.setActive(false);
		assertTrue(!p.isActive());
	}
	
	@Test
	public void testAddCard() {
		TexasHoldemHand thh = new TexasHoldemHand(true);
		Player p = new Player(thh, "Mattias", new Balance());
		Card c = new Card(Card.Suit.CLUBS, Card.Rank.ACE);
		p.addCard(c);
		assertTrue(thh.getCards().get(0).equals(c));
		
	}

}