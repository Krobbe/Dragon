package ctrl.game;

import java.util.List;

import model.game.Card;
import model.game.Dealer;
import model.game.Table;

/**
 * This class contains methods that handles the application during game mode.
 * @author mattiashenriksson
 *
 */

public class GameController {
	
	private Table table;
	
	public GameController(Table table) {
		this.table = table;
	}
	
	/**
	 * Adds a new card to the "table cards"
	 */
	public void showRiver() {
		Dealer dealer = table.getDealer();
		Card c = dealer.getRiver();
		table.addTableCard(c);
	}
	
	/**
	 * Adds three new cards to the "table cards"
	 */
	public void showFlop() {
		Dealer dealer = table.getDealer();
		List<Card> flop = dealer.getFlop();
		for (Card c : flop) {
			table.addTableCard(c);
		}
	}

}
