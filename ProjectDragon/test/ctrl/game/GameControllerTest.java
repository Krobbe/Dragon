package ctrl.game;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.card.Card;
import model.card.Card.Rank;
import model.card.Card.Suit;
import model.game.Table;
import model.player.Balance;
import model.player.Bet;
import model.player.Player;
import model.player.User;
import model.player.IPlayer;
import model.player.hand.TexasHoldemHand;

import org.junit.Test;

import ctrl.game.GameController;

import utilities.IllegalRaiseException;
import utilities.PlayersFullException;
import utilities.CommunityCardsFullException;

/**
 * A class containing methods testing the GameController class.
 * @author mattiashenriksson
 *
 */

public class GameControllerTest {
	
	//TODO ta bort dessa? testar numera privata metoder
	/*
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
	*/
	@Test
	public void testRaise() throws PlayersFullException, IllegalRaiseException {
		Table t = new Table();
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
		Table t = new Table();
		GameController gc = new GameController(t);
		IPlayer u1 = new User();
		IPlayer u2 = new User();
		t.addPlayer(u1);
		t.addPlayer(u2);
		Card c1 = new Card(Suit.CLUBS, Rank.ACE);
		Card c2 = new Card(Suit.DIAMONDS, Rank.ACE);
		u1.addCard(c1);
		u1.addCard(c2);
		u1.setActive(true);
		gc.fold(t.getCurrentPlayer());
		assertTrue(u1.getHand().getCards().size() == 0);
		assertTrue(!u1.isActive());
		assertTrue(t.getCurrentPlayer() == u2);
	}
	
	@Test
	public void testNextRound() throws PlayersFullException {
		Table t = new Table();
		GameController gc = new GameController(t);
		IPlayer u1 = new User(), u2 = new User(), u3 = new User(), u4 = new User();
		t.addPlayer(u1); t.addPlayer(u2); t.addPlayer(u3); t.addPlayer(u4);	
		List<IPlayer> players = t.getPlayers();
		for (IPlayer p : players) {
			p.addCard(new Card());
			p.setActive(false);
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
