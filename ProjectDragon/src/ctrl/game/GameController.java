package ctrl.game;

import java.util.List;

import model.game.Card;
import model.game.Dealer;
import model.game.Pot;
import model.game.Round;
import model.game.Table;
import model.player.Bet;
import model.player.iPlayer;
import utilities.IllegalCheckException;
import utilities.TableCardsFullException;

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
	 * @throws TableCardsFullException 
	 */
	//TODO private?
	public void showRiver() throws TableCardsFullException {
		Dealer dealer = table.getDealer();
		Card c = dealer.getRiver();
		table.addTableCard(c);
	}

	/**
	 * Adds three new cards to the "table cards"
	 * @throws TableCardsFullException 
	 */
	//TODO private?
	public void showFlop() throws TableCardsFullException {
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

	//TODO: Koden i call, raise, check, fold är lite lik. refactor?
	/**
	 * @author forssenm Method for handling the call scenario
	 * @author mattiashenriksson
	 */
	public void call() {
		Bet currentBet = table.getRound().getBettingRound().getCurrentBet();
		iPlayer currentPlayer = table.getCurrentPlayer();
		currentPlayer.getBalance().removeFromBalance(currentBet.getValue());
		Pot currentPot = table.getRound().getPot();
		currentPot.addToPot(currentBet.getValue());
		currentPlayer.setOwnCurrentBet(currentBet.getValue());
		table.nextPlayer();
	}
	
	/**
	 * Performs a raise
	 * @param amount The amount to raise the pot with.
	 */
	//TODO delvis otestad
	public void raise(int amount) {
		iPlayer currentPlayer = table.getCurrentPlayer();
		currentPlayer.getBalance().removeFromBalance(amount);
		table.getRound().getPot().addToPot(amount);
		table.getRound().getBettingRound().setCurrentBet(
				new Bet(table.getCurrentPlayer(),amount));
		if (currentPlayer.getOwnCurrentBet() == -1) {
			currentPlayer.setOwnCurrentBet(0);
		}
		currentPlayer.setOwnCurrentBet(amount + currentPlayer.getOwnCurrentBet());
		table.nextPlayer();
	}
	
	/**
	 * Performs a fold.
	 */
	public void fold() {
		table.getCurrentPlayer().getHand().discard();
		table.getCurrentPlayer().setActive(false);
		table.nextPlayer();
	}
	
	/**
	 * Performs a check.
	 * @throws IllegalCheckException 
	 */
	//TODO otestat: exception, setOwncurrentBet
	public void check() throws IllegalCheckException {
		iPlayer currentPlayer = table.getCurrentPlayer();
		if (currentPlayer.getOwnCurrentBet() == -1) {
			currentPlayer.setOwnCurrentBet(0);
		}
		if (currentPlayer.getOwnCurrentBet() < table.getRound()
				.getBettingRound().getCurrentBet().getValue()) {
			throw new IllegalCheckException();
		}
		table.nextPlayer();
	}
	
	/**
	 * Performs a showdown.
	 * @return a list of the winning players of the current round.
	 */
	public List<iPlayer> doShowdown() {
		return table.doShowdown();
	}
	
	/**
	 * Performs actions required for starting a new round
	 */
	//TODO Bättre java-doc?
	public void nextRound() {
		//TODO distribute pot?
		List<iPlayer> players = table.getPlayers();
		for (iPlayer p : players) {
			p.getHand().discard();
			p.setActive(true);
			p.setOwnCurrentBet(-1);
		}
		Round r = table.getRound();
		r.getPot().emptyPot();
		r.getBettingRound().setCurrentBet(new Bet());
		distributeCards();
		//TODO kolla så detta inte görs nån annanstans..
		table.nextDealerButtonIndex();
		//TODO funkar för två spelare?
		table.setIndexOfCurrentPlayer((table.getDealerButtonIndex() + 3) % 
				table.getPlayers().size());
	}
	
	/**
	 * Performs actions required for starting a new betting round
	 * @throws TableCardsFullException 
	 */
	//TODO Bättre java-doc och förklarande kommentarer?
	//TODO övergripande metod = annat namn?
	//TODO List<iPlayer> winners ful lösning?
	public List<iPlayer> nextBettingRound() throws TableCardsFullException {
		List<iPlayer> winners = null;
		table.getRound().getBettingRound().setCurrentBet(new Bet());
		List<iPlayer> players = table.getPlayers();
		for (iPlayer p : players) {
			p.setOwnCurrentBet(-1);
		}
		if (table.getTableCards().size() == 5 || 
				table.getNumberOfActivePlayers() == 1) {
			winners = doShowdown();
		} else if (table.getTableCards().size() == 0) {
			showFlop();
		} else { //TODO ett till alternativ för showTurn?
			showRiver();
		}
		return winners;
	}
	

}
