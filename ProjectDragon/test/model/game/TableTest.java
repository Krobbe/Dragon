package model.game;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import model.card.Card;
import model.card.Card.Suit;
import model.card.iCard;
import model.player.User;
import model.player.iPlayer;
import model.player.hand.HandValueType;

import org.junit.Test;

import utilities.PlayersFullException;
import utilities.TableCardsFullException;

/**
 * A test of the Table-class
 * @author mattiashenriksson
 * @author lisastenberg
 *
 */

public class TableTest {
	Table t = new Table();
	
	@Test
	public void testGetRound() {
		assertTrue(t.getRound().getPot().getValue() == 0);
	}
	
	@Test
	public void testGetPlayers() {
		iPlayer p1 = new User();
		try {
			t.addPlayer(p1);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		assertTrue(t.getPlayers().get(0).equals(p1));
	}
	
	@Test
	public void testGetActivePlayers() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		p1.setActive(true);
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		assertTrue(t.getActivePlayers().size() == 1);	}
	
	@Test
	public void testGetTableCards() {
		assertTrue(t.getTableCards().equals(new LinkedList<Card>()));
	}
	
	@Test
	public void testGetIndexOfCurrentPlayer() {
		assertTrue(t.getIndexOfCurrentPlayer() == 0);
	}
	
	@Test
	public void testSetIndexOfCurrentPlayer() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		p1.setActive(true);
		p2.setActive(true);
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		t.setIndexOfCurrentPlayer(1);
		assertTrue(t.getIndexOfCurrentPlayer() == 1);
	}
	
	@Test
	public void testAddPlayer() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		List<iPlayer> players = t.getPlayers();
		assertTrue(players.size() == 2);
	}
	
	@Test(expected=PlayersFullException.class) 
	public void testTableFull() throws PlayersFullException {
		iPlayer p1 = new User(), p2 = new User(), p3 = new User(), p4 = new User(), p5 = new User(), 
				p6 = new User(), p7 = new User(), p8 = new User(), p9 = new User(), p10 = new User(), 
				p11 = new User();
		t.addPlayer(p1); t.addPlayer(p2); t.addPlayer(p3); t.addPlayer(p4); t.addPlayer(p5); t.addPlayer(p6);
		t.addPlayer(p7); t.addPlayer(p8); t.addPlayer(p9); t.addPlayer(p10); t.addPlayer(p11);
	}
	
	@Test
	public void testGetCurrentPlayer() {
		iPlayer p1 = new User();
		try {
			t.addPlayer(p1);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		assertTrue(p1 == t.getCurrentPlayer());
	}

	@Test
	public void testNextPlayer() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		p1.setActive(true);
		p2.setActive(true);
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		assertTrue(t.getCurrentPlayer().equals(p1));
		assertTrue(t.nextPlayer().equals(p2));
		assertTrue(t.nextPlayer().equals(p1));
	}
	
	@Test
	public void testGetDealerButtonIndex() {
		assertTrue(t.getDealerButtonIndex() == 0);
	}
	
	@Test
	public void testNextDealerButtonIndex() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		p1.setActive(true);
		p2.setActive(true);
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		assertTrue(t.nextDealerButtonIndex() == 1);
	}

	@Test
	public void testAddTableCard() throws TableCardsFullException {
		t.addCommunityCard();
		List<iCard> tableCards = t.getTableCards();
		assertTrue(tableCards.get(0).getClass() == Card.class);
	}
	
	@Test(expected=TableCardsFullException.class) 
	public void testTableCardsFull() throws TableCardsFullException {
		for (int i = 0; i <= 5; i++) {
			t.addCommunityCard();
		}	
	}
	
	@Test
	public void testClearTableCards() throws TableCardsFullException {
		t.addCommunityCard();
		t.clearTableCards();
		assertTrue(t.getTableCards().size() == 0);
	}
	
	@Test
	public void testMakeHandVisible() {
		iPlayer p = new User();
		p.getHand().setVisible(false);
		t.makeHandVisible(p);
		assertTrue(p.getHand().isVisible());
	}
	
	@Test
	public void testDistributePot() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		List<iPlayer> winners = new LinkedList<iPlayer>();
		
		winners.add(p1);
		winners.add(p2);
		t.getRound().getPot().addToPot(100);
		t.distributePot(winners, t.getRound().getPot().getValue());
		assertTrue(p1.getBalance().getValue() == 50);
	}
	
	//TODO: Is this test ready? It's best if robinandersson checks.
	@Test
	public void testDistributeCards() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		p1.setActive(true);
		p2.setActive(true);
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		t.distributeCards();
		assertTrue(p2.getHand().getCards().size() == 2);
	}
	
	@Test
	public void testDoShowdown() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		p1.setActive(true);
		p2.setActive(true);
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		t.getRound().getPot().addToPot(10);
		p1.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		p2.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
		t.doShowdown(t.getPlayers(), t.getRound().getPot().getValue());
		assertTrue(p1.getBalance().getValue() == 10);
	}
	
	@Test
	public void testGetHandTypes() {
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		p1.setActive(true);
		p2.setActive(true);
		try {
			t.addPlayer(p1);
			t.addPlayer(p2);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		t.getRound().getPot().addToPot(10);
		p1.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
		p2.getHand().addCard(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
		t.doShowdown(t.getPlayers(), t.getRound().getPot().getValue());
		assertTrue(t.getHandTypes().get(p1).equals(HandValueType.HIGH_CARD));
	}
	
	@Test
	public void testToString() {
		iPlayer p1 = new User();
		p1.setActive(true);
		try {
			t.addPlayer(p1);
		} catch (PlayersFullException e) {
			System.out.println("The table is full");
			e.printStackTrace();
		}
		String tmp = t.toString();
		String expected = "Players at table:\n" +
				"Name: Default , Balance: 0 , Active: true , Hand: Texas " +
				"Hold'em Hand: [] , Own current bet: -1\n\n" +
				"Current player is Default\n" +
				"Player with Dealer button is: Default\n" +
				"Table cards are:\n" +
				"[]\n" +
				"Pot is: 0\n" +
				"Current bet is: 0\n";
		assertTrue(tmp.equals(expected));
	}
	
	@Test
	public void testEquals() {
		Table t1 = new Table();
		Table t2 = new Table();
		assertTrue(t1.equals(t1));
		assertFalse(t1.equals(t2));
	}
}
