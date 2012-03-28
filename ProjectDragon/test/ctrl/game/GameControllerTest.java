package ctrl.game;

import static org.junit.Assert.*;

import java.util.List;

import model.game.Card;
import model.game.Table;

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

}
