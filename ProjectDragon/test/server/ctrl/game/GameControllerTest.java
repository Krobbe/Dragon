package server.ctrl.game;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import common.model.card.Card;
import common.model.card.Rank;
import common.model.card.Suit;
import common.model.game.Pot;
import common.model.player.Balance;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.Player;
import common.model.player.User;
import common.model.player.hand.TexasHoldemHand;
import common.utilities.IllegalCallException;
import common.utilities.IllegalCheckException;
import common.utilities.IllegalRaiseException;
import common.utilities.PlayersFullException;

import server.ctrl.game.GameController;
import server.model.game.Table;

/**
 * A class containing methods testing the GameController class.
 * @author mattiashenriksson
 *
 */

public class GameControllerTest {
	int maxPlayers = 10;
	int entranceFee = 100;
	int startingChips = 1000;
	
	
	@Test
	public void testAddPlayer() {
		Table t = new Table(maxPlayers, entranceFee, startingChips);
		GameController gc = new GameController(t);
		IPlayer p = new Player();
		gc.addPlayer(p);
		assertTrue(t.getPlayers().get(t.getPlayers().size()-1).equals(p));
	}
	
	@Test
	public void testAddPlayers() {
		Table t = new Table(maxPlayers, entranceFee, startingChips);
		GameController gc = new GameController(t);
		IPlayer p1 = new Player();
		Collection<IPlayer> players = new ArrayList<IPlayer>();
		players.add(p1);
		gc.addPlayers(players);
		assertTrue(t.getPlayers().equals(players));
	}
	
	public void testCall() {
		Table t = new Table(maxPlayers,entranceFee,startingChips);
		GameController gc = new GameController(t);
		IPlayer p1 = new Player();
		IPlayer p2 = new Player();
		t.addPlayer(p1);
		t.addPlayer(p2);
		Bet b = new Bet(p1, 100);
		t.getRound().getBettingRound().setCurrentBet(b);
		t.setIndexOfCurrentPlayer(1);
		assertTrue(!gc.call(b));
		t.setIndexOfCurrentPlayer(0);
		assertTrue(gc.call(b));
	}
	
	@Test(expected=IllegalCallException.class)
	public void testBadCall() {
		Table t = new Table(maxPlayers,entranceFee,startingChips);
		GameController gc = new GameController(t);
		IPlayer p1 = new Player();
		gc.addPlayer(p1);
		Bet b = new Bet(p1, 100);
		gc.call(b);
	}
	
	public void testCheck() {
		Table t = new Table(maxPlayers,entranceFee,startingChips);
		GameController gc = new GameController(t);
		IPlayer p1 = new Player();
		IPlayer p2 = new Player();
		t.addPlayer(p1);
		t.addPlayer(p2);
		t.setIndexOfCurrentPlayer(1);
		Bet b = new Bet(p1, 100);
		assertTrue(!gc.check(b));
		t.setIndexOfCurrentPlayer(0);
		assertTrue(gc.check(b));
	}
	
	@Test(expected=IllegalCheckException.class)
	public void testBadCheck() {
		Table t = new Table(maxPlayers,entranceFee,startingChips);
		GameController gc = new GameController(t);
		IPlayer p1 = new Player();
		IPlayer p2 = new Player();
		t.addPlayer(p1);
		t.addPlayer(p2);
		Bet b1 = new Bet(p1, 100);
		Bet b2 = new Bet(p2, 1);
		t.getRound().getBettingRound().setCurrentBet(b1);
		t.setIndexOfCurrentPlayer(0);
		gc.check(b2);
	}
	
	@Test
	public void testRaise() throws PlayersFullException, IllegalRaiseException {
		Table t = new Table(maxPlayers, entranceFee, startingChips);
		GameController gc = new GameController(t);
		IPlayer u1 = new User(new Player(new TexasHoldemHand(),"Mattias"
				, new Balance(100)));
		IPlayer u2 = new User();
		t.addPlayer(u1);
		t.addPlayer(u2);
		gc.raise(new Bet(t.getCurrentPlayer(), t.getRound().
				getBettingRound().getCurrentBet().getValue() + 50));
		assertTrue(u1.getBalance().getValue() == 50);
		assertTrue(t.getRound().getPot().getValue() == 50);
		assertTrue(t.getRound().getBettingRound().getCurrentBet()
				.getValue() == 50);
		assertTrue(t.getCurrentPlayer() == u2);
	}
	
	@Test
	public void testFold() throws PlayersFullException {
		Table t = new Table(maxPlayers, entranceFee, startingChips);
		GameController gc = new GameController(t);
		IPlayer u1 = new User();
		IPlayer u2 = new User();
		t.addPlayer(u1);
		t.addPlayer(u2);
		Card c1 = new Card(Suit.CLUBS, Rank.ACE);
		Card c2 = new Card(Suit.DIAMONDS, Rank.ACE);
		u1.getHand().addCard(c1);
		u1.getHand().addCard(c2);
		u1.setActive(true);
		gc.fold(t.getCurrentPlayer());
		assertTrue(u1.getHand().getCards().size() == 0);
		assertTrue(!u1.isActive());
		assertTrue(t.getCurrentPlayer() == u2);
	}
	
	@Test
	public void testNextRound() throws PlayersFullException {
		Table t = new Table(maxPlayers, entranceFee, startingChips);
		GameController gc = new GameController(t);
		IPlayer u1 = new User(), u2 = new User(), u3 = new User(), u4 = new User();
		gc.addPlayer(u1); gc.addPlayer(u2); gc.addPlayer(u3); gc.addPlayer(u4);	
		List<IPlayer> players = t.getPlayers();
		for (IPlayer p : players) {
			p.getHand().addCard(new Card());
			p.setActive(true);
		}
		t.getRound().getPot().addToPot(33);
		t.getRound().getBettingRound().setCurrentBet(new Bet(u1,22));
		int previousIndexOfDealerButton = t.getDealerButtonIndex();
		gc.nextRound();
		
		for (IPlayer p : players) {
			assertTrue(p.getHand().getCards().size() == 2);
			assertTrue(p.isActive());
		}
		assertTrue(t.getRound().getPot().getValue() == 0);
		assertTrue(t.getRound().getBettingRound().getCurrentBet()
				.getValue() == 0);
		assertTrue((previousIndexOfDealerButton + 1)% t.getPlayers().size() == 
				t.getDealerButtonIndex());
		assertTrue((t.getDealerButtonIndex() + 3) % t.getPlayers().size() == 
				t.getIndexOfCurrentPlayer());	
	}

}
