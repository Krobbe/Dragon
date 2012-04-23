package ctrl.game;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.game.BettingRound;
import model.game.Card;
import model.game.Dealer;
import model.game.Pot;
import model.game.SidePotHandler;
import model.game.Table;
import model.player.Bet;
import model.player.iPlayer;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;
import utilities.PlayersFullException;
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
	//TODO skulle kunna vara i table?
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
	//TODO skulle kunna vara i table?
	public void showFlop() throws TableCardsFullException {
		Dealer dealer = table.getDealer();
		List<Card> flop = dealer.getFlop();
		for (Card c : flop) {
			table.addTableCard(c);
		}
	}

	/**
	 * Calls the distributeCards() method in the table class where the actual
	 * distribution of the two "personal cards" to every remaining player is
	 * done 
	 * 
	 * @author robinandersson
	 */
	public void distributeCards() {

		/* 
		 * The  distribution is invoked by this control class and redirected
		 * to the table class which makes more sense and also eliminates a bunch
		 * of unnecessary method calls between the two classes
		 */
		table.distributeCards();
	}

	//TODO: Koden i call, raise, check, fold är lite lik. refactor?
	/**
	 * @author forssenm Method for handling the call scenario
	 * @author mattiashenriksson
	 * @throws IllegalCallException 
	 */
	public void call() throws IllegalCallException {
		Bet currentBet = table.getRound().getBettingRound().getCurrentBet();
		if (currentBet.getValue() == -1 || currentBet.getValue() == 0) {
			throw new IllegalCallException();
		}
		iPlayer currentPlayer = table.getCurrentPlayer();
		currentPlayer.getBalance().removeFromBalance(currentBet.getValue() 
				- currentPlayer.getOwnCurrentBet());
		Pot currentPot = table.getRound().getPot();
		currentPot.addToPot(currentBet.getValue() 
				- currentPlayer.getOwnCurrentBet());
		currentPlayer.setOwnCurrentBet(currentBet.getValue());
		currentPlayer.setDoneFirstTurn(true);
	}
	
	/**
	 * Performs a raise
	 * @param amount The amount to raise the pot with.
	 * @throws IllegalRaiseException 
	 */
	//TODO delvis otestad
	public void raise(int amount) throws IllegalRaiseException {
		iPlayer currentPlayer = table.getCurrentPlayer();
		BettingRound currentBettingRound = table.getRound().getBettingRound(); 
		if(amount + currentPlayer.getOwnCurrentBet() <= 
				currentBettingRound.getCurrentBet().getValue()) {
			throw new IllegalRaiseException();
		}
		currentPlayer.getBalance().removeFromBalance(amount);
		table.getRound().getPot().addToPot(amount);
		currentBettingRound.setCurrentBet( new Bet(table.getCurrentPlayer(),
						amount + currentPlayer.getOwnCurrentBet()));
		currentPlayer.setOwnCurrentBet(amount + currentPlayer.getOwnCurrentBet());
		currentPlayer.setDoneFirstTurn(true);
	}
	
	/**
	 * Performs a fold.
	 */
	//TODO Delvis otestad
	public void fold() {
		table.getCurrentPlayer().getHand().discard();
		table.getCurrentPlayer().setActive(false);
		table.getCurrentPlayer().setDoneFirstTurn(true);
	}
	
	/**
	 * Performs a check.
	 * @throws IllegalCheckException 
	 */
	//TODO otestat: exception, setOwncurrentBet
	public void check() throws IllegalCheckException {
		iPlayer currentPlayer = table.getCurrentPlayer();
		if (currentPlayer.getOwnCurrentBet() < table.getRound()
				.getBettingRound().getCurrentBet().getValue()) {
			throw new IllegalCheckException();
		}
		currentPlayer.setDoneFirstTurn(true);
	}
	
	/**
	 * Performs a showdown.
	 * @return a list of the winning players of the current round.
	 * @throws TableCardsFullException 
	 */
	//TODO nödvändig här? den finns ju redan i table. dock lite annorulunda nu..
	//TODO snygga till?
	public List<iPlayer> preformShowdown(List<iPlayer> plrs, int potAmount) throws TableCardsFullException {
		
		/* put the rigth amount of table cards on the table */
		int tableCardsSize = table.getTableCards().size();
		if (tableCardsSize == 0) {
			showFlop();
			showRiver();
			showRiver();
		} else if (tableCardsSize == 3) {
			showRiver();
			showRiver();
		} else if (tableCardsSize == 4) {
			showRiver();
		}
		
		/* showdown */
		return table.doShowdown(plrs, potAmount);
	}
	
	/**
	 * Performs actions required for starting a new round
	 */
	//TODO Bättre java-doc?
	public void nextRound() {
		//TODO distribute pot?
		List<iPlayer> players = table.getPlayers();
		table.getRound().getPot().emptyPot();
		table.getRound().getPreBettingPot().emptyPot();
		table.clearTableCards();
		table.getSidePots().clear();
		for (iPlayer p : players) {
			p.getHand().discard();
			p.setOwnCurrentBet(0);
			p.setDoneFirstTurn(false);			
			if (p.getBalance().getValue() != 0) {
				p.setActive(true);
			}
		}
		table.getRound().getPot().emptyPot();
		table.getDealer().newDeck();
		distributeCards();
		//TODO kolla så detta inte görs nån annanstans..
		table.nextDealerButtonIndex();
		postBlinds();
		//TODO funkar för två spelare?
		table.setIndexOfCurrentPlayer((table.getDealerButtonIndex() + 3) % 
				table.getPlayers().size());
	}
	
	/**
	 * Makes the players who's turn it is to post the big and small blind to do 
	 * so.
	 */
	//TODO ska denna vara här? likt raise = refactor?
	private void postBlinds() {
		int bigBlind = 20, smallBlind = bigBlind / 2; //TODO ska detta göras i "parameters"?
		int dealerButtonIndex = table.getDealerButtonIndex();
		int smallBlindIndex = (dealerButtonIndex + 1) % table.getPlayers().size();
		int bigBlindIndex = (dealerButtonIndex + 2) % table.getPlayers().size();
		List<iPlayer> players = table.getPlayers();
		iPlayer smallBlindPlayer = players.get(smallBlindIndex);
		iPlayer bigBlindPlayer = players.get(bigBlindIndex);
		
		smallBlindPlayer.getBalance().removeFromBalance(smallBlind);
		bigBlindPlayer.getBalance().removeFromBalance(bigBlind);
		smallBlindPlayer.setOwnCurrentBet(smallBlind);
		bigBlindPlayer.setOwnCurrentBet(bigBlind);
		table.getRound().getPot().addToPot(smallBlind + bigBlind);
		table.getRound().getBettingRound().setCurrentBet(
				new Bet(bigBlindPlayer,bigBlind));
	}
	
	/**
	 * Performs actions required for starting a new betting round
	 * @throws TableCardsFullException 
	 * @throws PlayersFullException 
	 */
	//TODO Bättre java-doc och förklarande kommentarer?
	//TODO övergripande metod = annat namn?
	//TODO List<iPlayer> winners ful lösning?
	public List<iPlayer> nextBettingRound() throws TableCardsFullException, PlayersFullException {
		List<iPlayer> winners = null;
		table.getRound().getBettingRound().setCurrentBet(new Bet());
		List<iPlayer> players = table.getPlayers();
		for (iPlayer p : players) {
			p.setOwnCurrentBet(0);
			p.setDoneFirstTurn(false);
		}
		
		/* Give right player the turn */
		table.setIndexOfCurrentPlayer(table.getDealerButtonIndex());
		for (int i = 0; i < 2; i++) {
			table.nextPlayer();
		}
		
		if (table.getTableCards().size() == 5 || 
				table.getActivePlayers().size() == 1 || 
				table.getActivePlayers().size() == 0) {
			
			List<SidePotHandler> sidePots = table.getSidePots();
			if (sidePots != null) {
				for (SidePotHandler sph : sidePots) {
					winners = preformShowdown(sph.getPlayers(),sph.getPot().getValue());
				}
			}
			
			if (table.getActivePlayers().size() != 0) { /* om alla spelare var all-in ska inte denna göras */
				winners = preformShowdown(table.getActivePlayers(), table.getRound().getPot().getValue());
			}
		} else if (table.getTableCards().size() == 0) {
			showFlop();
			table.getRound().getPreBettingPot().setValue(table.getRound().getPot().getValue());
		} else { //TODO ett till alternativ för showTurn?
			showRiver();
			table.getRound().getPreBettingPot().setValue(table.getRound().getPot().getValue());
		}
		return winners;
	}
}
