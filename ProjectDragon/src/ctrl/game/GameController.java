package ctrl.game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import event.Event;
import event.Event.Tag;
import event.EventBus;
import event.EventHandler;

import model.card.Card;
import model.card.iCard;
import model.game.BettingRound;
import model.game.Dealer;
import model.game.P;
import model.game.Pot;
import model.game.SidePotHandler;
import model.game.Table;
import model.player.Bet;
import model.player.OwnCurrentBetComparator;
import model.player.iPlayer;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;

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
	 */
	//TODO skulle kunna vara i table?
	private void showRiver() {
		Dealer dealer = table.getDealer();
		Card c = dealer.getRiver();
		table.addTableCard(c);
		List<iCard> tmp = new LinkedList<iCard>();
		tmp.add(c);
		EventBus.publish(new Event(Event.Tag.SERVER_ADD_TABLE_CARDS, tmp));
	}

	/**
	 * Adds three new cards to the "table cards"
	 */
	//TODO skulle kunna vara i table?
	private void showFlop() {
		Dealer dealer = table.getDealer();
		//TODO: List<iCard>
		List<Card> flop = dealer.getFlop();
		for (Card c : flop) {
			table.addTableCard(c);
		}
		EventBus.publish(new Event(Event.Tag.SERVER_ADD_TABLE_CARDS, flop));
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

	//TODO: Koden i call, raise, check, fold �r lite lik. refactor?
	/**
	 * Method for handling the call scenario
	 * 
	 * @author forssenm 
	 * @author mattiashenriksson
	 */
	private void doCall() {
		iPlayer currentPlayer = table.getCurrentPlayer();
		Pot currentPot = table.getRound().getPot();
		int currentBetValue = 
				table.getRound().getBettingRound().getCurrentBet().getValue();
		
		/* if the player calls a bet which is bigger than his balance, the 
		 * player will call with as much as his balance allows and move all-in
		 */
		if (currentBetValue > currentPlayer.getBalance().getValue() 
				+ currentPlayer.getOwnCurrentBet()) {
			currentBetValue = currentPlayer.getBalance().getValue() 
				+ currentPlayer.getOwnCurrentBet();
		}
		
		//TODO den h�r delen skulle kunna refactoreras kanske?
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
	
	/**
	 * Checks if a call is valid and does a call if it is valid.
	 * 
	 * @author lisastenberg
	 * @author robinandersson
	 * @param bet	The placed bet.
	 * @return true if call is a valid action.
	 * @throws IllegalCallException
	 */
	public boolean call(Bet bet) {
		//G�r call ovanf�r om call blir giltlig
		int currentBetValue = 
				table.getRound().getBettingRound().getCurrentBet().getValue();

		if(!table.getCurrentPlayer().equals(bet.getOwner())) {
			return false;
		} else if (currentBetValue == -1 //TODO -1 n�dv�ndig? 
				|| currentBetValue == 0) {
			throw new IllegalCallException(
					"Not possible to call since no bet has been posted");
		} else {
			doCall();
			EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_BET,bet));
		}
		return true;
	}
	
	/**
	 * Performs a raise.
	 * @param amount The amount to raise the pot with.
	 * @throws IllegalRaiseException 
	 */
	//TODO delvis otestad
	public boolean raise(Bet bet) {
		iPlayer currentPlayer = table.getCurrentPlayer();
		if(!currentPlayer.equals(bet.getOwner())) {
			return false;
		}
		
		//The amount the player wants to raise with
		int amount = bet.getValue() - currentPlayer.getOwnCurrentBet();
		BettingRound currentBettingRound = table.getRound().getBettingRound(); 
		
		/* you should not be able to raise a bigger amount than what you have
		 * on your balance */
		//TODO: Betyder den h�r koden verkligen det?
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
		
		EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_BET,bet));
		return true;
	}
	
	/**
	 * Performs a fold.
	 */
	//TODO Delvis otestad
	public boolean fold(iPlayer player) {
		//Check if the player is allowed to fold.
		if(!table.getCurrentPlayer().equals(player)) {
			return false;
		}
		player.getHand().discard();
		player.setActive(false);
		player.setDoneFirstTurn(true);
		
		EventBus.publish(new Event(Event.Tag.SERVER_FOLD, player));
		return true;
	}
	
	/**
	 * Performs a check.
	 * @throws IllegalCheckException 
	 */
	//TODO otestat: exception, setOwncurrentBet
	public boolean check(Bet bet) {
		iPlayer currentPlayer = table.getCurrentPlayer();
		
		if(!currentPlayer.equals(bet.getOwner())) {
			return false;
		}
		
		/* if there is a bet bigger than your own current bet you should not
		 * be able to check */
		if (currentPlayer.getOwnCurrentBet() < table.getRound()
				.getBettingRound().getCurrentBet().getValue()) {
			throw new IllegalCheckException(
				"Your own current bet is lesser than the global current bet");
		}
		
		currentPlayer.setDoneFirstTurn(true);
		
		EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_BET,bet));
		return true;
	}
	
	/**
	 * Performs a showdown.
	 * @return a list of the winning players of the current round.
	 */
	//TODO n�dv�ndig h�r? den finns ju redan i table. dock lite annorulunda nu..
	//TODO snygga till/effektivisera?
	//TODO b�r distribute pot plockas ur denna och st� f�r sig sj�lv?
	//TODO en performShowdown och en doShowdown = snurrigt?
	public void performShowdown(List<iPlayer> plrs, int potAmount) {
		
		/* put all the community cards out on the table */
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
		
		EventBus.publish(new Event(Event.Tag.SERVER_ADD_TABLE_CARDS, table.getTableCards()));
		
		/* do showdown */
		table.doShowdown(plrs, potAmount);
	}
	
	/**
	 * Performs actions required for starting a new round
	 */
	public void nextRound() {
		List<iPlayer> players = table.getPlayers();
		
		/* set the table in a "initial" mode, in other words clear all bets
		 * and pots, discard all hands etc. */
		table.setShowdownDone(false); //TODO: beh�vs denna h�r eller bara i nextRound?
		table.getRound().getPot().emptyPot();
		EventBus.publish(new Event(Event.Tag.SERVER_UPDATE_POT, table.getRound().getPot()));
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
		
		EventBus.publish(new Event(Event.Tag.SERVER_NEW_ROUND,""));
		
		/* new cards for all active players*/
		table.getDealer().newDeck();
		distributeCards();

		/* give dealer button to the right player*/
		table.nextDealerButtonIndex();
		
		postBlinds();
		
		/* set the turn to the right player */
		//TODO funkar f�r tv� spelare?, detta g�rs p� ett flertal st�llen = 
		//		refactor? 
		int indexOfCurrentPlayer = table.getDealerButtonIndex();
		for (int i = 0; i < 3; i++) {
			do {
				indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % table.getPlayers().size();
			} while (!players.get(indexOfCurrentPlayer).isActive());
		}
		table.setIndexOfCurrentPlayer(indexOfCurrentPlayer);
		EventBus.publish(new Event(Event.Tag.SERVER_SET_TURN, indexOfCurrentPlayer));
		
	}
	
	/**
	 * Makes the players who's turn it is to post the big and small blind to do 
	 * so.
	 */
	//TODO ska denna vara h�r? likt raise = refactor?
	private void postBlinds() {
		List<iPlayer> players = table.getPlayers();

		int dealerButtonIndex = table.getDealerButtonIndex();
		
		/* define the initial value of the blinds */ 
		int bigBlind = P.INSTANCE.getBigBlindValue();
		int smallBlind = bigBlind / 2;
		
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
		
		/* define the definite value on the blinds (a player migth have to 
		 * small balance to post the full blind) */
		if (smallBlindPlayer.getBalance().getValue() < smallBlind) {
			smallBlind = smallBlindPlayer.getBalance().getValue();
		}
		
		if (bigBlindPlayer.getBalance().getValue() < bigBlind) {
			bigBlind = bigBlindPlayer.getBalance().getValue();
		}
		
		/* post blinds */
		smallBlindPlayer.getBalance().removeFromBalance(smallBlind);
		smallBlindPlayer.setOwnCurrentBet(smallBlind);
		bigBlindPlayer.getBalance().removeFromBalance(bigBlind);
		bigBlindPlayer.setOwnCurrentBet(bigBlind);

		/* add blinds to pot and current bet */
		table.getRound().getPot().addToPot(smallBlind + bigBlind);
		if (bigBlind >= smallBlind) {
			table.getRound().getBettingRound().setCurrentBet(
				new Bet(bigBlindPlayer,bigBlind));
		} else {
			table.getRound().getBettingRound().setCurrentBet(
					new Bet(smallBlindPlayer,smallBlind));
		}
		
		EventBus.publish(new Event(Tag.SERVER_UPDATE_BET, new Bet(smallBlindPlayer,smallBlind)));
		EventBus.publish(new Event(Tag.SERVER_UPDATE_BET, new Bet(bigBlindPlayer,bigBlind)));
		
		/* if a player has gone all-in he shall not be able to act */
		//TODO ska denna vara h�r?
		if (bigBlindPlayer.getBalance().getValue() == 0) {
			bigBlindPlayer.setDoneFirstTurn(true);
		}
		if (smallBlindPlayer.getBalance().getValue() == 0) {
			smallBlindPlayer.setDoneFirstTurn(true);
		}
	}
	
	/**
	 * Performs actions required for starting a new betting round
	 */
	//TODO B�ttre javadoc
	//TODO �vergripande metod = annat namn?
	public void nextBettingRound() {
		List<iPlayer> players = table.getPlayers();
		
		/* the table should have the default settings for a new betting round */
		table.getRound().getBettingRound().setCurrentBet(new Bet());
		for (iPlayer p : players) {
			p.setOwnCurrentBet(0);
			EventBus.publish(new Event(Event.Tag.SERVER_SET_OWN_CURRENT_BET, new Bet(p,0)));
			p.setDoneFirstTurn(false);
		}
		
		/* give right player the turn */
		table.setIndexOfCurrentPlayer(table.getDealerButtonIndex());
		for (int i = 0; i < 2; i++) {
			table.nextPlayer();
		}
		
		/* check if it's time for showdown */
		if (table.getTableCards().size() == 5 || 
				table.getActivePlayers().size() == 1 || 
				table.getActivePlayers().size() == 0) {
			
			/* if so, first perform showdown for possible sidepots */
			List<SidePotHandler> sidePots = table.getSidePots();
			if (sidePots != null) {
				for (SidePotHandler sph : sidePots) {
					performShowdown(sph.getPlayers(),sph.getPot().getValue());
				}
			}
			
			/* then perfom showdown for the table's current state, unless all 
			 * players has gone all-in */
			if (table.getActivePlayers().size() != 0) { 
				performShowdown(table.getActivePlayers(), table.getRound().getPot().getValue());
			}
		
		/* else, if it's time for flop, show flop */
		} else if (table.getTableCards().size() == 0) {
			showFlop();
			table.getRound().getPreBettingPot().setValue(table.getRound().getPot().getValue());
			
		/* if its time for river, show river */	
		} else { //TODO ett till alternativ f�r showTurn?, ev tydligare
			showRiver();
			table.getRound().getPreBettingPot().setValue(table.getRound().getPot().getValue());
		}
	}
	
	/**
	 * Performs the actions which occur when a player has gone all-in 
	 */
	//TODO mkt f�rklaringar h�r. skulle koden varit mer sj�lvf�rklarande?
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
	            EventBus.publish(new Event(Event.Tag.SERVER_SET_PLAYER_UNACTIVE,p));
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
			handleAllIn();
			nextBettingRound();
		} 
		
		/* set the turn to the next player*/
		table.nextPlayer();
		
		/* if a showdown has been done a new round should take place */
		if (table.isShowdownDone()) {
			nextRound();
		}
		
	}

}
