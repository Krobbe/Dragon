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
		List<Card> tableCardsT0 = t.getTableCards();
		gc.showFlop();
		List<Card> tableCardsT = t.getTableCards();
		assertTrue(tableCardsT.size() - tableCardsT0.size() == 3);
	}
	
	@Test
	public void testShowRiver() {
		Table t = new Table();
		GameController gc = new GameController(t);
		List<Card> tableCardsT0 = t.getTableCards();
		gc.showFlop();
		List<Card> tableCardsT = t.getTableCards();
		assertTrue(tableCardsT.size() - tableCardsT0.size() == 1);
	}

}
