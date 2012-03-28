package ctrl.game;

import java.util.List;

import model.game.Card;
import model.game.Dealer;
import model.game.Pot;
import model.game.Table;
import model.player.Bet;
import model.player.User;
import model.player.iPlayer;

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

	
	// TODO Write test to distribute cards
	/**
	 * Distributes the cards to all remaining players in the round
	 * 
	 * @author robinandersson
	 */
	public void distributeCards() {

		List<iPlayer> players = table.getPlayers();
		Dealer dealer = table.getDealer();

		/*
		 * Prepares the list of players to simplify the distribution of cards.
		 * The list of players is ordered so that the first player in the list
		 * gets the first card (this is the player directly after the dealer
		 * button)
		 */
		for(int i = 0 ; i <= table.getDealerButtonIndex() ; i++){
			players.add(players.remove(0));
		}

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
		for(int i = 0 ; i <= table.getDealerButtonIndex() ; i++){
			players.add(0, players.remove(players.size() -1));
		}

	}

	
	/**
	 * @author forssenm Method for handling the call scenario
	 */
	public void call() {
		Bet currentBet = table.getRound().getBettingRound().getCurrentBet();
		User currentPlayer = (User)table.getCurrentPlayer();
		currentPlayer.call(currentBet.getValue());
		//TODO ska fšljande vara hŠr eller i User.call()?
		currentPlayer.getBalance().removeFromBalance(currentBet.getValue());
		Pot currentPot = table.getRound().getPot();
		currentPot.addToPot(currentBet.getValue());
	}
	
	/**
	 * Performs a showdown.
	 */
	public List<iPlayer> doShowdown() {
		return table.doShowdown();
	}
	
	public void raise(int amount) {
		table.getCurrentPlayer().removeFromBalance(amount);
		table.getRound().getPot().addToPot(amount);
		table.getRound().getBettingRound().setCurrentBet(
				new Bet(table.getCurrentPlayer(),amount));
	}

}
