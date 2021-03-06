package server.ctrl.game;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import server.event.Event;
import server.event.EventBus;
import server.model.game.Table;

import common.model.game.BettingRound;
import common.model.game.P;
import common.model.game.Pot;
import common.model.game.SidePotHandler;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.utilities.IllegalCallException;
import common.utilities.IllegalCheckException;
import common.utilities.IllegalRaiseException;
import common.utilities.OwnCurrentBetComparator;

/**
 * This class contains methods that handles the application during game mode.
 * 
 * @author mattiashenriksson
 * @author robinandersson
 * @author lisastenberg
 * 
 */

public class GameController {

	private Table table;
	
	public GameController(int maxPlayers, int entranceFee, int startingChips) {
		this(new Table(maxPlayers, entranceFee, startingChips));
	}

	public GameController(Table table) {
		this.table = table;
	}
	
	/**
	 * Adds a new card to the "table cards"
	 */
	public void addCommunityCard() {
		table.addCommunityCard();
	}
	
	/**
	 * Adds a player to the game's table
	 * 
	 * @param player The player to be added
	 * @author robinandersson
	 */
	public void addPlayer(IPlayer player) {
		table.addPlayer(player);
	}
	
	/**
	 * Adds the players in the list to to the game
	 * 
	 * @param player The player to be added
	 * @author robinandersson
	 */
	public void addPlayers(Collection<IPlayer> player) {
		table.addPlayers(player);
	}
	
	/**
	 * Removes a player
	 * @param player
	 */
	public void removePlayer(IPlayer player) {
		table.removePlayer(player);
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
		 * The  distribution is invoked by this controller class and redirected
		 * to the table class. This makes more sense and also eliminates a bunch
		 * of unnecessary method calls between the two classes
		 */
		table.distributeCards();
	}
	
	/**
	 * Returns the gameControllers table object
	 * @return The table object
	 */
	public Table getTable() {
		return table;
	}
	
	/**
	 * Returns the maximum number of allowed players in the table
	 * @return the maximum number of allowed players
	 */
	public int getMaxPlayers() {
		return table.getMaxPlayers();
	}
	
	/**
	 * Returns the entrance fee to join the table
	 * @return the cost of joining the game at the table
	 */
	public int getEntranceFee() {
		return table.getEntranceFee();
	}
	
	/**
	 * Returns the amount of chips players get at the start of the game
	 * @return The amount of chips players get at the start of the game
	 */
	public int getStartingChips() {
		return table.getStartingChips();
	}
	
	/**
	 * Checks if a call is valid and does a call if it is.
	 * 
	 * @author lisastenberg
	 * @author robinandersson
	 * @param bet	The placed bet.
	 * @return true if call is a valid action.
	 * @throws IllegalCallException
	 */
	public boolean call(Bet bet) {
		int currentBetValue = 
				table.getRound().getBettingRound().getCurrentBet().getValue();

		if(!table.getCurrentPlayer().equals(bet.getOwner())) {
			return false;
		} else if (currentBetValue <= 0) {
			throw new IllegalCallException(
					"Not possible to call since no bet has been posted");
		} else {
			doCall();
		}
		return true;
	}
	
	/**
	 * Performs a check.
	 * @throws IllegalCheckException 
	 */
	public boolean check(Bet bet) {
		if(!isValidPlayerAction(bet.getOwner())) {
			return false;
		}
		
		IPlayer currentPlayer = table.getCurrentPlayer();
		/* if there is a bet bigger than your own current bet you should not
		 * be able to check */
		if (currentPlayer.getOwnCurrentBet() < table.getRound()
				.getBettingRound().getCurrentBet().getValue()) {
			throw new IllegalCheckException(
				"Your own current bet is lesser than the global current bet");
		}
		
		currentPlayer.setDoneFirstTurn(true);
		
		EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_BET,bet));
		progressTurn();
		return true;
	}
	
	/**
	 * This method takes a player and creats a side pot with a player that has
	 * gone all-in as reference.
	 * 
	 * @param player
	 *            A player that has gone all-in and figures as reference for a
	 *            side pot
	 */
	private void createSidePots(int sidePotValue) {
		
		table.getSidePots().add(
				new SidePotHandler(table.getActivePlayers(), new Pot(
						sidePotValue)));
	}
	
	/**
	 * Method for handling the call scenario
	 * 
	 * @author forssenm 
	 * @author mattiashenriksson
	 */
	private void doCall() {
		IPlayer currentPlayer = table.getCurrentPlayer();
		int playersOwnCurrentBet = currentPlayer.getOwnCurrentBet();
		int currentBetValue = 
				table.getRound().getBettingRound().getCurrentBet().getValue();
		
		/* if the player calls a bet which is bigger than his balance, the 
		 * player will move all-in
		 */
		if(currentPlayer.getBalance().getValue() < 
				currentBetValue - playersOwnCurrentBet) {
			currentBetValue = currentPlayer.getBalance().getValue() 
					+ playersOwnCurrentBet;
		}

		performPlayerBet(new Bet(currentPlayer,currentBetValue - playersOwnCurrentBet));
		currentPlayer.setDoneFirstTurn(true);
		EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_BET, new Bet(currentPlayer,currentBetValue - playersOwnCurrentBet)));
		progressTurn();
	}
	
	/**
	 * Performs a fold.
	 */
	public boolean fold(IPlayer player) {
		if(!isValidPlayerAction(player)) {
			return false;
		}
		
		IPlayer currentPlayer = table.getCurrentPlayer();
		currentPlayer.getHand().discard();
		currentPlayer.setActive(false);
		currentPlayer.setDoneFirstTurn(true);
		
		EventBus.publish(new Event(Event.Tag.SERVER_FOLD, currentPlayer));
		progressTurn();
		return true;
	}
	
	/**
	 * Performs the actions which occur when a player has gone all-in 
	 */
	private void handleAllInCase() {
		List<IPlayer> allInPlayers = table.getAllInPlayers();

		/*
		 * sort allInPlayers so that the next task is performed in the correct
		 * order
		 */
		Collections.sort(allInPlayers, new OwnCurrentBetComparator());

		/*
		 * for each player that has gone all-in a SidePotHandler containing '
		 * info regarding that all-in case is created
		 */
		for (IPlayer player : allInPlayers) {
			
			/* calculate side pot values */
			int allInValue = player.getOwnCurrentBet();
			int sidePotValue = allInValue * table.getActivePlayers().size()
					+ table.getRound().getPreBettingPot().getValue();
			
			createSidePots(sidePotValue);
			updateMainPot(allInValue,sidePotValue);
			
			/* the player that went all in should not be active after that */
			player.setActive(false);
			EventBus.publish(new Event(Event.Tag.SERVER_SET_PLAYER_UNACTIVE,
					player));
			
		}
	}
	
	/**
	 * Check if it's time to do showdown. This method is used after a 
	 * bettinground is done.
	 * 
	 * It is time to do showdown if all cards are on the table, if just one 
	 * player is left or if all players all all-in.
	 * 
	 * @return true if it is time to do showdown.
	 */
	private boolean isTimeForShowDown() {
		return table.getCommunityCards().size() == 5 || 
				table.getActivePlayers().size() == 1 || 
				table.getActivePlayers().size() == 0;
	}
	
	/**
	 * Checks if all players are all-in.
	 * 
	 * @return true if all players are all-in.
	 */
	private boolean isAllPlayersAllIn() {
		return table.getActivePlayers().size() == 0;
	}
	
	/**
	 * Checks if a player is allowed to do an action. In other words this method
	 * checks if player is the currentPlayer at the table.
	 * 
	 * @param player The player
	 * @return true if the player is allowed to do the action.
	 */
	private boolean isValidPlayerAction(IPlayer player) {
		return player.equals(table.getCurrentPlayer());
	}
		
	/**
	 * Performs actions required for starting a new betting round. 
	 */
	public void nextBettingRound() {	
		playersInitial();
		
		/* give right player the turn */
		table.setIndexOfCurrentPlayer(table.getDealerButtonIndex());
		for (int i = 0; i < 2; i++) {
			table.nextPlayer();
		}
		
		EventBus.publish(new Event(Event.Tag.SERVER_SET_TURN, table.getCurrentPlayer()));
		
		nextGameAction();
	}
	
	/**
	 * The method checks what action is next (if it's time for showDown,
	 * or if it's time to add community cards on the table) and does that action.
	 * 
	 * @lisastenberg
	 */
	private void nextGameAction() {
		int potValue = table.getRound().getPot().getValue();
		
		if(isTimeForShowDown()) {
			
			List<SidePotHandler> sidePots = table.getSidePots();
			/* perform showdown for possible sidepots */
			if (sidePots != null) {
				for (SidePotHandler sph : sidePots) {
					performShowdown(sph.getPlayers(), sph.getPot().getValue());
				}
			}
			
			if (!isAllPlayersAllIn()) { 
				/* perfom showdown for the table's current state*/
				performShowdown(table.getActivePlayers(), potValue);
			}
		
		/* if it's time for flop, show flop */
		} else if (table.getCommunityCards().size() == 0) {
			for(int i = 0; i < 3; i++) {
				addCommunityCard();
			}
			table.getRound().getPreBettingPot().setValue(potValue);
			
		// if it's time for river or turn	
		} else {
			addCommunityCard();
			table.getRound().getPreBettingPot().setValue(potValue);
		}
	}
	
	/**
	 * Performs actions required for starting a new round
	 */
	public void nextRound() {
		playersInitial();
		List<IPlayer> players = table.getPlayers();
		for (IPlayer player : players) {
			player.getHand().discard();
			if (!player.isAllIn()) {
				player.setActive(true);
			}
		}
		tableInitial();
		
		/* set the turn to the right player */
		int indexOfCurrentPlayer = table.getDealerButtonIndex();
		for (int i = 0; i < 3; i++) {
			indexOfCurrentPlayer = table
					.findIndexOfNextActivePlayer(indexOfCurrentPlayer);
		}
		table.setIndexOfCurrentPlayer(indexOfCurrentPlayer);
		EventBus.publish(new Event(Event.Tag.SERVER_SET_TURN,
				indexOfCurrentPlayer));
	}
	
	/**
	 * Performs a showdown by laying out the rest of the cards.
	 * 
	 * @return a list of the winning players of the current round.
	 * @lisastenberg
	 */
	public void performShowdown(List<IPlayer> plrs, int potAmount) {

		/* put all the community cards out on the table */
		int cardsOnTable = table.getCommunityCards().size();
		for(int i = cardsOnTable; i < 5; i++) {
			addCommunityCard();
		}
		
		table.doShowdown(plrs, potAmount);
	}
	
	/**
	 * Performs a bet from specific player.
	 * @param bet The bet (contains owner and value) that should be performed
	 */
	private void performPlayerBet(Bet bet) {
		int value = bet.getValue();
		IPlayer player = bet.getOwner();

		player.makeBet(value);
		table.recieveBet(bet);
	}
	
	/**
	 * Makes the players who's turn it is to post the big and small blind to do 
	 * so.
	 */
	private void postBlinds() {
		List<IPlayer> players = table.getPlayers();
		int dealerButtonIndex = table.getDealerButtonIndex();
				
		int smallBlindIndex = table.findIndexOfNextActivePlayer(dealerButtonIndex);
		int bigBlindIndex =  table.findIndexOfNextActivePlayer(smallBlindIndex);		
		IPlayer smallBlindPlayer = players.get(smallBlindIndex);
		IPlayer bigBlindPlayer = players.get(bigBlindIndex);
		
		/* define the value of the blinds */ 
		int bigBlind = P.INSTANCE.getBigBlindValue();
		int smallBlind = bigBlind / 2;
		
		/* the initial value of the blinds might be bigger than the player's
		 * balance, correct this.. */
		if (smallBlindPlayer.getBalance().getValue() <= smallBlind) {
			smallBlind = smallBlindPlayer.getBalance().getValue();
			smallBlindPlayer.setDoneFirstTurn(true);
		}		
		if (bigBlindPlayer.getBalance().getValue() <= bigBlind) {
			bigBlind = bigBlindPlayer.getBalance().getValue();
			bigBlindPlayer.setDoneFirstTurn(true);
		}
		
		performPlayerBet(new Bet(smallBlindPlayer, smallBlind));
		performPlayerBet(new Bet(bigBlindPlayer, bigBlind));
		
		EventBus.publish(new Event(Event.Tag.SERVER_POST_BLIND, new Bet(smallBlindPlayer,smallBlind)));
		EventBus.publish(new Event(Event.Tag.SERVER_POST_BLIND, new Bet(bigBlindPlayer,bigBlind)));
		
		/* if a player has gone all-in he shall not be able to act */
		if (bigBlindPlayer.isAllIn()) {
			bigBlindPlayer.setDoneFirstTurn(true);
		}
		if (smallBlindPlayer.isAllIn()) {
			smallBlindPlayer.setDoneFirstTurn(true);
		}
	}
	
	/**
	 * Resets the players to its initial mode. This method is used when
	 * setting up a new Round or BettingRound
	 */
	private void playersInitial() {
		table.getRound().getBettingRound().setCurrentBet(new Bet());
		EventBus.publish(new Event(Event.Tag.SERVER_CLEAR_CURRENT_BET,""));
		for (IPlayer p : table.getPlayers()) {
			p.setOwnCurrentBet(0);
			EventBus.publish(new Event(Event.Tag.SERVER_SET_OWN_CURRENT_BET, new Bet(p,0)));
			p.setDoneFirstTurn(false);
		}
	}
	
	/**
	 * This method evaluates what measures has to be done to make the game 
	 * progress after a player has done a turn, and then perform these measures
	 */
	private void progressTurn() {
		
		/* if the betting is done possible all-in case must be handled and then
		 * a new betting round should take place */
		if (table.isBettingDone()) {
			handleAllInCase();
			nextBettingRound();
		} 
		
		/* set the turn to the next player*/
		table.nextPlayer();
		
		/* if a showdown has been done a new round should take place */
		if (table.isShowdownDone()) {
			//Save data
		}
		
	}
	
	/**
	 * Performs a raise.
	 * @param amount The amount to raise the pot with.
	 * @throws IllegalRaiseException 
	 */
	public boolean raise(Bet bet) {
		if(!isValidPlayerAction(bet.getOwner())) {
			return false;
		}
		IPlayer currentPlayer = table.getCurrentPlayer();
		
	
		int raiseValue = bet.getValue() - currentPlayer.getOwnCurrentBet();
		BettingRound currentBettingRound = table.getRound().getBettingRound(); 

		if(raiseValue > currentPlayer.getBalance().getValue()) {
			throw new IllegalRaiseException(
					"Not enough money on balance to make that raise");
		} else if(bet.getValue() < currentBettingRound.getCurrentBet().getValue()
				+ P.INSTANCE.getBigBlindValue()) {
			throw new IllegalRaiseException("The raise have to be bigger" +
					"than the current bet plus big blind.");
		}
		/* arrange the player's balance, bet and the pot according to the raise 
		 */
		table.getRound().getPot().addToPot(raiseValue);
		currentPlayer.getBalance().removeFromBalance(raiseValue);
		currentPlayer.setOwnCurrentBet(raiseValue + currentPlayer.getOwnCurrentBet());
		currentBettingRound.setCurrentBet( new Bet(table.getCurrentPlayer(),
						bet.getValue()/*raiseValue + currentPlayer.getOwnCurrentBet()*/));
		
		currentPlayer.setDoneFirstTurn(true);
		
		EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_BET, new Bet(bet.getOwner(),raiseValue)));
		progressTurn();
		return true;
	}
	
	/**
	 * Resets the table to its initial mode. This method is used when
	 * setting up a new Round.
	 */
	private void tableInitial() {
		table.setShowdownDone(false);
		table.getRound().getPot().emptyPot();
		table.getRound().getPreBettingPot().emptyPot();
		table.clearCommunityCards();
		table.getSidePots().clear();
		EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_POT, table.getRound().getPot()));
		EventBus.publish(new Event(Event.Tag.SERVER_NEW_ROUND,""));
		/* new cards for all active players*/
		table.getDealer().newDeck();
		distributeCards();

		/* give dealer-button to the right player*/
		table.nextDealerButtonIndex();
		
		postBlinds();
	}
	
	/**
	 * This method is called after a side pot is added. It removes from the main
	 * pot what was added to the side pot
	 * 
	 * @param allInValue
	 *            The value of the all-in player's (from which the side pot was
	 *            created) bet.
	 * @param sidePotValue
	 *            The full value of the side pot
	 */
	private void updateMainPot(int allInValue, int sidePotValue) {
		table.getRound().getPreBettingPot().emptyPot();
		for (IPlayer ap : table.getActivePlayers()) {
			ap.setOwnCurrentBet(ap.getOwnCurrentBet() - allInValue);
		}
		table.getRound().getPot().removeFromPot(sidePotValue);
	}
	
}
