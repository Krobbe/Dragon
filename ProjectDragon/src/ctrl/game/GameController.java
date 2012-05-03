package ctrl.game;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.card.Card;
import model.game.BettingRound;
import model.game.Dealer;
import model.game.Pot;
import model.game.SidePotHandler;
import model.game.Table;
import model.player.Bet;
import model.player.OwnCurrentBetComparator;
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
	
	/**
	 * @author robinandersson
	 *
	 */
	public GameController() {
		this(new Table());
	}

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
	 * Method for handling the call scenario
	 * @author forssenm 
	 * @author mattiashenriksson
	 * @throws IllegalCallException 
	 */
	public void call() throws IllegalCallException {
		iPlayer currentPlayer = table.getCurrentPlayer();
		Pot currentPot = table.getRound().getPot();
		int currentBetValue = 
				table.getRound().getBettingRound().getCurrentBet().getValue();
		
		/* if there hasn't yet been any bets you should not be able to call*/
		if (currentBetValue == -1 //TODO -1 nödvändig?
				|| currentBetValue == 0) {
			throw new IllegalCallException("There hasn't been any bets yet");
		}
		
		/* if the player calls a bet which is bigger than his balance, the 
		 * player will call with as much as his balance allows and move all-in
		 */
		if (currentBetValue > currentPlayer.getBalance().getValue() 
				+ currentPlayer.getOwnCurrentBet()) {
			currentBetValue = currentPlayer.getBalance().getValue() 
				+ currentPlayer.getOwnCurrentBet();
		}
		
		//TODO den här delen skulle kunna refactoreras kanske?
		/* arrange the player's balance, bet and the pot according to the call 
		 */
		currentPlayer.getBalance().removeFromBalance(currentBetValue 
				- currentPlayer.getOwnCurrentBet());
		currentPot.addToPot(currentBetValue 
				- currentPlayer.getOwnCurrentBet());
		currentPlayer.setOwnCurrentBet(currentBetValue);
		
		/* the player has now done a move */
		currentPlayer.setDoneFirstTurn(true);
	}
	
	public boolean call(Bet bet) {
		
		boolean validCall = true;
		
		if(!table.getCurrentPlayer().equals(bet.getOwner())){
			validCall = false;
		}
		
		return validCall;
	}
	
	/**
	 * Performs a raise.
	 * @param amount The amount to raise the pot with.
	 * @throws IllegalRaiseException 
	 */
	//TODO delvis otestad
	public void raise(int amount) throws IllegalRaiseException {
		iPlayer currentPlayer = table.getCurrentPlayer();
		BettingRound currentBettingRound = table.getRound().getBettingRound(); 
		
		/* you should not be able to raise a bigger amount than what you have
		 * on your balance */
		if(amount + currentPlayer.getOwnCurrentBet() <= 
				currentBettingRound.getCurrentBet().getValue()) {
			throw new IllegalRaiseException(
					"Not enough money on balance to make that raise");
		}
		
		/* arrange the player's balance, bet and the pot according to the raise 
		 */
		currentPlayer.getBalance().removeFromBalance(amount);
		table.getRound().getPot().addToPot(amount);
		currentBettingRound.setCurrentBet( new Bet(table.getCurrentPlayer(),
						amount + currentPlayer.getOwnCurrentBet()));
		currentPlayer.setOwnCurrentBet(
				amount + currentPlayer.getOwnCurrentBet());
		
		/* the player has now done a move */
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
	//TODO otestad
	public void check() throws IllegalCheckException {
		iPlayer currentPlayer = table.getCurrentPlayer();
		
		/* if there is at bet bigger than your own current bet you should not
		 * be able to check */
		if (currentPlayer.getOwnCurrentBet() < table.getRound()
				.getBettingRound().getCurrentBet().getValue()) {
			throw new IllegalCheckException(
				"Your own current bet is lesser than the global current bet");
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
		
		/* set the turn to the right player */
		//TODO funkar för två spelare?, detta görs på ett flertal ställen = 
		//		refactor? 

		int indexOfCurrentPlayer = table.getDealerButtonIndex();
		for (int i = 0; i < 3; i++) {
			do {
				indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % table.getPlayers().size();
			} while (!players.get(indexOfCurrentPlayer).isActive());
		}
		table.setIndexOfCurrentPlayer(indexOfCurrentPlayer);
	}
	
	/**
	 * Makes the players who's turn it is to post the big and small blind to do 
	 * so.
	 */
	//TODO ska denna vara här? likt raise = refactor?
	private void postBlinds() {
		List<iPlayer> players = table.getPlayers();
		int bigBlind = 20, smallBlind = bigBlind / 2; //TODO ska detta göras i "parameters"?
		int dealerButtonIndex = table.getDealerButtonIndex();
		
		/* calculate smallBlindIndex */
		int smallBlindIndex;
		int count = 1;
		do {
			smallBlindIndex = (dealerButtonIndex + count) % table.getPlayers().size();
			count++;
		} while (!players.get(smallBlindIndex).isActive());
		
		/* calculate bigBlindIndex */
		int bigBlindIndex;
		count = 1;
		do {
			bigBlindIndex = (smallBlindIndex + count) % table.getPlayers().size();
			count++;
		} while (!players.get(bigBlindIndex).isActive());
		
		iPlayer smallBlindPlayer = players.get(smallBlindIndex);
		iPlayer bigBlindPlayer = players.get(bigBlindIndex);
		
		/* post blinds */
		if (smallBlindPlayer.getBalance().getValue() < smallBlind) {
			smallBlind = smallBlindPlayer.getBalance().getValue();
		}
		smallBlindPlayer.getBalance().removeFromBalance(smallBlind);
		smallBlindPlayer.setOwnCurrentBet(smallBlind);
		
		if (bigBlindPlayer.getBalance().getValue() < bigBlind) {
			bigBlind = bigBlindPlayer.getBalance().getValue();
		}
		bigBlindPlayer.getBalance().removeFromBalance(bigBlind);
		bigBlindPlayer.setOwnCurrentBet(bigBlind);

		table.getRound().getPot().addToPot(smallBlind + bigBlind);
		if (bigBlind >= smallBlind) {
			table.getRound().getBettingRound().setCurrentBet(
				new Bet(bigBlindPlayer,bigBlind));
		} else {
			table.getRound().getBettingRound().setCurrentBet(
					new Bet(smallBlindPlayer,smallBlind));
		}
		
		/* if a player has gone all-in they shall not be able to act */
		//TODO ska denna vara här?
		if (bigBlindPlayer.getBalance().getValue() == 0) {
			bigBlindPlayer.setDoneFirstTurn(true);
		}
		if (smallBlindPlayer.getBalance().getValue() == 0) {
			smallBlindPlayer.setDoneFirstTurn(true);
		}
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
	
	/**
	 * Performs the actions which occur when a player has gone all-in 
	 */
	//TODO mkt förklaringar hör. skulle koden varit mer självförklarande?
	public void handleAllIn() {
		List<iPlayer> allInPlayers = table.getAllInPlayers();
		List<iPlayer> activePlayers = table.getActivePlayers();
		List<SidePotHandler> sidePots = table.getSidePots();
		
		/* sort allInPlayers so that the next task is performed in the correct
		 * order */
		Collections.sort(allInPlayers, new OwnCurrentBetComparator());
		
		/* for each player that has gone all-in a SidePotHandler containing '
		 * info regarding that all-in case is created */
		for (iPlayer p : allInPlayers) {
			
				/* calculate how big the all-in bet was and conduct neccesary 
				 * changes according to this bet */
				int allInAmount = p.getOwnCurrentBet();
				int sidePotValue = allInAmount * activePlayers.size() 
						+ table.getRound().getPreBettingPot().getValue();
				for (iPlayer ap : activePlayers) {
					ap.setOwnCurrentBet(ap.getOwnCurrentBet() - allInAmount);
				}
				table.getRound().getPreBettingPot().emptyPot();
				table.getRound().getPot().removeFromPot(sidePotValue);
				
				/* create the sidepot */
				Pot sidePot = new Pot(sidePotValue);
				sidePots.add(new SidePotHandler(table.getActivePlayers(), sidePot));
		
				/* controll prints */
	            System.out.println("\n\n-------------------------------\n" + 
	            "SIDEPOT ADDED\n");
	            System.out.println("sidePotValue: " + sidePotValue + "\n");
	            System.out.println("ADDED PLAYERS:");
	            for (iPlayer ap : table.getActivePlayers() )
	            	System.out.println(ap.getName());
	            System.out.println("\n-----------------------------------\n");
				
	            /* the all-in player should after this not longer be active */
	            p.setActive(false);
		} 
	}
}
