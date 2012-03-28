package ctrl.game;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.game.Card;
import model.game.Table;
import model.player.Balance;
import model.player.Player;
import model.player.User;
import model.player.iPlayer;
import model.player.hand.TexasHoldemHand;

import org.junit.Test;

/**
 * A class containing methods testing the GameController class.
 * @author mattiashenriksson
 *
 */

public class GameControllerTest {

	@Test
	public void testShowFlop() {
		Table t = new Table();
		GameController gc = new GameController(t);
		int tableCardsLengthT0 = t.getTableCards().size();
		gc.showFlop();
		List<Card> tableCardsT = t.getTableCards();
		int tableCardsLengthT = t.getTableCards().size();
		assertTrue(tableCardsLengthT - tableCardsLengthT0 == 3);
	}
	
	@Test
	public void testShowRiver() {
		Table t = new Table();
		GameController gc = new GameController(t);
		int tableCardsLengthT0 = t.getTableCards().size();
		gc.showRiver();
		int tableCardsLengthT = t.getTableCards().size();
		assertTrue(tableCardsLengthT - tableCardsLengthT0 == 1);
	}
	
	@Test
	public void testRaise() {
		Table t = new Table();
		GameController gc = new GameController(t);
		iPlayer u = new User(new Player(new TexasHoldemHand(false),"Mattias"
				, new Balance(100)));
		t.addPlayer(u);
		gc.raise(50);
		assertTrue(u.getBalance().getValue() == 50);
		assertTrue(t.getRound().getPot().getValue() == 50);
		assertTrue(t.getRound().getBettingRound().getCurrentBet()
				.getValue() == 50);
	}

}
