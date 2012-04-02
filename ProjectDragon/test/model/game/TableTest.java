package model.game;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.game.Card.Suit;
import model.player.User;
import model.player.iPlayer;

import org.junit.Test;

import utilities.PlayersFullException;
import utilities.TableCardsFullException;

/**
 * A test of the Table-class
 * @author mattiashenriksson
 *
 */

public class TableTest {

	@Test
	public void testAddPlayer() throws PlayersFullException {
		Table t = new Table();
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		t.addPlayer(p1);
		t.addPlayer(p2);
		List<iPlayer> players = t.getPlayers();
		assertTrue(players.size() == 2);
	}
	
	@Test(expected=PlayersFullException.class) 
	public void testTableFull() throws PlayersFullException {
		Table t = new Table();
		iPlayer p1 = new User(), p2 = new User(), p3 = new User(), p4 = new User(), p5 = new User(), 
				p6 = new User(), p7 = new User(), p8 = new User(), p9 = new User(), p10 = new User(), 
				p11 = new User();
		t.addPlayer(p1); t.addPlayer(p2); t.addPlayer(p3); t.addPlayer(p4); t.addPlayer(p5); t.addPlayer(p6);
		t.addPlayer(p7); t.addPlayer(p8); t.addPlayer(p9); t.addPlayer(p10); t.addPlayer(p11);
	}

	@Test
	public void testNextPlayer() throws PlayersFullException {
		Table t = new Table();
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		t.addPlayer(p1);
		t.addPlayer(p2);
		t.nextPlayer();
		assertTrue(t.getCurrentPlayer() == p2);
		t.nextPlayer();
		assertTrue(t.getCurrentPlayer() == p1);	
	}
	
	@Test
	public void testGetCurrentPlayer() throws PlayersFullException {
		Table t = new Table();
		iPlayer p1 = new User();
		t.addPlayer(p1);
		assertTrue(p1 == t.getCurrentPlayer());
	}

	@Test
	public void testAddTableCard() throws TableCardsFullException {
		Table t = new Table();
		Card c = new Card(Suit.SPADES, Card.Rank.TWO);
		t.addTableCard(c);
		List<Card> tableCards = t.getTableCards();
		assertTrue(tableCards.get(0).equals(c));
	}
	
	@Test(expected=TableCardsFullException.class) 
	public void testTableCardsFull() throws TableCardsFullException {
		Table t = new Table();
		Card c = new Card(Suit.SPADES, Card.Rank.TWO);
		for (int i = 0; i <= 5; i++) {
			t.addTableCard(c);
		}	
	}
	
	@Test
	public void testClearTableCards() throws TableCardsFullException {
		Table t = new Table();
		Card c = new Card(Suit.SPADES, Card.Rank.TWO);
		t.addTableCard(c);
		t.clearTableCards();
		assertTrue(t.getTableCards().size() == 0);
	}
	
	@Test
	public void testMakeHandVisible() {
		Table t = new Table();
		iPlayer p = new User();
		p.getHand().setVisible(false);
		t.makeHandVisible(p);
		assertTrue(p.getHand().isVisible());
	}
	
	@Test
	public void testDistributePot() {
		//TODO kan implementeras n�r getWinners implementerats
	}
}
