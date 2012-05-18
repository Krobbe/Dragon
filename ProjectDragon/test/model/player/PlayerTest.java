package model.player;

import static org.junit.Assert.*;


import org.junit.Test;

import common.model.card.Card;
import common.model.player.Balance;
import common.model.player.Player;
import common.model.player.hand.TexasHoldemHand;

/**
 * A test of the class "Player".
 * @author mattiashenriksson
 * @author lisastenberg
 *
 */
public class PlayerTest {

	@Test
	public void testIsActive() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(!p.isActive());
	}
	
	@Test
	public void testIsStillInGame() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(p.isStillInGame());
	}
	
	@Test
	public void testGetName() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(p.getName().equals("Mattias"));
	}
	
	@Test
	public void testSetActive() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		p.setActive(true);
		assertTrue(p.isActive());
		p.setActive(false);
		assertTrue(!p.isActive());
	}
	
	@Test
	public void testGetHand() {
		TexasHoldemHand thh = new TexasHoldemHand();
		Player p = new Player(thh, "Mattias", new Balance());
		assertTrue(p.getHand().equals(thh));
	}
	
	@Test
	public void testSetStillInGame() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(p.isStillInGame());
		p.setStillInGame(false);
		assertTrue(!p.isStillInGame());
	}
	
	@Test
	public void testGetBalance() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(p.getBalance().getValue() == 0);
	}
	
	@Test
	public void testEquals() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(p.equals(p));
		Player p2 = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(!p.equals(p2));
	}
	
	@Test
	public void testCompareTo() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(p.compareTo(p) == 0);
		Player p2 = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		assertTrue(p.compareTo(p2) == 0);
	}
	
	@Test
	public void testToString() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		String s = p.toString();
		String expected = "Name: Mattias , Balance: 0 , Active: false , " +
				"Hand: Texas Hold'em hand: []";
		assertTrue(s.equals(expected));
	}
}
