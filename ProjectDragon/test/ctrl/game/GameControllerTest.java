package ctrl.game;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.game.Card;
import model.game.Card.Rank;
import model.game.Card.Suit;
import model.game.Table;
import model.player.Balance;
import model.player.Player;
import model.player.User;
import model.player.iPlayer;
import model.player.hand.TexasHoldemHand;

import org.junit.Test;

import utilities.PlayersFullException;
import utilities.TableCardsFullException;

/**
 * A class containing methods testing the GameController class.
 * @author mattiashenriksson
 *
 */

public class GameControllerTest {

	@Test
	public void testShowFlop() throws TableCardsFullException {
		Table t = new Table();
		GameController gc = new GameController(t);
		int tableCardsLengthT0 = t.getTableCards().size();
		gc.showFlop();
		List<Card> tableCardsT = t.getTableCards();
		int tableCardsLengthT = t.getTableCards().size();
		assertTrue(tableCardsLengthT - tableCardsLengthT0 == 3);
	}
	
	@Test
	public void testShowRiver() throws TableCardsFullException {
		Table t = new Table();
		GameController gc = new GameController(t);
		int tableCardsLengthT0 = t.getTableCards().size();
		gc.showRiver();
		int tableCardsLengthT = t.getTableCards().size();
		assertTrue(tableCardsLengthT - tableCardsLengthT0 == 1);
	}
	
	@Test
	public void testRaise() throws PlayersFullException {
		Table t = new Table();
		GameController gc = new GameController(t);
		iPlayer u1 = new User(new Player(new TexasHoldemHand(false),"Mattias"
				, new Balance(100)));
		iPlayer u2 = new User();
		t.addPlayer(u1);
		t.addPlayer(u2);
		gc.raise(50);
		assertTrue(u1.getBalance().getValue() == 50);
		assertTrue(t.getRound().getPot().getValue() == 50);
		assertTrue(t.getRound().getBettingRound().getCurrentBet()
				.getValue() == 50);
		assertTrue(t.getCurrentPlayer() == u2);
	}
	
	@Test
	public void testFold() throws PlayersFullException {
		Table t = new Table();
		GameController gc = new GameController(t);
		iPlayer u1 = new User();
		iPlayer u2 = new User();
		t.addPlayer(u1);
		t.addPlayer(u2);
		Card c1 = new Card(Suit.CLUBS, Rank.ACE);
		Card c2 = new Card(Suit.DIAMONDS, Rank.ACE);
		u1.addCard(c1);
		u1.addCard(c2);
		u1.setActive(true);
		gc.fold();
		assertTrue(u1.getHand().getCards().size() == 0);
		assertTrue(!u1.isActive());
		assertTrue(t.getCurrentPlayer() == u2);
	}
	
	@Test
	public void testNextRound() throws PlayersFullException {
		Table t = new Table();
		GameController gc = new GameController(t);
		iPlayer u1 = new User(), u2 = new User(), u3 = new User();
		t.addPlayer(u1); t.addPlayer(u2); t.addPlayer(u3);		
		List<iPlayer> players = t.getPlayers();
		for (iPlayer p : players) {
			p.addCard(new Card());
			p.setActive(false);
		}
		t.getRound().getPot().addToPot(33);
		//TODO gšr klart
		gc.nextRound();
		
		
	}

}
