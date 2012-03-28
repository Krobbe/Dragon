package ctrl.game;

import java.util.List;

import model.game.Card;
import model.game.Dealer;
import model.game.Pot;
import model.game.Table;
import model.player.iPlayer;
import model.player.User;

/**
 * This class contains methods that handles the application during game mode.
 * 
 * @author mattiashenriksson
 * @author robinandersson
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

	/**
	 * Distributes the cards to all remaining players in the round
	 * 
	 * @author robinandersson
	 */
	public void distributeCards() {

		List<iPlayer> players = table.getPlayers();
		Dealer dealer = table.getDealer();

		// Prepares the list of players to simplify the distribution of cards
		prepareList(players);

		/*
		 * Every (active) player gets two cards where the first is distributed
		 * directly, and the second after everyone else has gotten their first
		 * card
		 */
		for (int i = 1; i <= 2; i++) {

			for (iPlayer player : players) {

				if (player.isActive()) {
					player.addCard(dealer.popCard());
				}
			}
		}

		// Restores the list to the previous state before it was prepared
		restoreList(players);

	}

	/*
	 * Short method for rearranging the list to simplify the distribution of
	 * cards.
	 * 
	 * @author robinandersson
	 */
	private void prepareList(List<iPlayer> players) {
		players.add(players.remove(0));
		players.add(players.remove(0));
	}

	/*
	 * Short method to reset the list to the previous order of players before it
	 * was arranged to the distribution of cards.
	 * 
	 * @author robinandersson
	 */
	private void restoreList(List<iPlayer> players) {
		iPlayer player = players.remove(players.size() - 1);
		players.add(0, player);

		player = players.remove(players.size() - 1);
		players.add(0, player);
	}

	/**
	 * @author forssenm Method for handling the call scenario
	 */
	public void call() {
		int currentBet = table.getRound().getBettingRound().getCurrentBet();
		User currentPlayer = (User) table.getCurrentPlayer();
		currentPlayer.call(currentBet);
		currentPlayer.getBalance().removeFromBalance(currentBet);
		Pot currentPot = table.getRound().getPot();
		currentPot.addToPot(currentBet);

	}
	
	/**
	 * Performs a showdown.
	 */
	public List<iPlayer> doShowdown() {
		return table.doShowdown();
	}

}
